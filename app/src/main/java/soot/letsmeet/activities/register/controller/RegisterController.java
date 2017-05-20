package soot.letsmeet.activities.register.controller;

import android.os.HandlerThread;

import javax.inject.Inject;

import soot.letsmeet.activities.BaseController;
import soot.letsmeet.activities.register.interfaces.RegisterInterface;

/**
 * Created by Soot on 13/05/2017.
 */

public class RegisterController extends BaseController<RegisterInterface> {
    private HandlerThread mHandlerThread;

    @Inject
    public RegisterController(){
        super();
        mHandlerThread = new HandlerThread("RegistrationProcessing");
        mHandlerThread.start();
    }

    @Override
    public void onCreate(RegisterInterface mView) {
        super.onCreate(mView);

    }

    public void registerClick(String mName, String mSurname, String mPassword, String mRepeatedPassword){



    }

    public static boolean validateEmail(String email) {
        return  email.matches(".+@.+\\.[a-z]+");
    }

    public static boolean validateSurname( String lastName ){
        return lastName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
    }

    private static boolean isTheSame(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    private boolean checkPassRequirments(String newPasswd) {
        return newPasswd.matches("^(?=.*[a-z])(?=.*[A-Z])((?=.*\\d)|(?=.*[!\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~]))(?!.*[ \\t\\n\\x0B\\f\\r]).{6,}$");
    }

}
