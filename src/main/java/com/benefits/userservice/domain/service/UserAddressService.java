package com.benefits.userservice.domain.service;

import com.benefits.userservice.db.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserAddressService {
    private final UserAddressRepository userAddressRepository;


}
