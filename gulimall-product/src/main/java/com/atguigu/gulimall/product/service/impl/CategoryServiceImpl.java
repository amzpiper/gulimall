package com.atguigu.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;


/**
 * @author guoyh
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询所有分类并封装成树行结构
     * @return List<CategoryEntity>
     */
    @Override
    public List<CategoryEntity> listWithTree() {
        //1.查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);

        //2.组装成父子树形结构
        //找到所以的一级分类
        return entities.stream().filter(categoryEntity ->
            //当该菜单的父id等于菜单id
            categoryEntity.getParentCid() == 0
        ).map(menu -> {
            //把当前菜单的所有子菜单找到并返回
            menu.setChildren(this.getChildren(menu, entities));
            return menu;
       }).sorted((preMenu, lastMenu) -> {
            //排序 getSort()可能返回空，所以做以下处理
            return (preMenu.getSort() == null ? 0 : preMenu.getSort()) - (lastMenu.getSort() == null ? 0 : lastMenu.getSort());
        }).collect(Collectors.toList());
    }

    /**
     * 把当前菜单的所有子菜单找到并返回
     * @param root 当前菜单
     * @param all  所有菜单
     * @return List<CategoryEntity>
     */
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {

        return all.stream().filter(categoryEntity -> {
            //当该菜单的父id等于菜单id
            return categoryEntity.getParentCid().equals(root.getCatId());
        }).map(menu->{
            //当前子菜单可能还有子菜单，找到设置所有子菜单(递归找)
            menu.setChildren(this.getChildren(menu,all));
            return menu;
        }).sorted((preMenu,lastMenu)->{
            return (preMenu.getSort() == null ? 0 : preMenu.getSort()) - (lastMenu.getSort() == null ? 0 : lastMenu.getSort());
        }).collect(Collectors.toList());
    }

}