/**
 * <p>Copyright: Copyright(c) 2015</p>
 * <p>Company: gome.com.cn</p>
 * <p>2015年3月3日上午10:36:51</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.neo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.neo.page.PageBean;
import com.neo.page.PageParam;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2015年3月3日上午10:36:51</p>
 * @version V1.0  
 */
public interface MBaseDao<T> {
	long insert(T entity);
	long insert(List<T> list);
	int update(T entity);
	int update(List<T> list);
	T getById(String id);
	int deleteById(String id);
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);
	public List<T> listBy(Map<String, Object> paramMap);
	public T getBy(Map<String, Object> paramMap);
	public SqlSessionTemplate getSessionTemplate();
	public SqlSession getSqlSession();
}
