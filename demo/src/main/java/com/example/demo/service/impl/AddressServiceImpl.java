package com.example.demo.service.impl;

import com.example.demo.entity.Address;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liuer
 * @since 2020-11-06
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Autowired
    AddressMapper addressMapper;
    @Override
    public String selectAddress(Address address) {
        StringBuffer addressStr = new StringBuffer();
        addressStr.append(address.getSheng()).append(address.getShi()).append(address.getQu());
        return addressStr+"";
    }

    @Override
    public Address selectAddress(String id) {
        Address address = addressMapper.selectAddress(id);
        return address;
    }
}
