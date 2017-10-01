package cn.itcast.mybatis.mapper;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustomer;
import cn.itcast.mybatis.po.User;

public class OrderUserMapperTest {

	SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testQueryOrderUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrderUserMapper mapper = sqlSession.getMapper(OrderUserMapper.class);
		List<OrdersCustomer> list = mapper.queryOrderUser();
		System.out.println(list);
	}
	
	@Test
	public void testqueryOrderUserResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrderUserMapper mapper = sqlSession.getMapper(OrderUserMapper.class);
		List<OrdersCustomer> list = mapper.queryOrderUser();
		System.out.println(list);
	}
	
	@Test
	public void testqueryOrderandOrderDetailResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrderUserMapper mapper = sqlSession.getMapper(OrderUserMapper.class);
		List<Orders> list = mapper.queryOrderandOrderDetailResultMap();
		System.out.println(list);
	}

	@Test
	public void testqueryUserAndItemResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrderUserMapper mapper = sqlSession.getMapper(OrderUserMapper.class);
		List<User> list = mapper.queryUserAndItemResultMap();
		System.out.println(list);
	}

	//测试延时加载
	@Test
	public void testFindOrderAndUserLazyLoading() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrderUserMapper mapper = sqlSession.getMapper(OrderUserMapper.class);
		List<Orders> list = mapper.findOrderAndUserLazyLoading();
		for (Orders orders:list) {
			User user = orders.getUser();
			System.out.println(user);
		}
	}

	
}
