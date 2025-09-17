package com.hauxy.turistguide2.service;

import com.hauxy.turistguide2.model.TouristAttraction;
import com.hauxy.turistguide2.repository.Tag;
import com.hauxy.turistguide2.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    final TouristRepository touristRepository;

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

    public void updateTouristAttraction(TouristAttraction touristAttraction) {
        touristRepository.updateTouristAttraction(touristAttraction.getName(), touristAttraction.getDescription(), touristAttraction.getCity(), touristAttraction.getTags());
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristRepository.addTouristAttraction(touristAttraction);
    }
    public void removeTouristAttraction(String name) {
        touristRepository.removeTouristAttraction(name);
    }

    public List<Tag> getTagsByName(String name) {
        return touristRepository.getTagsByName(name);
    }

    public List<Tag> getAllTags() {
        return touristRepository.getAllTags();
    }

    public List<String> getAllCities() {
        return touristRepository.getAllCities();
    }




}
