package com.kys.service;

public class UserServiceImpl implements UserService {

	@Override
	public int update(int id, String name) {
		System.out.println("执行修改操作成功...");
		return 1;
	}

}
