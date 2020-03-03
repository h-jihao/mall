package org.aji.mall.exception;

import org.aji.mall.enums.ResponseEnum;
import org.aji.mall.vo.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseVo handler(RuntimeException e){
        return ResponseVo.error(ResponseEnum.SERVER_ERROR,e.getMessage());
    }

    @ExceptionHandler(UserLoginException.class)
    @ResponseBody
    public ResponseVo UserLoginHandler(){
        return ResponseVo.error(ResponseEnum.NEED_LOGIN);
    }
}
