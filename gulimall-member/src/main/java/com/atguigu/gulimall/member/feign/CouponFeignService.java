package com.atguigu.gulimall.member.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//告诉springcloud这个是个远程客户端，想要调用gulimall-coupon
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    //测试远程调用服务,路径得写全
    @RequestMapping("/coupon/coupon/member/list")
    public R membersCoupons();

}
