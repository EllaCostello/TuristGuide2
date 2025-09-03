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
        ArrayList<Tag> tag1 = new ArrayList<>();
        tag1.add(Tag.NATUR);
        tag1.add(Tag.BØRNEVENLIG);
        tag1.add(Tag.GRATIS);


        attractions.add(new TouristAttraction("Tivoli", "Verdens ældste tivoli", List.of(Tag.BØRNEVENLIG, Tag.DYRT)));
        attractions.add(new TouristAttraction("Den Lille Havfrue", "Verdens ældste havfrue", List.of(Tag.BØRNEVENLIG, Tag.DYRT)));
        attractions.add(new TouristAttraction("Operaen", "Verdens ældste opera", List.of(Tag.BØRNEVENLIG, Tag.DYRT)));


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
        ArrayList<Tag> tagList = new ArrayList<>(Arrays.asList(Tag.values()));
            return tagList;
    }


}
