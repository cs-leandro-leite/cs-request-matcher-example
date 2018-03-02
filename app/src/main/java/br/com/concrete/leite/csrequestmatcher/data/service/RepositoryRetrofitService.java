package br.com.concrete.leite.csrequestmatcher.data.service;


import java.util.List;

import br.com.concrete.leite.csrequestmatcher.data.model.RepositoryDataModel;
import br.com.concrete.leite.csrequestmatcher.data.response.GetRepositoriesResponseData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RepositoryRetrofitService {

    @GET("search/repositories?q=language:Java&sort=stars")
    Call<GetRepositoriesResponseData> getRepositoriesAtPageNumber(@Query("page") int pageNumber);

    @GET("users/{username}/repos?")
    Call<List<RepositoryDataModel>>
        getUserRepositoriesAtPageNumber(@Path("username") String username, @Query("page") int pageNumber);
}
