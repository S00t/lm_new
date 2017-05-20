package soot.letsmeet.activities.login.interfaces;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import soot.letsmeet.customviews.ProgressCustomView;

/**
 * Created by Soot on 02/05/2017.
 */

public interface LoginInterface {
    int STATE_LOGIN = 0, STATE_CHANGE_PASSWD = 1, STATE_ACCOUNT_EXPIRED = 2, STATE_PASSWORD_EXPIRED = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATE_LOGIN, STATE_CHANGE_PASSWD, STATE_ACCOUNT_EXPIRED, STATE_PASSWORD_EXPIRED})
    @interface PreloadViewState {
    }


    void onLoginSuccess();
    void onLoginError();

    void onNoAccountOffline();

    void onUserAccountBlocked();
    void onChangePassError(@StringRes int message);

    void setProgresViewState(@ProgressCustomView.ProgressInterface.ProgresViewState int viewState, @Nullable String title, @Nullable Boolean mShowBlur);

    void showToast(String message);
}