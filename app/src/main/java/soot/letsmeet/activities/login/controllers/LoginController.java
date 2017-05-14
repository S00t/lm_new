package soot.letsmeet.activities.login.controllers;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;

import javax.inject.Inject;

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
        mOAuthWebServices.getAccessToken(TokenUtil.getBasicAuthorizationHeader(login, password),
                TokenUtil.buildTokenRequest(mOAuth2Config))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(tokenResponse -> {
                    generateToken(tokenResponse);
        }, throwable -> {
                    Timber.e(throwable);
                    loginError();
                });
    }

    private void loginError() {
        Timber.e("B³¹d logowania");
        if (isViewPresent()){
            getView().showToast("B³¹d logowania, spróbuj jeszcze raz");
        } else{
            Timber.e("B³¹d logowania, w dodatku coœ jeb³o");
        }
    }

    private Token generateToken(TokenResponse mTokenResponse){
        Token token;

        if(mTokenResponse != null){
            token = new Token(mTokenResponse.getExpiresIn(),mTokenResponse.getTokenType(),mTokenResponse.getRefreshToken(),mTokenResponse.getAccessToken());
        } else {
            Timber.e("TokenResponse null");
            return null;
        }
        return token;
    }
}

