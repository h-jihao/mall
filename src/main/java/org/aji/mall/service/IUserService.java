package org.aji.mall.service;

import org.aji.mall.pojo.User;
import org.aji.mall.vo.ResponseVo;

public interface IUserService {

    ResponseVo<User> regist(User user);

    ResponseVo<User> login(String username, String password);
}
