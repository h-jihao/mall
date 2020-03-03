package org.aji.mall.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    SERVER_ERROR(-1,"服务器错误"),

    SUCCESS(0,"成功"),

    PASSWORD_ERROR(1,"密码错误"),

    USERNAME_EXIST(2,"用户已存在"),

    PARAM_ERROR(3,"参数校验错误"),

    EMAIL_EXIST(4,"邮箱已被注册"),

    NEED_LOGIN(10,"用户尚未登录，请先登录"),

    USERNAME_OR_PASSWORD_ERROR(11,"用户名或者密码错误"),
    ;

    Integer code;
    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
