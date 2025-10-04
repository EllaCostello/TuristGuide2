package com.hauxy.turistguide2.repository;

import com.hauxy.turistguide2.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TouristAttractionDAO {
    private final JdbcTemplate jdbc;
    private final TouristAttractionRowMapper touristRowMapper;

    public TouristAttractionDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc; // Spring injects JdbcTemplate
        this.touristRowMapper = new TouristAttractionRowMapper(this); // pass DAO
    }

    public List<TouristAttraction> getTouristAttractions() {

        List<TouristAttraction> touristAttractions;

        String sql = "SELECT attractions.attraction.attractionID, attractions.attraction.name, attractions.attraction.description, attractions.attraction.cityID FROM attractions.attraction ORDER BY attractions.attraction.attractionID ";
        touristAttractions = jdbc.query(sql, touristRowMapper);
        for (TouristAttraction ta : touristAttractions) {
            ta.setTags(getTagsByTouristAttractionName(ta.getName()));

        }

        return touristAttractions;
    }

    public void addAttraction(TouristAttraction touristAttraction) {


        String sql = "INSERT INTO attractions.attraction (name, description, cityID) VALUES (?, ?, ?)";
        jdbc.update(sql, touristAttraction.getName(), touristAttraction.getDescription(), getCityIDByName(touristAttraction.getCity()));
    }

    public void updateTouristAttraction(String name, String updateDescription, String city, List<Tag> tags) {
        String sql = "UPDATE attractions.attraction SET description = ?, cityID = ? WHERE name = ?;";
        int cityID = getCityIDByName(city);
        jdbc.update(sql,updateDescription, cityID, name);
        updateTags(tags, getAttractionIDByName(name));
    }

    public void removeTouristAttraction(String name) {
        String sqlDeleteAttraction = """
                DELETE FROM attractions.attraction
                WHERE name = ?;
                """;

        jdbc.update(sqlDeleteAttraction, name);
    }

    public int getAttractionIDByName(String name) {
        String sql = "SELECT attractionID FROM attraction WHERE name = ?";
        return jdbc.queryForObject(sql, Integer.class, name);
    }

    public String getCityNameByID(int ID) throws SQLException {
        String sqlGetCityByID = "SELECT attractions.city.cityID FROM attractions.city WHERE cityID = ?";
        String cityName = jdbc.queryForObject(sqlGetCityByID, String.class, ID);
        return cityName;
    }

    public int getCityIDByName(String name) {
        String sql = "SELECT attractions.city.cityID FROM attractions.city WHERE attractions.city.name = ?";
        return jdbc.queryForObject(sql, Integer.class, name);
    }

    public Tag getTagByID(int tagID) {
        String sql = "SELECT attractions.tag.name FROM attractions.tag WHERE tagID = ?";
        String tagName = jdbc.queryForObject(sql, String.class, tagID);
        return Tag.valueOf(tagName.toUpperCase());
    }

    public int getTagIDByName(String name) {
        String sql = "SELECT attractions.tag.tagID FROM attractions.tag WHERE name = ?";
        return jdbc.queryForObject(sql, Integer.class, name);
    }

    public List<Tag> getTagsByTouristAttractionName(String attractionName) {
        List<Tag> tags = new ArrayList<>();
        int attractionID = getAttractionIDByName(attractionName);
        String sql = "SELECT tagID FROM attraction_tag WHERE attractionID = ?";
        List<Integer> tagIDs = jdbc.query(sql, (rs, rowNum) -> rs.getInt("tagID"), attractionID);
        for (int id : tagIDs) {
            tags.add(getTagByID(id));
        }
        return tags;
    }


    public List<String> getAllCities() {
        String sql = """
                SELECT attractions.city.name
                FROM attractions.city
                """;

        List<String> cities = jdbc.query(
                sql,
                (rs, rowNum) -> rs.getString("name")
        );
        return cities;
    }

    public List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT attractions.attraction_tag.tagID FROM attractions.attraction_tag";
        List<Integer> tagIDs = jdbc.query(sql, (rs, rowNum) -> rs.getInt("tagID"));
        for (int id : tagIDs) {
            tags.add(getTagByID(id));
        }
        return tags;
    }
    public int getTagIDByAttractionID(int attractionID) {
        String sql = "SELECT attractions.attraction_tag.tagID FROM attractions.attraction_tag WHERE attractionID = ?";
        return jdbc.queryForObject(sql, Integer.class, attractionID);
    }

    public int getAttractionIDByTagID(int tagID) {
        String sql = "SELECT attractions.attraction_tag.attractionID FROM attractions.attraction_tag WHERE tagID = ?";
        return jdbc.queryForObject(sql, Integer.class, tagID);
    }

    public void updateTags(List<Tag> tags, int attractionID) {

        String sqlDeleteTags = "DELETE FROM attraction.attraction_tag WHERE attraction.attraction_tag.attractionId = ?";
        jdbc.update(sqlDeleteTags, attractionID);


        String sqlGetTagID = "SELECT attractions.tag.tagId FROM attractions.tag WHERE name = ?";
        String sqlInsertTag = "INSERT INTO attraction_tag (attractionId, tagId) VALUES (?, ?)";

        for (Tag tag : tags) {
            String tagName = tag.name();
            String newTagName = tagName.substring(0, 1).toUpperCase() + tagName.substring(1).toLowerCase();
            Integer tagID = jdbc.queryForObject(sqlGetTagID, new Object[]{newTagName}, Integer.class);
            jdbc.update(sqlInsertTag, attractionID, tagID);
        }
    }
}
