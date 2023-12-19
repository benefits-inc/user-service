package com.benefits.userservice.domain.service;

import com.benefits.userservice.domain.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserAddressService {
    private final UserAddressRepository userAddressRepository;

    public void test(){
    }
}
