package br.com.concrete.leite.csrequestmatcher.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.util.Log;

import java.util.List;

import br.com.concrete.leite.csrequestmatcher.data.mapper.RepositoryDataModelMapper;
import br.com.concrete.leite.csrequestmatcher.data.model.RepositoryDataModel;
import br.com.concrete.leite.csrequestmatcher.data.response.GetRepositoriesResponseData;
import br.com.concrete.leite.csrequestmatcher.data.service.RepositoryRetrofitService;
import br.com.concrete.leite.csrequestmatcher.model.Repository;
import br.com.concrete.leite.csrequestmatcher.model.ResponseData;
import br.com.concrete.leite.csrequestmatcher.model.AppendableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepositoryListViewModel extends ViewModel {

    public AppendableLiveData<List<Repository>> repositories = new AppendableLiveData<>();
    public Integer currentPage;

    private RepositoryRetrofitService service;


    public RepositoryListViewModel(Retrofit retrofit) {
        this.service = retrofit.create(RepositoryRetrofitService.class);;
        this.currentPage = 1;
    }

    public void clearRepositories(){
        repositories.getValue().data.clear();
    }

    public Repository getRepositoryAt(int position) {
        return repositories.getValue().data.get(position);
    }

    public void incrementPage(){
        currentPage += 1;
    }

    public void resetPage(){
        currentPage = 1;
    }

    public void getRepositoriesAtCurrentPage() {
        Call<GetRepositoriesResponseData> call = service.getRepositoriesAtPageNumber(currentPage);
        final RepositoryDataModelMapper repositoryDataMapper = new RepositoryDataModelMapper();

        Log.d("XXXXXXXASF23ER2", call.request().url().toString());

        call.enqueue(new Callback<GetRepositoriesResponseData>() {
            @Override
            public void onResponse(Call<GetRepositoriesResponseData> call, Response<GetRepositoriesResponseData> response) {
                List<RepositoryDataModel> repositoriesData = response.body().getRepositories();
                List<Repository> repositoriesPage = repositoryDataMapper.toModelList(repositoriesData);

                repositories.setAppendData(repositoriesPage);
            }

            @Override
            public void onFailure(Call<GetRepositoriesResponseData> call, Throwable t) {
                ResponseData<List<Repository>> error = ResponseData.error(t.getMessage(), null);

                repositories.setValue(error);
            }
        });
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final Retrofit retrofit;

        public Factory(Retrofit retrofit) {
            this.retrofit = retrofit;
        }

        @SuppressWarnings("unchecked")
        @Override
        public RepositoryListViewModel create(Class modelClass) {
            return new RepositoryListViewModel(retrofit);
        }
    }
//    public void getRepositoriesAtCurrentPage(){
//        RepositoryNetwork repositoryNetwork = new RepositoryNetwork();
//
//        WeakReference<AppendableLiveData<List<Repository>>> repositoriesWeakRef = new WeakReference<AppendableLiveData<List<Repository>>>(repositories);
//        repositoryNetwork.getRepositoriesAtPageNumber(currentPage, repositoriesWeakRef);
//    }
}
