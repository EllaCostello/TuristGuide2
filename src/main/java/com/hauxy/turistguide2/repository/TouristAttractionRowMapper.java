package com.hauxy.turistguide2.repository;

import com.hauxy.turistguide2.model.TouristAttraction;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TouristAttractionRowMapper implements RowMapper<TouristAttraction> {


    private final TouristAttractionDAO dao;

    public TouristAttractionRowMapper(TouristAttractionDAO dao) {
        this.dao = dao;
    }

    @Override
    public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {
        TouristAttraction touristAttraction = new TouristAttraction();
        touristAttraction.setID(rs.getInt("attractionID"));
        touristAttraction.setName(rs.getString("name"));
        touristAttraction.setDescription(rs.getString("description"));

        int cityID = rs.getInt("cityID");

        String cityName = dao.getCityNameByID(cityID);
        touristAttraction.setCity(cityName);
        return touristAttraction;
    }




}
