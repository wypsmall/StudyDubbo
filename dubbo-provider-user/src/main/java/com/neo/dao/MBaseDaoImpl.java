package com.neo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.neo.page.PageBean;
import com.neo.page.PageParam;

public class MBaseDaoImpl<T> extends SqlSessionDaoSupport implements MBaseDao<T> {
	protected static final Logger log = LoggerFactory.getLogger(MBaseDaoImpl.class);
	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_BATCH_UPDATE = "batchUpdate";
	public static final String SQL_GET_BY_ID = "getById";
	public static final String SQL_DELETE_BY_ID = "deleteById";
	public static final String SQL_LIST_PAGE = "listPage";
	public static final String SQL_LIST_PAGE_COUNT = "listPageCount";
	public static final String SQL_LIST_BY = "listBy";
	public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam"; // 根据当前分页参数进行统计

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
	public long insert(T entity) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public long insert(List<T> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(T entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(List<T> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> listBy(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getBy(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}


}
