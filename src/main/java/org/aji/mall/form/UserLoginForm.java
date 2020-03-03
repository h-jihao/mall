package org.aji.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 通常将view传递给后台，并且后台需要做参数校验的数据命名为form，这些实体类通常是某个pojo1类的一部分
 */
@Data
public class UserLoginForm {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
