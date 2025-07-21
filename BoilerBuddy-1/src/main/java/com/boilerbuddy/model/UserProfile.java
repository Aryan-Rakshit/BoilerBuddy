package com.boilerbuddy.model;

import java.util.List;

public class UserProfile {
    private String uid;
    private String name;
    private List<String> interests;

    public UserProfile() {}

    public UserProfile(String uid, String name, List<String> interests) {
        this.uid = uid;
        this.name = name;
        this.interests = interests;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
} 