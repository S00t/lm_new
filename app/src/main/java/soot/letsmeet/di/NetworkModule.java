package soot.letsmeet.di;


import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import soot.letsmeet.BuildConfig;
import soot.letsmeet.webservices.DomainWebService;
import soot.letsmeet.webservices.OAuthWebServices;

@Module
public class NetworkModule {
    private static final String sEndPoint = BuildConfig.BASE_URL;
    private static final int sConnectTimeOut = 30;  // SECONDS
    private static final int sReadTimeOut = 30; //SECONDS

    @Provides
    @Singleton
    OkHttpClient providesOKClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(sConnectTimeOut, TimeUnit.SECONDS)
                .readTimeout(sReadTimeOut, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofi(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(sEndPoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    DomainWebService providesDomainWebService(Retrofit retrofit) {
        return retrofit.create(DomainWebService.class);
    }


    @Provides
    @Singleton
    OAuthWebServices providesOAuthWebSerwices(Retrofit retrofit) {
        return retrofit.create(OAuthWebServices.class);
    }

//    @Provides
//    @Singleton
//    LoginWebService providesLoginWebService(Retrofit retrofit) {
//        return retrofit.create(LoginWebService.class);
//    }
//
//
//
//    @Provides
//    @Singleton
//    LoggerWebService providesLoggerWebService(Retrofit retrofit){
//        return retrofit.create(LoggerWebService.class);
//    }
}
