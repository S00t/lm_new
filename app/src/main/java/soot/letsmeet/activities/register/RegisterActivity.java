package soot.letsmeet.activities.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import javax.inject.Inject;

import soot.letsmeet.R;
import soot.letsmeet.activities.login.interfaces.LoginInterface;
import soot.letsmeet.activities.register.controller.RegisterController;
import soot.letsmeet.activities.register.interfaces.RegisterInterface;
import soot.letsmeet.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements RegisterInterface {


    @Inject
    protected RegisterController mController;

    private ActivityRegisterBinding mRegisterBinding;
    private @LoginInterface.PreloadViewState
    int mViewState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        mController.onCreate(this);
    }

    public void registerClick(View v) {

    }
}
