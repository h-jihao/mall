package org.aji.mall.service.impl;

import org.aji.mall.dao.UserMapper;
import org.aji.mall.enums.ResponseEnum;
import org.aji.mall.enums.RoleEnum;
import org.aji.mall.pojo.User;
import org.aji.mall.service.IUserService;
import org.aji.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseVo<User> regist(User user) {
        //error();
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if(countByUsername > 0){
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if(countByEmail > 0){
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(Charset.forName("utf-8"))));

        user.setRole(RoleEnum.CUSTOMER.getCode());
        int flag = userMapper.insertSelective(user);
        if(flag == 0){
            return ResponseVo.error(ResponseEnum.SERVER_ERROR);
        }

        return ResponseVo.success();
    }

    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if(user == null){
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        if(!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(Charset.forName("utf-8"))))){
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        user.setPassword("");
        return ResponseVo.success(user);
    }

//    private void error(){
//        throw new RuntimeException("意外错误");
//    }


}
