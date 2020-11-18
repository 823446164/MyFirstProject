package com.example.demo.service;

import com.example.demo.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Liuer
 * @since 2020-11-06
 */
public interface AddressService extends IService<Address> {
    String selectAddress(Address address);
    Address selectAddress(String id);
}
