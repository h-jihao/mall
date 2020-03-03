package org.aji.mall.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    MANAGER(0),
    CUSTOMER(1);

    Integer code;

    RoleEnum(Integer code) {
        this.code = code;
    }
}
