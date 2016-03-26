package cn.tiger.shop.adminuser.mapper;

import cn.tiger.shop.adminuser.vo.Adminuser;


public interface AdminuserMapperCustom {


	//通过用户名和密码查询
	Adminuser findByUsernamePassword(Adminuser adminUser);
  
	
	
}