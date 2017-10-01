package cn.itcast.mybatis.first;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.lf5.util.Resource;
import org.junit.Test;

import cn.itcast.mybatis.po.User;


public class MyBatisFirst {
	
	public SqlSession getsqlSession() throws IOException{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	//根据id查询用户信息，返回结果对象
	@Test
	public void findUserByIdTest() throws IOException{
		SqlSession sqlSession = getsqlSession();
		//通过sqlSession操作数据库
		//第一个参数：映射文件中的statement的 id，等于namespace+"."+statement的 id
		//第二个参数：指定和映射文件所匹配的parameterType类型的值
		//sqlSession.selectOne返回的对象是映射文件中resultType类型的对象
		User user = sqlSession.selectOne("test.findUserById", 1);
		System.out.println(user);
		sqlSession.close();
	}
	
	//根据用户名模糊查询用户信息，返回结果对象
		@Test
		public void findUserByNameTest() throws IOException{
			SqlSession sqlSession = getsqlSession();
			//通过sqlSession操作数据库
			//第一个参数：映射文件中的statement的 id，等于namespace+"."+statement的 id
			//第二个参数：指定和映射文件所匹配的parameterType类型的值
			//sqlSession.selectOne返回的对象是映射文件中resultType类型的对象
			List<User> list = sqlSession.selectList("test.findUserByName", '五' );
			System.out.println(list);
			sqlSession.close();
		}
		
	//添加用户信息
		@Test
		public void insertUserTest() throws IOException{
			SqlSession sqlSession = getsqlSession();
			//通过sqlSession操作数据库
			//第一个参数：映射文件中的statement的 id，等于namespace+"."+statement的 id
			//第二个参数：指定和映射文件所匹配的parameterType类型的值
			
			//要插入的用户对象
			User user = new User();
			user.setUsername("王晓明");
			user.setBirthday(new Date());
			user.setSex("2");
			user.setAddress("江苏南京");
			sqlSession.insert("test.insertUser", user);
			
			System.out.println(user.getId());
			
			//事务提交
			sqlSession.commit();
			//关闭事务
			sqlSession.close();
		}
		
		//删除用户信息
		@Test
		public void deleteUserTest() throws IOException{
			SqlSession sqlSession = getsqlSession();
			//通过sqlSession操作数据库
			//第一个参数：映射文件中的statement的 id，等于namespace+"."+statement的 id
			//第二个参数：指定和映射文件所匹配的parameterType类型的值
			
			sqlSession.delete("test.deleteUser", 31);
			
			//事务提交
			sqlSession.commit();
			//关闭事务
			sqlSession.close();
		}
		
		//更新用户信息
		@Test
		public void updateUserTest() throws IOException{
			SqlSession sqlSession = getsqlSession();
			//通过sqlSession操作数据库
			//第一个参数：映射文件中的statement的 id，等于namespace+"."+statement的 id
			//第二个参数：指定和映射文件所匹配的parameterType类型的值
			User user = new User();
			user.setId(27);
			user.setUsername("王大刚");
			user.setBirthday(new Date());
			user.setSex("1");
			user.setAddress("江苏常州");
			sqlSession.update("test.updateUser", user);
			
			//事务提交
			sqlSession.commit();
			//关闭事务
			sqlSession.close();
		}
						
				
}
