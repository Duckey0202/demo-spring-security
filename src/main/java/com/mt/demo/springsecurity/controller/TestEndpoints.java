package com.mt.demo.springsecurity.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xujingfeng on 2017/8/7.
 */
@RestController
public class TestEndpoints {

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }

    @PostMapping("/test")
    public String testPost(@RequestBody JSONObject object) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("object: " + object.toJSONString());
        return "testPost id : " + authentication.getName();
    }

}
