package soot.letsmeet.activities.register.controller;

/**
 * Created by Soot on 13/05/2017.
 */

public class RegisterController {

    public void registerClick(String mName, String mSurname, String mPassword, String mRepeatedPassword){



    }



    private boolean checkNewPassRequirments(String newPasswd) {
        return newPasswd.matches("^(?=.*[a-z])(?=.*[A-Z])((?=.*\\d)|(?=.*[!\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~]))(?!.*[ \\t\\n\\x0B\\f\\r]).{6,}$");
    }

}
