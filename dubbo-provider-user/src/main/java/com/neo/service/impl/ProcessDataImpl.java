package com.neo.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.dao.PmsUserDao;
import com.neo.entity.PmsUser;
import com.neo.service.IProcessData;

@Service("demoService")
public class ProcessDataImpl implements IProcessData {
	@Autowired
	private PmsUserDao pmsUserDao;

	@Override
	public String hello(String name) {
		System.out.println(name);
		PmsUser pmsUser = pmsUserDao.getById(1);
		JSONObject jsonObject = JSONObject.fromObject(pmsUser);
		System.out.println(jsonObject.toString());
		return "hello : " + name;
	}

}
