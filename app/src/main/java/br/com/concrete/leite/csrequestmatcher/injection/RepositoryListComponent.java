package br.com.concrete.leite.csrequestmatcher.injection;


import javax.inject.Singleton;

import br.com.concrete.leite.csrequestmatcher.ui.RepositoryListActivity;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, OkHttpModule.class, RetrofitModule.class, RepositoryListModule.class})
public interface RepositoryListComponent {
    void inject(RepositoryListActivity target);
}