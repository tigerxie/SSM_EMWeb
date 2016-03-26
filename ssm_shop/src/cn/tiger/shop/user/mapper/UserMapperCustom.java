package cn.tiger.shop.user.mapper;

import java.util.List;

import cn.tiger.shop.user.vo.User;

public interface UserMapperCustom extends UserMapper {
    
	//通过 Email 查询用户
	public List<User> findUserByEmail(String email)throws Exception;

	//通过 username 查找用户
	public int countByUsername(String username);

	//通过 email 查询用户
	public int countByEmail(String email);

	//通过 code 查询并返回用户
	public User findByCode(String code);

	//通过用户名和密码查找用户
	public User findByUser(User user);

}