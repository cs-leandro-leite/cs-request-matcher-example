package br.com.concrete.leite.csrequestmatcher.injection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module
public class OkHttpModule {
    private static final int CACHE_SIZE_10_MB = 10 * 1024 * 1024;

    @Provides
    @Singleton
    Cache providesOkHttpCache(Application application) {
        return new Cache(application.getCacheDir(), CACHE_SIZE_10_MB);
    }

    @Provides
    @Singleton
    protected OkHttpClient providesOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder().cache(cache).build();
    }
}
