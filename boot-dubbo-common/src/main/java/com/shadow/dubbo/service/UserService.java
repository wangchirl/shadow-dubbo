package com.shadow.dubbo.service;

import com.shadow.dubbo.entity.UserAddress;

import java.util.List;

public interface UserService {

    List<UserAddress> getUserList(String uId);

}
