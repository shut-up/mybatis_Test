package cn.itcast.mybatis.dao;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.User;

public class UserDaoImpTest {
	
	SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setUp() throws Exception{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() throws Exception {
	 	UserDao userDao = new UserDaoImp(sqlSessionFactory);
	 	User user = userDao.findUserById(1);
	 	System.out.println(user);
	}
	
	@Test
	public void testInsertUser() throws Exception {
		UserDao userDao = new UserDaoImp(sqlSessionFactory);
		User user = new User();
		user.setUsername("张三");
		user.setBirthday(new Date());
		user.setSex("2");
		user.setAddress("广东广州");
		userDao.insertUser(user);
	}

	@Test
	public void testDeleteUser() throws Exception {
		UserDao userDao = new UserDaoImp(sqlSessionFactory);
	 	userDao.DeleteUser(34);
	}


}
