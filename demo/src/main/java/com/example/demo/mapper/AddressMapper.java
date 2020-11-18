package com.example.demo.mapper;

import com.example.demo.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Liuer
 * @since 2020-11-06
 */
public interface AddressMapper extends BaseMapper<Address> {
    Address selectAddress(String ano);
}
