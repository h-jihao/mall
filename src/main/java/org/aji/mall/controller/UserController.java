package org.aji.mall.controller;

import lombok.extern.slf4j.Slf4j;
import org.aji.mall.consts.MallConst;
import org.aji.mall.enums.ResponseEnum;
import org.aji.mall.form.UserRegistForm;
import org.aji.mall.form.UserLoginForm;
import org.aji.mall.pojo.User;
import org.aji.mall.service.IUserService;
import org.aji.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/user/regist")
    public ResponseVo<User> regeist(@Valid @RequestBody UserRegistForm userForm,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("message:{},{}",
                    bindingResult.getFieldError().getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(ResponseEnum.PARAM_ERROR,bindingResult);
        }
        User user = new User();
        BeanUtils.copyProperties(userForm,user);
        return userService.regist(user);
    }

    @RequestMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  HttpServletRequest request){
        if(bindingResult.hasErrors()){
           log.error("message:{},{}:",bindingResult.getFieldError().getField(),
                   bindingResult.getFieldError().getDefaultMessage());
           return ResponseVo.error(ResponseEnum.PARAM_ERROR,bindingResult);
        }

        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(MallConst.CURRENT_USER,userResponseVo.getData());
        log.info("保存的用户session："+session.getId());
        return userResponseVo;
    }

    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info("用户信息:"+session.getId());
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success(user);
    }

    @RequestMapping("/user/logout")
    public ResponseVo<User> logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success();
    }
}
