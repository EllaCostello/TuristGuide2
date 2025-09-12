package com.hauxy.turistguide2.model;

import com.hauxy.turistguide2.repository.Tag;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String city;
    private List<Tag> tags;


    public TouristAttraction(String name, String description, String city, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    public TouristAttraction() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTags(Tag tag) {
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
}
