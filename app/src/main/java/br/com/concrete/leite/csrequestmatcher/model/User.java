package br.com.concrete.leite.csrequestmatcher.model;

import java.util.Objects;


public class User extends Model {

    private String username;
    private String name;
    private String avatarUrl;
    private String location;
    private String websiteUrl;
    private int amountRepositories;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public int getAmountRepositories() {
        return amountRepositories;
    }

    public void setAmountRepositories(int amountRepositories) {
        this.amountRepositories = amountRepositories;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        User user = (User) other;
        return amountRepositories == user.amountRepositories &&
                Objects.equals(username, user.username) &&
                Objects.equals(name, user.name) &&
                Objects.equals(avatarUrl, user.avatarUrl) &&
                Objects.equals(location, user.location) &&
                Objects.equals(websiteUrl, user.websiteUrl);
    }
}
