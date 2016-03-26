package cn.tiger.shop.adminuser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tiger.shop.adminuser.mapper.AdminuserMapperCustom;
import cn.tiger.shop.adminuser.service.AdminuserService;
import cn.tiger.shop.adminuser.vo.Adminuser;

public class AdminuserServiceImpl implements AdminuserService {

	@Autowired
	private AdminuserMapperCustom adminuserMapperCustom;
	
	//管理员登录
	@Override
	public Adminuser login(Adminuser adminUser) {
		return adminuserMapperCustom.findByUsernamePassword(adminUser);
	}

}
