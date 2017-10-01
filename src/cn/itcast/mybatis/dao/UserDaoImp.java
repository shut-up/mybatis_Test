package cn.itcast.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.itcast.mybatis.po.User;

/*
 * dao接口实现类
 */
public class UserDaoImp implements UserDao{

	SqlSessionFactory sqlSessionFactory ;
	
	public UserDaoImp(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public User findUserById(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("test.findUserById", id);
		sqlSession.close();
		return user;
	}
	
	@Override
	public void insertUser(User user) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("test.insertUser", user);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void DeleteUser(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("test.deleteUser", id);
		sqlSession.commit();
		sqlSession.close();
		
	}



}
