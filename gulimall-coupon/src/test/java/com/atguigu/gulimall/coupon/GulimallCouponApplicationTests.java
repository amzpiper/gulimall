package com.atguigu.gulimall.coupon;

import com.atguigu.gulimall.coupon.controller.CouponController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallCouponApplicationTests {

    @Autowired
    CouponController couponController;

    @Test
    void contextLoads() {
        
    }

}
