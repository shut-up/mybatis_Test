package cn.itcast.mybatis.dao;

import cn.itcast.mybatis.po.User;

/*
 * dao接口
 */

public interface UserDao {
	public User findUserById(int id) throws Exception;
	public void DeleteUser(int id) throws Exception;
	public void insertUser(User user) throws Exception;
}
