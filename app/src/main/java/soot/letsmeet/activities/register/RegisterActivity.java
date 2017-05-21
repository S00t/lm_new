package soot.letsmeet.activities.register;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import soot.letsmeet.LetsMeetApplication;
import soot.letsmeet.R;
import soot.letsmeet.activities.login.interfaces.LoginInterface;
import soot.letsmeet.activities.register.controller.RegisterController;
import soot.letsmeet.activities.register.interfaces.RegisterInterface;
import soot.letsmeet.customviews.ProgressCustomView;
import soot.letsmeet.databinding.ActivityRegisterBinding;
import soot.letsmeet.di.ContextScope.ConnectivityModule;
import soot.letsmeet.utils.ViewUtils;

public class RegisterActivity extends AppCompatActivity implements RegisterInterface, ProgressCustomView.ProgressInterface {


    @Inject
    protected RegisterController mController;

    private ActivityRegisterBinding mRegisterBinding;
    private
    @LoginInterface.PreloadViewState
    int mViewState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        ((LetsMeetApplication) getApplication()).mApplicationComponent
                .newContextComponent(new ConnectivityModule(this))
                .inject(this);

        mController.onCreate(this);
        mRegisterBinding.registerProgress.setProgressInterface(this);
        setProgresViewState(STATE_NORMAL, null, null);

        if (mRegisterBinding.RegisterName != null)
            mRegisterBinding.RegisterName.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
                ViewUtils.hideKeyboard(this, v);
                registerClick(v);
                return true;
            });

    }

    public void registerClick(View v) {

        if ((mRegisterBinding.RegisterName.getText() == null || mRegisterBinding.RegisterName.getText().toString().isEmpty()) &&
                (mRegisterBinding.RegisterSurname.getText() == null || mRegisterBinding.RegisterSurname.getText().toString().isEmpty()) &&
                (mRegisterBinding.RegisterMail.getText() == null || mRegisterBinding.RegisterMail.getText().toString().isEmpty()) &&
                (mRegisterBinding.RegisterPassword.getText() == null || mRegisterBinding.RegisterPassword.getText().toString().isEmpty() &&
                        mRegisterBinding.RegisterRepeatPassword.getText() == null || mRegisterBinding.RegisterRepeatPassword.getText().toString().isEmpty())) {
            nameError();
            surnameError();
            emailError();
            passwordError();
            repeatPasswordError();
            setProgresViewState(STATE_NORMAL, null, null);
            showToast(this.getResources().getString(R.string.login_password_empty));
        } else {
            setProgresViewState(STATE_REGISTER, this.getResources().getString(R.string.register_label), true);
            mController.register(mRegisterBinding.RegisterName.getText().toString().trim(),
                    mRegisterBinding.RegisterSurname.getText().toString().trim(),
                    mRegisterBinding.RegisterMail.getText().toString().trim(),
                    mRegisterBinding.RegisterPassword.getText().toString().trim(),
                    mRegisterBinding.RegisterRepeatPassword.getText().toString().trim());
        }

    }

    @Override
    public void onRegisterSuccess() {

    }

    @Override
    public void onRegistrationError() {
        setProgresViewState(STATE_NORMAL, null, null);
                Toast.makeText(this, R.string.error_register, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passwordsNotMatch() {
        mRegisterBinding.RegisterPassword.setError(this.getResources().getString(R.string.paswords_not_matching));
        mRegisterBinding.RegisterRepeatPassword.setError(this.getResources().getString(R.string.paswords_not_matching));
        showToast(this.getResources().getString(R.string.paswords_not_matching));

    }

    @Override
    public void nameError() {
        mRegisterBinding.RegisterName.setError(this.getResources().getString(R.string.empty_field));
    }

    @Override
    public void surnameError() {
        mRegisterBinding.RegisterSurname.setError(this.getResources().getString(R.string.empty_field));
    }

    @Override
    public void emailError() {
        mRegisterBinding.RegisterMail.setError(this.getResources().getString(R.string.empty_field));
    }

    @Override
    public void passwordError() {
        mRegisterBinding.RegisterPassword.setError(this.getResources().getString(R.string.errorPassReq));
    }

    @Override
    public void repeatPasswordError() {
        mRegisterBinding.RegisterRepeatPassword.setError(this.getResources().getString(R.string.errorPassReq));
    }

    @Override
    public void setProgresViewState(@ProgressCustomView.ProgressInterface.ProgresViewState int viewState, @Nullable String title, @Nullable Boolean mShowBlur) {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
