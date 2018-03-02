package br.com.concrete.leite.csrequestmatcher.injection;


import android.arch.lifecycle.ViewModelProvider;

import br.com.concrete.leite.csrequestmatcher.viewmodel.RepositoryListViewModel;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RepositoryListModule {

    @Provides
    ViewModelProvider.Factory providesViewModelFactory(Retrofit retrofit) {
        return new RepositoryListViewModel.Factory(retrofit);
    }
}