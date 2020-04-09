package com.shadow.dubbo.consumer.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.shadow.dubbo.entity.UserAddress;
import com.shadow.dubbo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAddressController {

    @Reference
    private UserService userService;

    @GetMapping("/user/addr/{uid}")
    public String remoteUserAddr(@PathVariable("uid") String uid){
        List<UserAddress> userList = userService.getUserList(uid);
        userList.forEach((item) -> {
            System.out.println(item.getName());
            System.out.println(item.getId());
        });
        return "success";
    }
}
