package com.neo.dao.impl;

import org.springframework.stereotype.Repository;

import com.neo.dao.BaseDaoImpl;
import com.neo.dao.PmsUserDao;
import com.neo.entity.PmsUser;


/**
 * 
 * @描述: 用户表数据访问层接口实现类.
 * @作者: Donglx .
 * @创建时间: 2015-02-24,下午5:51:47 .
 * @版本: 1.0 .
 */
@Repository("pmsUserDao")
public class PmsUserDaoImpl extends BaseDaoImpl<PmsUser> implements PmsUserDao {

	/**
	 * 根据用户登录名获取用户信息.
	 * 
	 * @param loginName
	 *            .
	 * @return user .
	 */

	public PmsUser findByUserNo(String userNo) {
		return super.getSqlSession().selectOne(getStatement("findByUserNo"), userNo);
	}

}
