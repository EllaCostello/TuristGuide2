package com.hauxy.turistguide2.repository;

import com.hauxy.turistguide2.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Før at databasen skal virke, skal vi først oprette en ny database ud fra ER diagrammet i
//der ligger i en anden branch der hedder "docs-sql-scripts" derudover skal vi linke vores DEV_URL til den
// korrekte, og sætte password til host computerens password.
// der er ikke taget højde for prod endnu.

// husk password ændring i Env Variables


@Repository
public class TouristRepository {
    private final TouristAttractionDAO dao;

    // Constructor injection
    public TouristRepository(TouristAttractionDAO dao) {
        this.dao = dao; // Spring injects DAO
    }
    public void populateTouristAttraction() {
//        attractions.add(new TouristAttraction("Tivoli", "Verdens ældste tivoli", "København", new ArrayList<>(Arrays.asList(Tag.BØRNEVENLIG, Tag.DYRT, Tag.FORLYSTELSEPARK))));
//        attractions.add(new TouristAttraction("Den Lille Havfrue", "Verdens ældste havfrue", "København", new ArrayList<>(Arrays.asList(Tag.BØRNEVENLIG, Tag.GRATIS, Tag.KUNST))));
//        attractions.add(new TouristAttraction("Operaen", "Verdens ældste opera", "København", new ArrayList<>(Arrays.asList(Tag.DYRT, Tag.KUNST, Tag.KONCERT))));

    }

//    public List<TouristAttraction> getTouristAttractions() {
//        return attractions;
//    }


//    private final RowMapper<TouristAttraction> attractionRowMapper = (rs, rowNum) -> {
//        TouristAttraction attraction = new TouristAttraction();
//        attraction.setName(rs.getString("attraction_name"));
//        attraction.setDescription(rs.getString("attraction_description"));
//        attraction.setCity(rs.getString("city_name"));
//
//        String tagsString = rs.getString("tag_names");
//        List<Tag> tags = (tagsString == null || tagsString.isEmpty())
//                ? Collections.emptyList()
//                : Arrays.stream(tagsString.split(","))
//                .map(String::trim)
//                .map(String::toUpperCase)
//                .map(Tag::valueOf)
//                .toList();
//
//        attraction.setTags(tags);
//        return attraction;
//    };




    public List<TouristAttraction> getTouristAttractions() {
        return dao.getTouristAttractions();
    }


//    public void addTouristAttraction(TouristAttraction touristAttraction) {
//        attractions.add(touristAttraction);
//    }


    public void addTouristAttraction(TouristAttraction touristAttraction) {
        dao.addAttraction(touristAttraction);
    }


//    public void addTouristAttraction(TouristAttraction touristAttraction) {
//
//        String sqlGetCityID = """
//        SELECT cityId
//        FROM city
//        WHERE name = ?
//    """;
//
//        Integer cityID = jdbcTemplate.queryForObject(
//                sqlGetCityID,
//                new Object[]{touristAttraction.getCity()},
//                Integer.class
//        );
//
//        if (cityID == null) {
//            throw new IllegalArgumentException("City not found: " + touristAttraction.getCity());
//        }
//
//
//        String sqlInsertAttraction = """
//        INSERT INTO attraction (name, description, cityID)
//        VALUES (?, ?, ?)
//    """;
//
//        jdbcTemplate.update(
//                sqlInsertAttraction,
//                touristAttraction.getName(),
//                touristAttraction.getDescription(),
//                cityID
//        );
//
//
//        Integer attractionID = jdbcTemplate.queryForObject(
//                "SELECT LAST_INSERT_ID()",
//                Integer.class
//        );
//
//
//        if (touristAttraction.getTags() != null && !touristAttraction.getTags().isEmpty()) {
//            String sqlGetTagID = "SELECT tagId FROM tag WHERE name = ?";
//            String sqlInsertAttractionTag = "INSERT INTO attraction_tag (attractionId, tagId) VALUES (?, ?)";
//
//            for (Tag tag : touristAttraction.getTags()) {
//                Integer tagID = jdbcTemplate.queryForObject(
//                        sqlGetTagID,
//                        new Object[]{tag.name()},
//                        Integer.class
//                );
//                jdbcTemplate.update(sqlInsertAttractionTag, attractionID, tagID);
//            }
//        }
//    }


//    public void updateTouristAttraction(String name, String updateDescription, String city, List<Tag> tags) {
//        for (TouristAttraction t : getTouristAttractions()) {
//            if (name.equals(t.getName())) {
//                t.setDescription(updateDescription);
//                t.setCity(city);
//                t.setTags(tags);
//            }
//        }
//    }

    public void updateTouristAttraction(String name, String updateDescription, String city, List<Tag> tags) {
        dao.updateTouristAttraction(name, updateDescription, city, tags);
    }

//    public void updateTouristAttraction(String name, String updateDescription, String city, List<Tag> tags) {
//
//
//        String sqlGetCityID = "SELECT cityId FROM city WHERE name = ?";
//        Integer cityID = jdbcTemplate.queryForObject(sqlGetCityID, new Object[]{city}, Integer.class);
//
//        if (cityID == null) {
//            throw new IllegalArgumentException("City not found: " + city);
//        }
//
//
//        String sqlUpdateAttraction = """
//        UPDATE attraction
//        SET name = ?, description = ?, cityID = ?
//        WHERE name = ?
//    """;
//        jdbcTemplate.update(sqlUpdateAttraction, name, updateDescription, cityID, name);
//
//
//        String sqlGetAttractionID = "SELECT attractionId FROM attraction WHERE name = ?";
//        Integer attractionID = jdbcTemplate.queryForObject(sqlGetAttractionID, new Object[]{name}, Integer.class);
//
//
//        String sqlDeleteTags = "DELETE FROM attraction_tag WHERE attractionId = ?";
//        jdbcTemplate.update(sqlDeleteTags, attractionID);
//
//
//        String sqlGetTagID = "SELECT tagId FROM tag WHERE name = ?";
//        String sqlInsertTag = "INSERT INTO attraction_tag (attractionId, tagId) VALUES (?, ?)";
//
//        for (Tag tag : tags) {
//            Integer tagID = jdbcTemplate.queryForObject(sqlGetTagID, new Object[]{tag.name()}, Integer.class);
//            jdbcTemplate.update(sqlInsertTag, attractionID, tagID);
//        }
//    }


//    public void removeTouristAttraction(String name) {
//        for (TouristAttraction t : attractions) {
//            if (name.equals(t.getName())) {
//                attractions.remove(t);
//                break;
//            }
//        }
//    }



//    public void removeTouristAttraction(String name) {
//        String sqlDeleteAttraction = """
//                DELETE FROM attractions.attraction
//                WHERE name = ?;
//                """;
//
//        jdbcTemplate.update(sqlDeleteAttraction, name);
//    }
    public void removeTouristAttraction(String name) {
        dao.removeTouristAttraction(name);
    }


//    public List<Tag> getTagsByName(String name) {
//        for (TouristAttraction t : getTouristAttractions()) {
//            if (name.equals(t.getName())) {
//                return t.getTags();
//            }
//
//        }
//        return null;
//    }
    public List<Tag> getTagsByName(String name) {
        return dao.getTagsByTouristAttractionName(name);
    }

//    public List<Tag> getTagsByName(String name) {
//
//        List<Tag> tags = new ArrayList<>();
//        String sqlGetAttractionID = """
//                SELECT attractionID
//                FROM attractions.attraction
//                WHERE attraction.name = ?
//                """;
//
//        int attractionID = jdbcTemplate.queryForObject(
//                sqlGetAttractionID,
//                new Object[]{name},
//                Integer.class
//        );
//
//        String sqlGetAllTagIDs = "SELECT tagId FROM attractions.attraction_tag WHERE attractionId = ?";
//
//        List<Integer> tagIDs = jdbcTemplate.query(
//                sqlGetAllTagIDs,
//                new Object[]{attractionID},
//                (rs, rowNum) -> rs.getInt("tagID")
//        );
//
//        String inSql = tagIDs.stream()
//                .map(id -> "?")
//                .collect(Collectors.joining(", "));
//
//        String sqlGetTagsByID = """
//                SELECT name
//                FROM attractions.tag
//                WHERE tagID IN (%s)
//                """;
//
//        List<String> tagNames = jdbcTemplate.query(
//                sqlGetTagsByID,
//                tagIDs.toArray(),
//                (rs, rowNum) -> rs.getString("name")
//        );
//
//        List<Tag> finishedTags = tagNames.stream()
//                .map(finishedName -> Tag.valueOf(name.toUpperCase()))
//                .collect(Collectors.toList());
//
//
//        return finishedTags;
//
//    }




//    public List<Tag> getAllTags() {
//        return List.of(Tag.values());
//    }


    public List<Tag> getAllTags() {
        return dao.getAllTags();
    }
//    public List<Tag> getAllTags() {
//        String sql = """
//                SELECT name
//                FROM attractions.tag
//                """;
//
//        List<String> tagNames = jdbcTemplate.query(
//                sql,
//                (rs, rowNum) -> rs.getString("name")
//        );
//
//        List<Tag> finishedTags = tagNames.stream()
//                .map(finishedName -> Tag.valueOf(finishedName.toUpperCase()))
//                .collect(Collectors.toList());
//
//        return finishedTags;
//    }

//    public List<String> getAllCities() {
//
//        return new ArrayList<>(
//                List.of(
//                        "København",
//                        "Odense",
//                        "Aarhus",
//                        "Aalborg",
//                        "Kalundborg"
//                )
//        );
//    }


    public List<String> getAllCities() {
        return dao.getAllCities();
    }
//    public List<String> getAllCities() {
//        String sql = """
//                SELECT attractions.city.name
//                FROM attractions.city
//                """;
//
//        List<String> cities = jdbcTemplate.query(
//                sql,
//                (rs, rowNum) -> rs.getString("name")
//        );
//        return cities;
//    }



}
