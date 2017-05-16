package soot.letsmeet.activities.register.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Soot on 13/05/2017.
 */

public class RegisterController {

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
