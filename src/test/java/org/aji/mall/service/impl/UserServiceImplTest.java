package org.aji.mall.service.impl;

import org.aji.mall.MallApplicationTests;
import org.aji.mall.enums.RoleEnum;
import org.aji.mall.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
public class UserServiceImplTest extends MallApplicationTests {
    @Autowired
    private UserServiceImpl userService;
    @Test
    public void regist() {
        User user = new User("jack","1234","jack@163.com", RoleEnum.CUSTOMER.getCode());
        userService.regist(user);
    }
}