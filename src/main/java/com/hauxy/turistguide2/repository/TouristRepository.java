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
        attractions.add(new TouristAttraction("Tivoli", "Verdens ældste tivoli", "København", new ArrayList<>(Arrays.asList(Tag.BØRNEVENLIG, Tag.DYRT, Tag.FORLYSTELSEPARK))));
        attractions.add(new TouristAttraction("Den Lille Havfrue", "Verdens ældste havfrue", "København", new ArrayList<>(Arrays.asList(Tag.BØRNEVENLIG, Tag.GRATIS, Tag.KUNST))));
        attractions.add(new TouristAttraction("Operaen", "Verdens ældste opera", "København", new ArrayList<>(Arrays.asList(Tag.DYRT, Tag.KUNST, Tag.KONCERT))));

    }

    public List<TouristAttraction> getTouristAttractions() {
        return attractions;
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        attractions.add(touristAttraction);
    }

    public void updateTouristAttraction(String name, String updateDescription, List<Tag> tags) {
        for (TouristAttraction t : getTouristAttractions()) {
            if (name.equals(t.getName())) {
                t.setDescription(updateDescription);
                t.setTags(tags);
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


    public List<Tag> getTagsByName(String name) {
        for (TouristAttraction t : getTouristAttractions()) {
            if (name.equals(t.getName())) {
                return t.getTags();
            }

        }
        return null;
    }

    public List<Tag> getAllTags() {
        return List.of(Tag.values());
    }

    public List<String> getAllCities() {
        List<String> cities = new ArrayList<>(
                List.of(
                        "København",
                        "Odense",
                        "Aarhus",
                        "Aalborg",
                        "Kalundborg"
                )
        );

        return cities;
    }


}
