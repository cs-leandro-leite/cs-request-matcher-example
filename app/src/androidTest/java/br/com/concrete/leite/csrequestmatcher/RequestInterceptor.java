package br.com.concrete.leite.csrequestmatcher;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

public final class RequestInterceptor implements Interceptor {

    private Map<String, HttpUrl> requestUrlCache;

    public RequestInterceptor() {
        this.requestUrlCache = new HashMap<>();
    }

    @Override public okhttp3.Response intercept(@Nonnull Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl requestUrl = requestUrlCache.get(getKeyFrom(request.url()));

        request = request.newBuilder()
                .url(requestUrl)
                .build();

        return chain.proceed(request);
    }

    public void addRequest(HttpUrl requestUrl) {
        String requestKey = getKeyFrom(requestUrl);

        requestUrlCache.put(requestKey, requestUrl);
    }

    private String getKeyFrom(HttpUrl requestUrl) {
        return requestUrl.url().getPath() + "?" + requestUrl.url().getQuery();

    }
}

