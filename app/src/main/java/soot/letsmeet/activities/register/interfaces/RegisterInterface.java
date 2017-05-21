package soot.letsmeet.activities.register.interfaces;

import android.support.annotation.Nullable;

import soot.letsmeet.customviews.ProgressCustomView;

/**
 * Created by Soot on 20/05/2017.
 */

public interface RegisterInterface {

    void onRegisterSuccess();

    void onRegistrationError();

    void passwordsNotMatch();

    void nameError();

    void surnameError();

    void emailError();

    void passwordError();

    void repeatPasswordError();



    void setProgresViewState(@ProgressCustomView.ProgressInterface.ProgresViewState int viewState, @Nullable String title, @Nullable Boolean mShowBlur);

    void showToast(String message);

}
