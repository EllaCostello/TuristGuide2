package com.hauxy.turistguide2.repository;

import com.hauxy.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TouristRepository {
    List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        populateTouristAttraction();
    }

    public void populateTouristAttraction() {
        attractions.add(new TouristAttraction("Tivoli", "Verdens ældste tivoli"));
        attractions.add(new TouristAttraction("Den Lille Havfrue", "Verdens ældste havfrue"));
        attractions.add(new TouristAttraction("Operaen", "Verdens ældste opera"));


    }

    public List<TouristAttraction> getTouristAttractions() {
        return attractions;
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        attractions.add(touristAttraction);
    }

    public void updateTouristAttraction(String name, String updateDescription) {
        for (TouristAttraction t : getTouristAttractions()) {
            if (name.equals(t.getName())) {
                t.setDescription(updateDescription);
            }
        }
    }


    public void removeTouristAttraction(String name) {
        for (TouristAttraction t : attractions) {
            if (name.equals(t.getName())) {
                attractions.remove(t);
            }
        }
    }

    public List getTags() {
        ArrayList<Tags> tagsList = new ArrayList<>(Arrays.asList(Tags.values()));
            return tagsList;
    }


}
