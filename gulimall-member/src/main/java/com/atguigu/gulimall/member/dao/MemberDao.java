package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author guoyuhang
 * @email 15512702732@163.com
 * @date 2021-01-26 19:57:22
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
