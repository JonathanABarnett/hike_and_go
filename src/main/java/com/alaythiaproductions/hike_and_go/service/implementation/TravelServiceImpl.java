package com.alaythiaproductions.hike_and_go.service.implementation;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.Travel;
import com.alaythiaproductions.hike_and_go.repository.TravelRepository;
import com.alaythiaproductions.hike_and_go.service.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelRepository travelRepository;

    @Override
    public Travel save(Travel travel) {
        return travelRepository.save(travel);
    }

    @Override
    public Travel findOne(Long id) {
        return travelRepository.findOne(id);
    }

    @Override
    public List<Travel> findAll() {
        List<Travel> travelList =  travelRepository.findAll();
        List<Travel> activeTravelList = new ArrayList<>();

        for (Travel travel : travelList) {
            if (travel.isActive()) {
                activeTravelList.add(travel);
            }
        }
        return activeTravelList;
    }

    @Override
    public void removeOne(Long id) {
        travelRepository.delete(id);
    }
}
