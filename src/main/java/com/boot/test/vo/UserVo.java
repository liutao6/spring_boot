package com.boot.test.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by tao on 2017/2/14.
 */
@ApiModel(value = "用户vo")
public class UserVo {

    @NotNull(message = "姓名不能为空")
    @Size(min = 6, message = "长度最小6位")
    @ApiModelProperty(value = "姓名")
    private String name;

    @Min(value = 10)
    @ApiModelProperty(value = "年龄")
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
