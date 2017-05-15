package soot.letsmeet.activities.login.controllers;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import soot.letsmeet.BuildConfig;
import soot.letsmeet.activities.BaseController;
import soot.letsmeet.activities.login.interfaces.LoginInterface;
import soot.letsmeet.models.OAuth2Config;
import soot.letsmeet.models.Token;
import soot.letsmeet.sqlite.repository.LoggerRepository;
import soot.letsmeet.sqlite.repository.TokenRepository;
import soot.letsmeet.utils.ConnectivityUtil;
import soot.letsmeet.utils.TokenUtil;
import soot.letsmeet.webservices.OAuthWebServices;
import soot.letsmeet.webservices.responses.TokenResponse;
import timber.log.Timber;

/**
 * Created by Soot on 02/05/2017.
 */

public class LoginController extends BaseController<LoginInterface> {

    @Inject
    protected ConnectivityUtil mConnectivityUtil;
    @Inject
    protected LoggerRepository mLoggerRepository;
    @Inject
    protected OAuthWebServices mOAuthWebServices;
    @Inject
    protected TokenRepository mTokenRepository;

    private HandlerThread mHandlerThread;
    private Token mToken;
    @Inject
    public LoginController() {
        super();
        mHandlerThread = new HandlerThread("PasswordProcessing");
        mHandlerThread.start();
    }


    @Override
    public void onCreate(LoginInterface mView) {
        super.onCreate(mView);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandlerThread != null) mHandlerThread.quit();
    }

    public boolean isNetworkAvaible() {
        return mConnectivityUtil.isNetworkAvailable();
    }


    private Handler getNewHandler() {
        return new Handler(mHandlerThread.getLooper());
    }


    private void loginOffline(@Nullable String login, @Nullable String pasword) {
        Timber.v("Logowanie Offline");
        Timber.i("Offline login");
    }


    public void login(String login, String password) {
        OAuth2Config mOAuth2Config = new OAuth2Config.OAuth2ConfigBuilder(login, password, BuildConfig.c_id, BuildConfig.ss).grantType("password").build();
        mOAuthWebServices.getAccessToken(TokenUtil.buildTokenRequest(mOAuth2Config))
                .subscribeOn(Schedulers.newThread())
                .onErrorResumeNext(throwable -> {
                    if(throwable instanceof HttpException) {
                        HttpException exception = (HttpException) throwable;
                        switch (exception.code()) {
                            case 401:

                                break;
                        }
                    }
                    return Observable.empty();
                }).observeOn(AndroidSchedulers.mainThread()).
                subscribe(tokenResponse -> {
                    storeToken(tokenResponse);
        }, throwable -> {
                    Timber.e(throwable);
                    loginError();
                });
    }

    private void loginError() {
        Timber.e("Błąd logowania");
        if (isViewPresent()){
            getView().showToast("Błąd logowania, spróbuj jeszcze raz");
        } else{
            Timber.e("Błąd logowania, w dodatku coś jebło");
        }
    }

    private void storeToken(TokenResponse mTokenResponse){

        if(mTokenResponse != null && mTokenResponse.getAccessToken()!=null && !mTokenResponse.getAccessToken().isEmpty()){
            mToken = new Token(mTokenResponse.getAccessToken(),mTokenResponse.getExpiresIn(),mTokenResponse.getTokenType(),mTokenResponse.getRefreshToken());
            boolean createOrUpdateSucess = mTokenRepository.createOrUpdate(mToken);

            if (!createOrUpdateSucess) {
                Timber.e("Error creating or updating token in databse");
            }
            //w tym miejscu użytkownik powinien być widoczny w bazie
            if (mTokenRepository.findToken() == null) {
                Timber.e("No token found after succesful login and tries to save in database");
            }
        } else {
            Timber.e("TokenResponse null or something bad happen");

        }

    }
}

