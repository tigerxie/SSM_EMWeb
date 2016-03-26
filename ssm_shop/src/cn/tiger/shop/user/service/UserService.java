package cn.tiger.shop.user.service;

import cn.tiger.shop.user.vo.User;

public interface UserService {

	//异步校验用户名
	public int ajaxFindByName(String username);

	//异步校验邮箱
	public int ajaxFindByEmail(String email);

	//注册提交
	public void save(User user);

	//验证激活码
	public User findByCode(String code);

	//修改用户
	public void update(User exitUser);

	//登录提交
	public User login(User user);
}
