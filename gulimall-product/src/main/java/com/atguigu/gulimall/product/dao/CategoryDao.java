package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author guoyuhang
 * @email 15512702732@163.com
 * @date 2021-01-06 19:29:44
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
