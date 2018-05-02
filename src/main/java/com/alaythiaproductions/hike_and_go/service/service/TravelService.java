package com.alaythiaproductions.hike_and_go.service.service;

import com.alaythiaproductions.hike_and_go.model.Travel;

import java.util.List;

public interface TravelService {

    Travel save(Travel travel);

    Travel findOne(Long id);

    List<Travel> findAll();
}
