package com.example.demo.entity;

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
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("address")
@ApiModel(value="Address对象", description="")
public class Address implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "ano", type = IdType.ID_WORKER)
    private String ano;

    @TableField("sheng")
    private String sheng;

    @TableField("shi")
    private String shi;

    @TableField("qu")
    private String qu;


}
