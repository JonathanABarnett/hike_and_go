package com.alaythiaproductions.hike_and_go.service.service;

import com.alaythiaproductions.hike_and_go.model.UserShipping;

public interface UserShippingService {

    UserShipping findById(Long userShippingId);

    void removeById(Long shippingId);
}
