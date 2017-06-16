package com.example.service.impl;

import com.example.model.Address;
import com.example.repository.AddressRepository;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Iterable<Address> getAll() {
        return addressRepository.findAll();
    }
}
