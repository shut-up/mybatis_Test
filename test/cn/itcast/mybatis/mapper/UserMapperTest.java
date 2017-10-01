package cn.itcast.mybatis.mapper;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.User;
import cn.itcast.mybatis.po.UserCustom;
import cn.itcast.mybatis.po.UserQueryVo;


public class UserMapperTest implements Serializable {
SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setUp() throws Exception{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("1");
		//动态sql 添加了<where> <if>标签
//		userCustom.setUsername("测试");
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(10);
		ids.add(16);
		userQueryVo.setIds(ids);
		userQueryVo.setUserCustom(userCustom);
		
		List<UserCustom> list = userMapper.findUserList(userQueryVo);
		System.out.println(list);
	}


	@Test
	public void testFindUserById() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(10);
		System.out.println(user);
	}
	
	@Test
	public void testFindUserByIdResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserByIdResultMap(10);
		System.out.println(user);
	}
	
	
	@Test
	public void testFindUserByName() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.findUserByName("五");
		System.out.println(list);
	}
	
	//测试一级缓存
	@Test
	public void testCache1() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);
		
		//commit之后会清空一级缓存中的内容,如果没有这个update操作，下面的findUserById不会执行sql语句，而是直接从缓存中获取内容
		user1.setUsername("无名");
		userMapper.updateUser(user1);
		sqlSession.commit();
		
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		
		sqlSession.close();

	}

	

	//测试二级缓存，可以跨sqlsession缓存
	@Test
	public void testCache2()  throws Exception {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		
		//第一次查询,从数据库中查询（sql语句查询）
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		User user1 = userMapper1.findUserById(1);
		System.out.println(user1);
		//只有执行close（）方法才会把查询的数据放到二级缓存
		sqlSession1.close();
		
//		//commit之后会清空二级缓存中的内容
//		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
//		user1.setUsername("张明明");
//		userMapper3.updateUser(user1);
//		sqlSession3.commit();
		
		//第二次查询，如果没有commit操作的话会直接从缓存中查询
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);
		sqlSession2.close();

	}


}





