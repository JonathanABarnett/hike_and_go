package com.alaythiaproductions.hike_and_go.service.service;

import com.alaythiaproductions.hike_and_go.model.UserPayment;

public interface UserPaymentService {

   UserPayment findById(Long creditCardId);
}
