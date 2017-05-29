package soot.letsmeet.activities.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;
import soot.letsmeet.LetsMeetApplication;
import soot.letsmeet.R;
import soot.letsmeet.activities.login.controllers.LoginController;
import soot.letsmeet.activities.login.interfaces.LoginInterface;
import soot.letsmeet.activities.register.RegisterActivity;
import soot.letsmeet.customviews.ProgressCustomView;
import soot.letsmeet.databinding.ActivityLoginBinding;
import soot.letsmeet.di.ContextScope.ConnectivityModule;
import soot.letsmeet.utils.ViewUtils;
import timber.log.Timber;


@RuntimePermissions
public class LoginActivity extends AppCompatActivity implements LoginInterface, ProgressCustomView.ProgressInterface {

    @Inject
    protected LoginController mController;

    private ActivityLoginBinding mLoginBinding;
    private
    @PreloadViewState
    int mViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        ((LetsMeetApplication) getApplication()).mApplicationComponent
                .newContextComponent(new ConnectivityModule(this))
                .inject(this);

        mController.onCreate(this);
        LoginActivityPermissionsDispatcher.initApplicationPermissionsWithCheck(this);

        mLoginBinding.loginProgress.setProgressInterface(this);
        setProgresViewState(STATE_NORMAL, null, null);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "Logowanie poprawne", Toast.LENGTH_SHORT).show();
        setProgresViewState(STATE_NORMAL, null, true);
//        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
//        startActivity(intent)
    }

    @Override
    public void onLoginError() {
        setProgresViewState(STATE_NORMAL, null, null);
        Toast.makeText(this, R.string.error_logging, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoAccountOffline() {

    }


    @Override
    public void setProgresViewState(@ProgresViewState int viewState, @Nullable String title, @Nullable Boolean mShowBlur) {
        mLoginBinding.loginProgress.setProgressVisibe(viewState == STATE_LOADING);
        mLoginBinding.loginProgress.setmProgressTitle(title);
        mLoginBinding.loginProgress.setmShowBlur(mShowBlur);
    }

    public void loginClick(View v) {
        if ((mController.isNetworkAvaible())){
        setProgresViewState(STATE_LOADING, "Logowanie...", true);
        if ((mLoginBinding.loginInput.getText() == null || mLoginBinding.loginInput.getText().toString().isEmpty()) &&
                (mLoginBinding.passwordInput.getText() == null || mLoginBinding.passwordInput.getText().toString().isEmpty())) {
            mLoginBinding.loginInput.setError(this.getResources().getString(R.string.login_empty));
            mLoginBinding.passwordInput.setError(this.getResources().getString(R.string.password_empty));
            setProgresViewState(STATE_NORMAL, null, null);
            showToast(this.getResources().getString(R.string.login_password_empty));
        } else if (mLoginBinding.loginInput.getText() == null || mLoginBinding.loginInput.getText().toString().isEmpty()) {
            mLoginBinding.loginInput.setError(this.getResources().getString(R.string.login_empty));
            setProgresViewState(STATE_NORMAL, null, null);
            showToast(this.getResources().getString(R.string.login_password_empty));
        } else if (mLoginBinding.passwordInput.getText() == null || mLoginBinding.passwordInput.getText().toString().isEmpty()) {
            mLoginBinding.passwordInput.setError(this.getResources().getString(R.string.password_empty));
            setProgresViewState(STATE_NORMAL, null, null);
            showToast(this.getResources().getString(R.string.login_password_empty));
        } else {
            mController.login(mLoginBinding.loginInput.getText() != null ? mLoginBinding.loginInput.getText().toString().trim() : null, mLoginBinding.passwordInput.getText().toString());
        }
        }else{

        }
    }

    public void registerClick(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    @NeedsPermission({Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    protected void initApplicationPermissions() {
        Timber.i("Ekran logowania");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LoginActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnPermissionDenied({Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    protected void onPhoneStatePermissionDenied() {
        Toast.makeText(this, R.string.permission_error, Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnNeverAskAgain({Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    protected void onPhoneStatePermissionCancelled() {
        Toast.makeText(this, R.string.permission_error_never_ask, Toast.LENGTH_LONG).show();
        finish();
    }

}
