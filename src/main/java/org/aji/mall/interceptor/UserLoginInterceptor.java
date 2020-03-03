package org.aji.mall.interceptor;

import org.aji.mall.consts.MallConst;
import org.aji.mall.exception.UserLoginException;
import org.aji.mall.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         User user=(User)request.getSession().getAttribute(MallConst.CURRENT_USER);
         if(user == null){
             throw new UserLoginException();
         }
        return true;
    }
}
