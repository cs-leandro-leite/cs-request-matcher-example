package br.com.concrete.leite.csrequestmatcher;


import br.com.concrete.leite.csrequestmatcher.injection.ApplicationModule;
import br.com.concrete.leite.csrequestmatcher.injection.DaggerRepositoryListComponent;
import br.com.concrete.leite.csrequestmatcher.injection.OkHttpModule;
import br.com.concrete.leite.csrequestmatcher.injection.RepositoryListComponent;
import br.com.concrete.leite.csrequestmatcher.injection.RepositoryListModule;
import br.com.concrete.leite.csrequestmatcher.injection.RetrofitModule;
import br.com.concrete.leite.csrequestmatcher.ui.app.App;
import dagger.Module;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestApp extends App {

    private Interceptor interceptor;

    public void setupInstrumentedTestInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;

        this.setRepositoryListComponent(createTestRepositoryListComponent());
    }

    private RepositoryListComponent createTestRepositoryListComponent() {
        return DaggerRepositoryListComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .okHttpModule(new TestOkHttpModule())
                .retrofitModule(new RetrofitTestModule())
                .repositoryListModule(new RepositoryListModule())
                .build();
    }

    @Module
    private class TestOkHttpModule extends OkHttpModule {

        @Override
        protected OkHttpClient providesOkHttpClient(Cache cache) {
            return new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
        }
    }

    @Module
    private class RetrofitTestModule extends RetrofitModule {

        @Override
        protected Retrofit providesRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
            return new Retrofit.Builder()
                    .baseUrl("http://dummy.com/")
                    .addConverterFactory(gsonConverterFactory)
                    .client(okHttpClient)
                    .build();
        }
    }
}
