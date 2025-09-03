package com.hauxy.turistguide2.model;

import com.hauxy.turistguide2.repository.Tags;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private List<Tags> tags;


    public TouristAttraction(String name, String description) {
        this.name = name;
        this.description = description;
        this.tags = tags;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
}
