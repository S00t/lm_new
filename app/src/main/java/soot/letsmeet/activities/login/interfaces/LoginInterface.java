package soot.letsmeet.activities.login.interfaces;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import soot.letsmeet.customviews.ProgressCustomView;

/**
 * Created by Soot on 02/05/2017.
 */

public interface LoginInterface {
    int STATE_LOGIN = 0;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATE_LOGIN})
    @interface PreloadViewState {
    }

    void onLoginSuccess();

    void onLoginError();

    void onNoAccountOffline();

    void setProgresViewState(@ProgressCustomView.ProgressInterface.ProgresViewState int viewState, @Nullable String title, @Nullable Boolean mShowBlur);

    void showToast(String message);
}