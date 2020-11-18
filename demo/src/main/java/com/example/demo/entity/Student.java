package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Liuer
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
//添加此注解会和easyExcel发生冲突，读数据时会出现空数据
//@Accessors(chain = true)
@TableName("student")
@ApiModel(value="Student对象", description="")
public class Student implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "Sno", type = IdType.ID_WORKER)
    @ExcelProperty(value="学生学号",index = 0)
    private String sno;

    @TableField("Sname")
    @ExcelProperty(value="学生姓名",index = 1)
    private String sname;

    @TableField("Ssex")
    @ExcelProperty(value = "性别",index = 2)
    private String ssex;

    @TableField("Sage")
    @ExcelProperty(value="年龄",index = 3)
    private Integer sage;

    @TableField("Sdept")
    @ExcelProperty(value="班级",index = 4)
    private String sdept;

    @TableField("PW")
    @ExcelProperty(value="登录密码",index = 5)
    private String PW;

    @TableField("addressId")
    @ExcelProperty(value = "家庭住址编号",index = 6)
    private String addressId;

//    private Address addressId;
}
