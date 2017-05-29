package soot.letsmeet.activities.login.controllers;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import soot.letsmeet.BuildConfig;
import soot.letsmeet.activities.BaseController;
import soot.letsmeet.activities.login.interfaces.LoginInterface;
import soot.letsmeet.customviews.ProgressCustomView;
import soot.letsmeet.models.Account;
import soot.letsmeet.models.OAuth2Config;
import soot.letsmeet.models.Token;
import soot.letsmeet.sqlite.repository.AccountRepository;
import soot.letsmeet.sqlite.repository.LoggerRepository;
import soot.letsmeet.sqlite.repository.TokenRepository;
import soot.letsmeet.utils.ConnectivityUtil;
import soot.letsmeet.utils.LoginUtils;
import soot.letsmeet.utils.SnackBarUtils;
import soot.letsmeet.utils.TimeUtil;
import soot.letsmeet.utils.TokenUtil;
import soot.letsmeet.webservices.LoginWebServices;
import soot.letsmeet.webservices.OAuthWebServices;
import soot.letsmeet.webservices.responses.AccountResponse;
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
    protected LoginWebServices mLoginWebServices;
    @Inject
    protected TokenRepository mTokenRepository;
    @Inject
    protected AccountRepository mAccountRepository;
    @Inject
    protected SnackBarUtils mSnackBarUtils;

    private HandlerThread mHandlerThread;
    private Token mToken;
    private Account mAccount;
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

    public Snackbar showSnackbar(Context context, View parent, String message){
        return mSnackBarUtils.makeSnackBar(context, parent, message);
    }


    private Handler getNewHandler() {
        return new Handler(mHandlerThread.getLooper());
    }


    private void loginOffline(@Nullable String login, @Nullable String pasword) {
        Timber.v("Logowanie Offline");
        Timber.i("Offline login");
    }


    public void login(@Nullable String login,@Nullable String password) {

        Timber.v( "login in progres");
//        checkUserBlocokade();
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            Timber.i("puste pola");
            boolean noLogin = login == null || login.isEmpty();
            boolean noPasswd = password == null || password.isEmpty();
            if (noLogin) Timber.v( "Login is missing");
            if (noPasswd) Timber.v( "Password is missing");
            if (isViewPresent()){
                getView().setProgresViewState(ProgressCustomView.ProgressInterface.STATE_NORMAL,null,null);
                getView().onLoginError();}
            return;
        }
        OAuth2Config mOAuth2Config = new OAuth2Config.OAuth2ConfigBuilder(login, password, BuildConfig.c_id, BuildConfig.ss).grantType("password").build();
        mOAuthWebServices.getAccessToken(TokenUtil.buildTokenRequest(mOAuth2Config))
                .subscribeOn(Schedulers.newThread())
                .onErrorResumeNext(throwable -> {
                    if(throwable instanceof HttpException) {
                        HttpException exception = (HttpException) throwable;
                        switch (exception.code()) {
                            case 401:
                                Timber.e("Blad 401 " + exception.message() + " login: "+ login + " password: " + password);
                                if(isViewPresent()) {
                                    getView().setProgresViewState(ProgressCustomView.ProgressInterface.STATE_NORMAL, null, null);
                                    getView().onLoginError();
                                }
                                break;
                        }
                    }
                    return Observable.empty();
                }).observeOn(AndroidSchedulers.mainThread()).
                subscribe(tokenResponse -> {
                    storeToken(tokenResponse);
                    getAccount();
        }, throwable -> {
                    Timber.e(throwable);
                    if(isViewPresent()) {
                        getView().setProgresViewState(ProgressCustomView.ProgressInterface.STATE_NORMAL, null, null);
                        getView().onLoginError();
                    }
                });
    }

    private void getAccount() {

        Token mAccessToken = mTokenRepository.findToken();
        if(mAccessToken.getmExpiresAt() >= TimeUtil.getLocalMillis()) {
            mLoginWebServices.login(LoginUtils.getAuthorizationHeaderForAccessToken(mAccessToken.getmAccessToken()))
                    .subscribeOn(Schedulers.newThread())
                    .onErrorResumeNext(throwable -> {
                        if (throwable instanceof HttpException) {
                            HttpException exception = (HttpException) throwable;
                            Timber.e("Blad logowania!! " + exception.message());
                            switch (exception.code()) {
                                case 401:
                                    Timber.e("Blad 401 " + exception.message());
                                    if (isViewPresent()) {
                                        getView().setProgresViewState(ProgressCustomView.ProgressInterface.STATE_NORMAL, null, null);
                                        getView().onLoginError();
                                    }
                                    break;
                            }
                        }
                        return Observable.empty();
                    }).observeOn(AndroidSchedulers.mainThread()).
                    subscribe(accountResponse -> {
                        Timber.i("POPRAWNY EMAIL" + " " + accountResponse.getEmail());
                        storeAccount(accountResponse);
                        boolean isView = isViewPresent();
                        if (isView){
                            getView().setProgresViewState(ProgressCustomView.ProgressInterface.STATE_NORMAL, null, null);
                            getView().onLoginSuccess();
                        }else{
                            Timber.e("WWTTFF");
                        }
                    }, throwable -> {
                        Timber.e(throwable);
                        if (isViewPresent()){
                            if(isViewPresent()) {
                                getView().setProgresViewState(ProgressCustomView.ProgressInterface.STATE_NORMAL, null, null);
                                getView().onLoginError();
                            }
                        }

                    });
        }else {

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

    private void storeAccount(AccountResponse mAccountResponse){
        if(mAccountResponse != null && mAccountResponse.getEmail()!=null && !mAccountResponse.getEmail().isEmpty()){
            mAccount = new Account(mAccountResponse.getEmail(),
                    mAccountResponse.getNickName(),
                    mAccountResponse.getFullName(),
                    mAccountResponse.getPhoneNumber(),
                    mAccountResponse.getShowName(),
                    mAccountResponse.getGravatarUrl());
            boolean createOrUpdateSucess = mAccountRepository.createOrUpdate(mAccount);

            if (!createOrUpdateSucess) {
                Timber.e("Error creating or updating account in databse");
            }else {
                Timber.i("Account stored " + mAccount.toString());

            }
            //w tym miejscu użytkownik powinien być widoczny w bazie
            if (mTokenRepository.findToken() == null) {
                Timber.e("No account found after succesful login and tries to save in database");
            }
        } else {
            Timber.e("AccountResponse null or something bad happen");

        }

    }


}

