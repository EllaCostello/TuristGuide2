package com.hauxy.turistguide2.model;


public class Tag {
    private int tagID;
    private String name;

    public Tag() {}

    public Tag(int tagID, String name) {
        this.tagID = tagID;
        this.name = name;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
