package cn.itcast.mybatis.mapper;

import java.util.List;

import cn.itcast.mybatis.po.User;
import cn.itcast.mybatis.po.UserCustom;
import cn.itcast.mybatis.po.UserQueryVo;

/*
 * dao接口
 */

public interface UserMapper {
	public User findUserById(int id)throws Exception;
	public User findUserByIdResultMap(int id)throws Exception;
	public List<UserCustom> findUserList(UserQueryVo userQueryVo)throws Exception;
	
	public List<User> findUserByName(String name) throws Exception;
	public void updateUser(User user)throws Exception;
//	public void DeleteUser(int id) throws Exception;
//	public void insertUser(User user) throws Exception;
}
