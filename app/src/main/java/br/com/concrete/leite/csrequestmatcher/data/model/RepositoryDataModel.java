package br.com.concrete.leite.csrequestmatcher.data.model;

import com.google.gson.annotations.SerializedName;


public class RepositoryDataModel extends DataModel{

    @SerializedName("owner")
    private UserDataModel owner;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("url")
    private String url;

    @SerializedName("description")
    private String description;

    @SerializedName("forks_count")
    private int forks;

    @SerializedName("stargazers_count")
    private int stars;

    @SerializedName("open_issues_count")
    private int issues;

    public UserDataModel getOwner() {
        return owner;
    }

    public void setOwner(UserDataModel owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getIssues() {
        return issues;
    }

    public void setIssues(int issues) {
        this.issues = issues;
    }
}
