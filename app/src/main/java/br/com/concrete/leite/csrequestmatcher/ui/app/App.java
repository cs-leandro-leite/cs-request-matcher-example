package br.com.concrete.leite.csrequestmatcher.ui.app;

import android.app.Application;

import br.com.concrete.leite.csrequestmatcher.injection.ApplicationModule;
import br.com.concrete.leite.csrequestmatcher.injection.DaggerRepositoryListComponent;
import br.com.concrete.leite.csrequestmatcher.injection.OkHttpModule;
import br.com.concrete.leite.csrequestmatcher.injection.RepositoryListComponent;
import br.com.concrete.leite.csrequestmatcher.injection.RepositoryListModule;
import br.com.concrete.leite.csrequestmatcher.injection.RetrofitModule;


public class App extends Application {

    private RepositoryListComponent repositoryListComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        repositoryListComponent = DaggerRepositoryListComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .okHttpModule(new OkHttpModule())
                .retrofitModule(new RetrofitModule())
                .repositoryListModule(new RepositoryListModule())
                .build();
    }

    public RepositoryListComponent getRepositoryListComponent() {
        return repositoryListComponent;
    }

    public void setRepositoryListComponent(RepositoryListComponent repositoryListComponent) {
        this.repositoryListComponent = repositoryListComponent;
    }
}