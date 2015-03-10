package com.neo.zkp;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class TestZookeeper {
	public final static String ZK_HOST = "192.168.149.102:2181";
	public static void main(String[] args) {
		try {
			String path = "/ticket/test";
			ZooKeeper zk = new ZooKeeper(ZK_HOST, 30000, new Watcher(){
				public void process(WatchedEvent event) {
					System.out.println("已经触发了" + event.getType() + "事件！");
				}
				
			});
			
			//zk.create(path, "00".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//			Stat stat = zk.exists(path, false);
//			zk.setData(path, "00".getBytes(), 0);
//			Stat stat = zk.exists(path, false);
//			stat.setVersion(8);
//			zk.getData(path, false, stat);
//			System.out.println(stat);
			//zk.setData("/ticket/draw", "00".getBytes(), -1);
			for (int i = 0; i < 30; i++) {

				path = String.format("/ticket/tno/t%010d", i);
				byte[] data = zk.getData(path, false, null);
				System.out.println(path + " => " + new String(data));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
