package com.hauxy.turistguide2.service;

import com.hauxy.turistguide2.model.TouristAttraction;
import com.hauxy.turistguide2.repository.Tag;
import com.hauxy.turistguide2.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }
    public void populateTouristAttraction() {
        touristRepository.populateTouristAttraction();
    }

    public List<TouristAttraction> getTouristAttractions() {
        return touristRepository.getTouristAttractions();
    }

    public TouristAttraction getAttraction(String name) {
        for (TouristAttraction t : getTouristAttractions()) {
            if (name.equals(t.getName())) {
                return t;
            }
        }
        return null;
    }

    public void updateTouristAttraction(String name, String updateDescription) {
        touristRepository.updateTouristAttraction(name, updateDescription);
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristRepository.addTouristAttraction(touristAttraction);
    }
    public void removeTouristAttraction(String name) {
        touristRepository.removeTouristAttraction(name);
    }

    public List<Tag> getTagsByName(String name) {
        List<Tag> tags = touristRepository.getTagsByName(name);
        return tags;
    }



}
