package soot.letsmeet.activities.login;

import android.Manifest;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;
import soot.letsmeet.LetsMeetApplication;
import soot.letsmeet.R;
import soot.letsmeet.activities.login.controllers.LoginController;
import soot.letsmeet.activities.login.interfaces.LoginInterface;
import soot.letsmeet.databinding.ActivityLoginBinding;
import soot.letsmeet.di.ContextScope.ConnectivityModule;
import timber.log.Timber;


@RuntimePermissions
public class LoginActivity extends AppCompatActivity implements LoginInterface {

    @Inject
    protected LoginController mController;

    private ActivityLoginBinding mLoginBinding;
    private @PreloadViewState int mViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        ((LetsMeetApplication) getApplication()).mApplicationComponent
                .newContextComponent(new ConnectivityModule(this))
                .inject(this);




    }

    @Override
    public void setViewState(@PreloadViewState int viewState) {

    }

    @Override
    public void onLoginSuccess(boolean firstLogging) {

    }

    @Override
    public void onLoginError(int faildLoginCount, @Nullable String message) {

    }

    @Override
    public void onNoAccountOffline() {

    }

    @Override
    public void onUserAccountBlocked() {

    }

    @Override
    public void onChangePassError(@StringRes int message) {

    }
    @NeedsPermission({Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    protected void initApplicationPermissions(){
        Timber.i("Ekran logowania");

    }

    public void loginClick(View v) {
        mLoginBinding.loginInput.setEnabled(false);
        mLoginBinding.passwordInput.setEnabled(false);
        mLoginBinding.loginButtonLogin.setEnabled(false);


        if ((mController.isNetworkAvaible()) && mViewState != STATE_CHANGE_PASSWD) {
            mController.login(mLoginBinding.loginInput.getText() != null ? mLoginBinding.loginInput.getText().toString().trim() : null, mLoginBinding.passwordInput.getText().toString());
        }

//        else {
//            mController.changePasswordOnline(mLoginBinding.loginLoginInput.getText() != null ? mLoginBinding.loginLoginInput.getText().trim() : null, mLoginBinding.loginPasswordInput.getText(), mLoginBinding.loginUserPasswordInput.getText(), mLoginBinding.loginUserPasswordRepasteInput.getText());
//        }
    }
}
