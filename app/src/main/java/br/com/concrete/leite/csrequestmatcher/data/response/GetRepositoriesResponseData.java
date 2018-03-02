package br.com.concrete.leite.csrequestmatcher.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.concrete.leite.csrequestmatcher.data.model.RepositoryDataModel;


public class GetRepositoriesResponseData {
    @SerializedName("items")
    private List<RepositoryDataModel> repositories;

    public List<RepositoryDataModel> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<RepositoryDataModel> repositories) {
        this.repositories = repositories;
    }
}
