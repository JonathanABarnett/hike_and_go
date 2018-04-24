package com.alaythiaproductions.hike_and_go.service.implementation;

import com.alaythiaproductions.hike_and_go.model.UserShipping;
import com.alaythiaproductions.hike_and_go.repository.UserShippingRepository;
import com.alaythiaproductions.hike_and_go.service.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShippingServiceImpl implements UserShippingService {

    @Autowired
    private UserShippingRepository userShippingRepository;

    @Override
    public UserShipping findById(Long userShippingId) {
        return userShippingRepository.findOne(userShippingId);
    }

    @Override
    public void removeById(Long shippingId) {
        userShippingRepository.delete(shippingId);
    }
}
