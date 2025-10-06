package com.hauxy.turistguide2.repository;

import com.hauxy.turistguide2.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class TouristRepository {
    final List<TouristAttraction> attractions = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;


    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    public TouristRepository(JdbcTemplate jdbcTemplate) {
        populateTouristAttraction();
        this.jdbcTemplate = jdbcTemplate;
    }

    public void populateTouristAttraction() {
//        attractions.add(new TouristAttraction("Tivoli", "Verdens ældste tivoli", "København", new ArrayList<>(Arrays.asList(Tag.BØRNEVENLIG, Tag.DYRT, Tag.FORLYSTELSEPARK))));
//        attractions.add(new TouristAttraction("Den Lille Havfrue", "Verdens ældste havfrue", "København", new ArrayList<>(Arrays.asList(Tag.BØRNEVENLIG, Tag.GRATIS, Tag.KUNST))));
//        attractions.add(new TouristAttraction("Operaen", "Verdens ældste opera", "København", new ArrayList<>(Arrays.asList(Tag.DYRT, Tag.KUNST, Tag.KONCERT))));

    }

//    public List<TouristAttraction> getTouristAttractions() {
//        return attractions;
//    }


    private final RowMapper<TouristAttraction> attractionRowMapper = (rs, rowNum) -> {
        TouristAttraction touristAttraction = new TouristAttraction();
        touristAttraction.setName(rs.getString("attractions.name"));
        touristAttraction.setDescription(rs.getString("attractions.description"));
        touristAttraction.setCity(rs.getString("city.name"));

        String tagsString = rs.getString("tags");
        if (tagsString != null && !tagsString.isEmpty()) {
            List<Tag> tags = Arrays.stream(tagsString.split(","))
                    .map(String::trim)
                    .map(Tag::valueOf)
                    .toList();
            touristAttraction.setTags(tags);
        } else {
            touristAttraction.setTags(Collections.emptyList());
        }

        return touristAttraction;
    };




    public List<TouristAttraction> getTouristAttractions() {
        String sql = """
        SELECT 
            a.attractionId,
            a.name AS attraction.name,
            a.description,
            c.name AS city.name,
            GROUP_CONCAT(t.name) AS tags
        FROM attraction a
        JOIN city c ON c.cityId = a.cityID
        LEFT JOIN attraction_tag at ON a.attractionId = at.attractionId
        LEFT JOIN tag t ON at.tagId = t.tagId
        GROUP BY a.attractionId, a.name, a.description, c.name;
        """;

        return jdbcTemplate.query(sql, attractionRowMapper);
    }


//    public void addTouristAttraction(TouristAttraction touristAttraction) {
//        attractions.add(touristAttraction);
//    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        String sqlInsert = """
                INSERT INTO attractions.attraction (name, description, cityID)
                VALUES (?, ?, ?)
                """;

        String sqlGetCityID = """
                SELECT * 
                FROM attractions.city
                WHERE name = ?
               
                """;
        int cityID =  jdbcTemplate.queryForObject(
                sqlGetCityID,
                new Object[]{touristAttraction.getCity()},
                Integer.class
        );

        jdbcTemplate.update(
                sqlInsert,
                touristAttraction.getName(),
                touristAttraction.getDescription(),
                cityID
        );
    }

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



        //Find city ID
        String sqlGetCityID = """
                SELECT cityId
                FROM attractions.city
                WHERE name = ?
                """;



        int cityID = jdbcTemplate.queryForObject(
                sqlGetCityID,
                new Object[]{city},
                Integer.class
        );



        // opdater attraction ud efter at have fundet cityID
        String sqlUpdateAttraction = """
                UPDATE attractions.attraction
                SET attraction.name = ?, attraction.description = ?, city.id = ?
                WHERE attraction.name = ?
                """;

        jdbcTemplate.update(
                sqlUpdateAttraction,
                name,
                updateDescription,
                cityID
        );


        // find AttractionID
        String sqlGetAttractionID = """
                SELECT attractionID
                FROM attractions.attraction
                WHERE attraction.name = ?
                """;

        int attractionID = jdbcTemplate.queryForObject(
                sqlGetAttractionID,
                new Object[]{name},
                Integer.class
        );


        //Slet tags der har attractionID
        String sqlDeleteTags = """
            DELETE FROM attractions.tag
            WHERE attractionId = ?
            """;

        jdbcTemplate.update(sqlDeleteTags, attractionID);



        // Find tagID
        String sqlGetTagID = """
                SELECT attractions.tag.tagID
                FROM attractions.tag
                WHERE attractions.id = ?            
                """;
        int tagID = jdbcTemplate.queryForObject(
                sqlGetTagID,
                new Object[]{attractionID},
                Integer.class
        );


        // Tilføj nye tags med attractionID per tag
        String sqlInsertTag = """
            INSERT INTO attractions.attraction_tags (attractionId, tagId)
            VALUES (?, ?)
            """;
        for (Tag tag : tags) {
            jdbcTemplate.update(sqlInsertTag, attractionID, tagID);
        }
    }


//    public void removeTouristAttraction(String name) {
//        for (TouristAttraction t : attractions) {
//            if (name.equals(t.getName())) {
//                attractions.remove(t);
//                break;
//            }
//        }
//    }

    public void removeTouristAttraction(String name) {
        String sqlDeleteAttraction = """
                DELETE FROM attractions.attraction
                WHERE name = ?;
                """;

        jdbcTemplate.update(sqlDeleteAttraction, name);
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

        List<Tag> tags = new ArrayList<>();
        String sqlGetAttractionID = """
                SELECT attractionID
                FROM attractions.attraction
                WHERE attraction.name = ?
                """;

        int attractionID = jdbcTemplate.queryForObject(
                sqlGetAttractionID,
                new Object[]{name},
                Integer.class
        );

        String sqlGetAllTagIDs = "SELECT tagId FROM attractions.attraction_tag WHERE attractionId = ?";

        List<Integer> tagIDs = jdbcTemplate.query(
                sqlGetAllTagIDs,
                new Object[]{attractionID},
                (rs, rowNum) -> rs.getInt("tagID")
        );

        String sqlGetTagsByID = """
                SELECT attractions.tag.name
                FROM attractions.tag
                WHERE tagID IN ?
                """;

        // lav videre her
        return tags;

    }




    public List<Tag> getAllTags() {
        return List.of(Tag.values());
    }

    public List<String> getAllCities() {

        return new ArrayList<>(
                List.of(
                        "København",
                        "Odense",
                        "Aarhus",
                        "Aalborg",
                        "Kalundborg"
                )
        );
    }


}
