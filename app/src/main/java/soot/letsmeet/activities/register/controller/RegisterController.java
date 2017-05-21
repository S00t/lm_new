package soot.letsmeet.activities.register.controller;

import android.os.HandlerThread;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import soot.letsmeet.activities.BaseController;
import soot.letsmeet.activities.register.interfaces.RegisterInterface;
import timber.log.Timber;

/**
 * Created by Soot on 13/05/2017.
 */

public class RegisterController extends BaseController<RegisterInterface> {
    private HandlerThread mHandlerThread;

    @Inject
    public RegisterController() {
        super();
        mHandlerThread = new HandlerThread("RegistrationProcessing");
        mHandlerThread.start();
    }

    @Override
    public void onCreate(RegisterInterface mView) {
        super.onCreate(mView);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandlerThread != null) mHandlerThread.quit();
    }


    public static boolean validateEmail(String email) {
        return email.matches(".+@.+\\.[a-z]+");
    }

    public static boolean validateName(String mName) {
        return mName.matches("[a-zA-Z]{3,30}");
    }

    private static boolean areTheSame(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    private boolean validatePassword(String newPasswd) {
        return newPasswd.matches("^(?=.*[a-z])(?=.*[A-Z])((?=.*\\d)|(?=.*[!\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~]))(?!.*[ \\t\\n\\x0B\\f\\r]).{6,}$");
    }

    public void register(@Nullable String mName, @Nullable String mSurname, @Nullable String mEmail, @Nullable String mPassword, @Nullable String mRepeatPassword) {
        boolean checkName = false;
        boolean checkSurname = false;
        boolean checkEmail = false;
        boolean checkPassword = false;
        boolean checkRepeatPassword = false;

        if (!mName.isEmpty() && validateName(mName.trim())) {
            checkName = validateName(mName);
        } else {
            getView().nameError();
        }

        if (!mSurname.isEmpty() && validateName(mSurname.trim())) {
            checkSurname = validateName(mSurname);
        } else {
            getView().surnameError();
        }

        if (!mEmail.isEmpty() && validateEmail(mEmail.trim())) {
            checkEmail = validateEmail(mEmail.trim());
        } else {
            getView().emailError();
        }

        if ((!mPassword.isEmpty() && validatePassword(mPassword.trim())) && (!mRepeatPassword.isEmpty() && areTheSame(mPassword, mRepeatPassword))) {
            checkPassword = validatePassword(mPassword.trim());
            checkRepeatPassword = areTheSame(mPassword, mRepeatPassword);
        } else {
            getView().passwordError();
        }

        if (checkName && checkSurname && checkEmail && checkPassword && checkRepeatPassword) {
            Timber.i(mName + mSurname + mEmail + mPassword + mRepeatPassword);
        }else {
            Timber.e("Rejestracja niemo¿liwa");
            getView().onRegistrationError();
        }

    }
}
