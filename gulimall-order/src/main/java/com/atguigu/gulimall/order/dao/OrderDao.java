package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author guoyuhang
 * @email 15512702732@163.com
 * @date 2021-01-26 20:22:04
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
