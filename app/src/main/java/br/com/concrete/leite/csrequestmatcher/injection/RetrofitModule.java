package br.com.concrete.leite.csrequestmatcher.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leandro on 26/12/2017.
 */

@Module
public class RetrofitModule {

    private static final String BASE_URL = "https://api.github.com/";
//    private static final int CACHE_SIZE_10_MB = 10 * 1024 * 1024;

//    @Provides
//    @Singleton
//    Cache providesOkHttpCache(Application application) {
//        return new Cache(application.getCacheDir(), CACHE_SIZE_10_MB);
//    }
//
//    @Provides
//    @Singleton
//    OkHttpClient providesOkHttpClient(Cache cache) {
//        return new OkHttpClient.Builder().cache(cache).build();
//    }

    @Provides
    @Singleton
    GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    protected Retrofit providesRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }
}
