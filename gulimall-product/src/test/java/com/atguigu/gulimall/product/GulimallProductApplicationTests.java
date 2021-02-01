package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {
        //添加
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setName("huawei");
//        brandService.save(brandEntity);
//        System.out.println("保存成功");
        //修改
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setBrandId(1L);
//        brandEntity.setDescript("华为");
//        brandService.updateById(brandEntity);
        //查询

        List<BrandEntity> brandEntity = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
        brandEntity.forEach((item)->{
            System.out.println(item);
        });
    }

}
