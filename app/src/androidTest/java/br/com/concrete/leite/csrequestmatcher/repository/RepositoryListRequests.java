package br.com.concrete.leite.csrequestmatcher.repository;


import br.com.concrete.leite.csrequestmatcher.RequestInterceptor;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

public class RepositoryListRequests {
    private HttpUrl serverUrl;

    public RepositoryListRequests(RequestMatcherRule rule) {
        this.serverUrl = rule.url("/");
    }

    public HttpUrl getSearchRepositoriesRequest(String pageNumber){
        return new HttpUrl.Builder()
                .scheme("http")
                .host(serverUrl.host())
                .port(serverUrl.port())
                .addPathSegments("search/repositories")
                .addQueryParameter("q", "language:Java")
                .addQueryParameter("sort", "stars")
                .addQueryParameter("page", pageNumber)
                .build();
    }

    public Interceptor getRepositoryListInterceptor(){
        RequestInterceptor requestInterceptor = new RequestInterceptor();

        requestInterceptor.addRequest(getSearchRepositoriesRequest("1"));
        requestInterceptor.addRequest(getSearchRepositoriesRequest("2"));

        return requestInterceptor;
    }
}