package com.shadow.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shadow.dubbo.entity.UserAddress;
import com.shadow.dubbo.service.UserService;

import java.util.Arrays;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserAddress> getUserList(String uId) {
        UserAddress address1 = new UserAddress(1L,"深圳");
        UserAddress address2 = new UserAddress(2L,"张家界");
        return Arrays.asList(address1,address2);
    }
}
