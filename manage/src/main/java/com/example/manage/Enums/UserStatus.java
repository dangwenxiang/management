package com.example.manage.Enums;

/**
 * @Description 用户状态枚举类
 * @Date 2020/4/13 10:25
 * @Created by dwx
 */
public enum UserStatus {
    YES("正常",0),
    NO("暂停使用",1);
    private Integer value;
    private String desc;

    UserStatus(String desc, Integer value) {
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
