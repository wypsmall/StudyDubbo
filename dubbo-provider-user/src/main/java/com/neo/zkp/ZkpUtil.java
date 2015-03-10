package com.neo.zkp;

import java.math.BigInteger;
import java.util.Random;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZkpUtil {
	public final static String ZK_HOST = "192.168.149.102:2181";
	/** 排队数量 */
	public final static String NODE_QUEUE_NUM = "/ticket/queue";
	/** 已领取数量 */
	public final static String NODE_DRAW_NUM = "/ticket/draw3";
	/** 发放不成功回收数量 */
	public final static String NODE_RECYCLE_NUM = "/ticket/recycle";
	
	private static final Random RANDOM = new Random();  
	
	private static final Integer TOTAL = 10000;
	private static ZkpUtil self = null;
	private ZooKeeper zk = null;
	
	public ZooKeeper getZk() {
		return zk;
	}

	public static ZkpUtil getInstance() {
		if(null == self) {
			synchronized (ZkpUtil.class) {
				if(null == self) {
					self = new ZkpUtil();
				}
			}
		}
		return self;
	}
	
	private ZkpUtil() {
		if(null == zk) {
			try {
				zk = new ZooKeeper(ZK_HOST, 30000, new Watcher(){
					public void process(WatchedEvent event) {
						System.out.println("已经触发了" + event.getType() + "事件！");
					}
					
				});
			} catch (Exception e) {
				zk = null;
				e.printStackTrace();
			}
		}
	}
	
	private Stat detectNode(String path) throws Exception {
		Stat st = zk.exists(path, false);
		if(null == st) {
			zk.create(path, "00".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			st = zk.exists(path, false);
		}
		return st;
	}
	
	public int getQueueNum() {
		int index = -1;
		//如果领取的数量达到发放总数则不再发放排队号
		if(getDrawNum() == TOTAL)
			return index;
		try {
			detectNode(NODE_QUEUE_NUM);
			//通过设置数据增加版本号
			Stat st = zk.setData(NODE_QUEUE_NUM, "00".getBytes(), -1);
			index = st.getVersion();
		} catch (Exception e) {
			e.printStackTrace();
			index = -1;
		}
		return index;
	}
	
	public int recordDrawNum() {
		int num = -1;
		try {
			detectNode(NODE_DRAW_NUM);
			//通过设置数据增加版本号
			Stat st = zk.setData(NODE_DRAW_NUM, "00".getBytes(), -1);
			num = st.getVersion();
		} catch (Exception e) {
			e.printStackTrace();
			num = -1;
		}
		return num;
	}
	
	public int getDrawNum() {
		int num = -1;
		try {
			detectNode(NODE_DRAW_NUM);
			//获取当前数据的版本号
			Stat st = zk.exists(NODE_DRAW_NUM, false);
			num = st.getVersion();
		} catch (Exception e) {
			e.printStackTrace();
			num = -1;
		}
		return num;
	}
	
	public int getRecycleNum() {
		int num = -1;
		try {
			detectNode(NODE_RECYCLE_NUM);
			//获取当前数据的版本号
			Stat st = zk.exists(NODE_RECYCLE_NUM, false);
			num = st.getVersion();
		} catch (Exception e) {
			e.printStackTrace();
			num = -1;
		}
		return num;
	}
	
	
	public static void main(String[] args) {
		try {
			ZkpUtil.getInstance().initTicketNo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * desc:准备票券
	 * <p>创建人：wangyunpeng , 2015年3月10日上午10:41:35</p>
	 * @throws Exception
	 */
	private void initTicketNo() throws Exception {
		//detectNode("/ticket/tno");
//		for (int i = 0; i < TOTAL-5; i++) {
//			
//		}
//		List<String> paths = zk.getChildren("/ticket/tno", false);
//		for (String spath : paths) {
//			System.out.println(spath);
//			zk.setData("/ticket/tno/"+spath, generateGUID().getBytes(), -1);
//		}
		
//		for (int i = 0; i < TOTAL; i++) {
//			
//			String path = String.format("/ticket/tno/t%010d", i);
//			byte[] data = zk.getData(path, false, null);
//			System.out.println(path + " => " + new String(data));
//		}
		
		//clear queue no
//		Stat st = zk.exists(NODE_QUEUE_NUM, false);
//		if(null == st) {
//			zk.create(NODE_QUEUE_NUM, "00".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//			st = zk.exists(NODE_QUEUE_NUM, false);
//		} else {
//			zk.delete(NODE_QUEUE_NUM, -1);
//		}
		zk.create(NODE_DRAW_NUM, "00".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	
	private String generateGUID()  
    {  
        return new BigInteger(165, RANDOM).toString(36).toUpperCase();  
    } 
}
