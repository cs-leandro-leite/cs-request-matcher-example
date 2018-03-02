package br.com.concrete.leite.csrequestmatcher.data.model;

import com.google.gson.annotations.SerializedName;


public class UserDataModel extends DataModel{

    @SerializedName("login")
    private String username;

    @SerializedName("name")
    private String name;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("location")
    private String location;

    @SerializedName("blog")
    private String websiteUrl;

    @SerializedName("public_repos")
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
}
