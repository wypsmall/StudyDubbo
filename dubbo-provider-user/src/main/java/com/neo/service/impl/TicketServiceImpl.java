package com.neo.service.impl;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

import com.neo.service.ITicketService;
import com.neo.zkp.ZkpUtil;

@Service("ticketService")
public class TicketServiceImpl implements ITicketService {
	/** 已领取数量 */
	public final static String NODE_DRAW_NUM = "/ticket/draw3";

	@Override
	public String getTicketNo(String userId) {
		String tickno = null;
		try {
			int index = -1;
			ZooKeeper zk = ZkpUtil.getInstance().getZk();
			//判断索引数据node是否存在，不存在创建，存在则获取当前数据版本号，作为数据索引
			Stat st = zk.exists(NODE_DRAW_NUM, false);
			if(null == st) {
				zk.create(NODE_DRAW_NUM, "00".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				index = 0;
			} else {
				index = st.getVersion();//数据版本号作为数据索引
			}
			System.out.println("=============================>index:"+index);
			//拼装票券数据node路径 格式为/ticket/tno/t0000000008
			String path = String.format("/ticket/tno/t%010d", index);
			System.out.println("=============================>path:"+path);
			//判断票券数据node是否存在，如果不存在，表示无此票券，存在则获取数据stat
			Stat stk = zk.exists(path, false);
			if(null == stk) {
				System.out.println("=============================ticket distribute finished!");
				return "No Tickets";
			} else {
				System.out.println("=============================>stk.getVersion():"+stk.getVersion());
			}
			//zookeeper中票券节点的数据版本号，用于测试
			int ticketVersion = 3;
			if(null != stk && stk.getVersion() == ticketVersion) {
				//乐观锁的重要体现，如果该索引节点的版本已经被修改，表示对应的票券节点已经被领取（分配）
				//就会抛异常，如果该操作在一个数据中存在，那么会造成事务回滚
				zk.setData(NODE_DRAW_NUM, "00".getBytes(), index);
				
				tickno = new String(zk.getData(path, false, null));
				zk.setData(path, ("S-"+tickno).getBytes(), ticketVersion);
				System.out.println("=============================>tickno:"+tickno);
			} else {
				//如果此节点的票券已经被领取则索引节点数据版本加一
				System.out.println("=============================>ticket is used:"+tickno);
				zk.setData(NODE_DRAW_NUM, "00".getBytes(), index);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			//throw new RuntimeException(e);
		}
		System.out.println("=============================>result:" + tickno);
		return tickno;
	}
	
	
	
	
	
	/*
	@Override
	public Integer getQueueNo() {
		Integer qn = -1;
		try {
			ZooKeeper zk = ZkpUtil.getInstance().getZk();
			Stat st = zk.exists("/ticket/tno", false);
			int max = st.getNumChildren();
			Stat stat = zk.exists("/ticket/queue", false);
			if(max > stat.getVersion()) {
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qn;
	}
	/*
	 * 使用乐观锁原理
	 */
/*	public String getTicketNo(Integer queueNo) {
		String tickno = null;
		try {
			int index = -1;
			ZooKeeper zk = ZkpUtil.getInstance().getZk();
			Stat st = zk.exists(NODE_DRAW_NUM, false);
			if(null == st) {
				zk.create(NODE_DRAW_NUM, "00".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				index = 0;
			} else {
				index = st.getVersion();
			}
			System.out.println("=============================>index:"+index);
			String path = String.format("/ticket/tno/t%010d", index);
			System.out.println("=============================>path:"+path);
			Stat stk = zk.exists(path, false);
			if(null == stk) {
				System.out.println("=============================ticket distribute finished!");
				return "No Tickets";
			} else {
				System.out.println("=============================>stk.getVersion():"+stk.getVersion());
			}
			//zookeeper中票券节点的数据版本号
			int ticketVersion = 3;
			if(null != stk && stk.getVersion() == ticketVersion) {
				//这一步会抛异常
				zk.setData(NODE_DRAW_NUM, "00".getBytes(), index);
				
				tickno = new String(zk.getData(path, false, null));
				zk.setData(path, ("S-"+tickno).getBytes(), ticketVersion);
				System.out.println("=============================>tickno:"+tickno);
			} else {
				System.out.println("=============================>ticket is used:"+tickno);
				zk.setData(NODE_DRAW_NUM, "00".getBytes(), index);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			//throw new RuntimeException(e);
		}
		System.out.println("=============================>result:" + tickno);
		return tickno;
	}*/
/*
	public String getTicketNo(Integer queueNo) {
		String path = String.format("/ticket/tno/t%010d", queueNo);
		ZooKeeper zk = ZkpUtil.getInstance().getZk();
		String tickno = null;
		try {
			Stat st = zk.exists(path, false);
			tickno = new String(zk.getData(path, false, st));
			if(0 == st.getVersion()) {
				zk.setData(path, ("S-"+tickno).getBytes(), -1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(null != tickno) {
					zk.create("/ticket/tno/t", tickno.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
					zk.setData(path, ("F-"+tickno).getBytes(), -1);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return null;
	}*/

}
