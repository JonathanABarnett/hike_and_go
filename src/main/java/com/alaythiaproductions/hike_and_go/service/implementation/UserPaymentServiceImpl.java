package com.alaythiaproductions.hike_and_go.service.implementation;

import com.alaythiaproductions.hike_and_go.model.UserPayment;
import com.alaythiaproductions.hike_and_go.repository.UserPaymentRepository;
import com.alaythiaproductions.hike_and_go.service.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Override
    public UserPayment findById(Long creditCardId) {
        return userPaymentRepository.findOne(creditCardId);
    }
}
