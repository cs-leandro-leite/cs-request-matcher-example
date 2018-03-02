package br.com.concrete.leite.csrequestmatcher.model;

import java.util.Objects;


public class Repository extends Model {

    private User owner;
    private String name;
    private String fullName;
    private String url;
    private String description;
    private int forks;
    private int stars;
    private int issues;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Repository repository = (Repository) other;
        return forks == repository.forks &&
                stars == repository.stars &&
                issues == repository.issues &&
                Objects.equals(owner, repository.owner) &&
                Objects.equals(name, repository.name) &&
                Objects.equals(fullName, repository.fullName) &&
                Objects.equals(url, repository.url) &&
                Objects.equals(description, repository.description);
    }
}
