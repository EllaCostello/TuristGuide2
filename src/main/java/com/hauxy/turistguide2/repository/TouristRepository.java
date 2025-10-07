package com.hauxy.turistguide2.repository;

import com.hauxy.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TouristRepository {
    private final TouristAttractionDAO dao;


    public TouristRepository(TouristAttractionDAO dao) {
        this.dao = dao;
    }

    public List<TouristAttraction> getTouristAttractions() {
        return dao.getTouristAttractions();
    }


    public void addTouristAttraction(TouristAttraction touristAttraction) {
        dao.addAttraction(touristAttraction);
    }

    public void updateTouristAttraction(String name, String updateDescription, String city, List<Tag> tags) {
        dao.updateTouristAttraction(name, updateDescription, city, tags);
    }

    public void removeTouristAttraction(String name) {
        dao.removeTouristAttraction(name);
    }


    public List<Tag> getAllTags() {
        return dao.getAllTags();
    }

    public List<String> getAllCities() {
        return dao.getAllCities();
    }

}
