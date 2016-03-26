package cn.tiger.shop.user.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import cn.tiger.shop.user.mapper.UserMapperCustom;
import cn.tiger.shop.user.service.UserService;
import cn.tiger.shop.user.vo.User;
import cn.tiger.shop.utils.MailUtils;
import cn.tiger.shop.utils.UUIDUtils;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapperCustom userMapperCustom;

	//异步校验用户名
	@Override
	public int ajaxFindByName(String username) {
		
		return userMapperCustom.countByUsername(username);
	}

	//异步校验邮箱
	@Override
	public int ajaxFindByEmail(String email) {

		return userMapperCustom.countByEmail(email);
	
	}

	//注册提交
	@Override
	public void save(User user) {

		/*
		 * 1. 补全uid(自增)、state、code
		 */
		user.setState(0);	//0:代表未激活，1:代表已激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code); //通过uuid生成32位随机字符
		/*
		 * 2. 调用dao完成保存
		 */
		userMapperCustom.insert(user);
		/*
		 * 3. 发送激活邮件
		 */
		MailUtils.sendMail(user.getEmail(), code);
		
	}

	//验证激活码
	@Override
	public User findByCode(String code) {
		return userMapperCustom.findByCode(code);
	}

	//修改用户
	@Override
	public void update(User exitUser) {
		userMapperCustom.updateByPrimaryKey(exitUser);
	}

	//登录提交
	@Override
	public User login(User user) {
		return userMapperCustom.findByUser(user);
	}
}
