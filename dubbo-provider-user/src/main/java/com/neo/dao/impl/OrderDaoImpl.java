package com.neo.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neo.dao.OrderDao;
import com.neo.entity.TOrder;
import com.neo.exceptions.BizException;

@Repository("orderDao")
public class OrderDaoImpl extends SqlSessionDaoSupport implements OrderDao{
	protected static final Logger log = LoggerFactory.getLogger(OrderDaoImpl.class);

	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}
	
	@Override
	public long insert(TOrder order) {
		int result = sessionTemplate.insert(getStatement("insert"), order);

		if (result <= 0) {
			throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,insert返回0.{%s}", getStatement("insert"));
		}
		return result;
	}

	
	public String getStatement(String sqlId) {
		String name = this.getClass().getName();
		StringBuffer sb = new StringBuffer();
		sb.append(name).append(".").append(sqlId);
		String statement = sb.toString();

		return statement;
	}
}
