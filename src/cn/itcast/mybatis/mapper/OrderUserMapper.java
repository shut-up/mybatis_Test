package cn.itcast.mybatis.mapper;

import java.util.List;

import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustomer;
import cn.itcast.mybatis.po.User;
import cn.itcast.mybatis.po.UserCustom;
import cn.itcast.mybatis.po.UserQueryVo;

/*
 * dao接口
 */

public interface OrderUserMapper {
	public List<OrdersCustomer> queryOrderUser() throws Exception;
	public List<Orders> queryOrderUserResultMap() throws Exception;
	public List<Orders> queryOrderandOrderDetailResultMap() throws Exception;
	public List<User> queryUserAndItemResultMap() throws Exception;
	public List<Orders> findOrderAndUserLazyLoading() throws Exception;
}
