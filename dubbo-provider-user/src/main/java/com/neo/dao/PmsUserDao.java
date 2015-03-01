package com.neo.dao;

import com.neo.entity.PmsUser;


/**
 * 
 * @描述: 用户表数据访问层接口.
 * @作者: Donglx .
 * @创建时间: 2015-02-24,下午5:51:47 .
 * @版本: 1.0 .
 */
public interface PmsUserDao extends BaseDao<PmsUser> {

	/**
	 * 根据用户登录名获取用户信息.
	 * 
	 * @param loginName
	 *            .
	 * @return user .
	 */
	PmsUser findByUserNo(String userNo);

}
