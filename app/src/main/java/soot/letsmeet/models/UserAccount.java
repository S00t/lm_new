package soot.letsmeet.models;

/**
 * Created by Soot on 23/05/2017.
 */

public class UserAccount {
    private static UserAccount mInstance = null;

    private String mID;
    private String mLogin;
    private String mFullName;
    private String mPass;

    public UserAccount(String login, String pass) {
        mLogin = login;
        mPass = pass;
    }

    public static UserAccount getInstance() {
        if (mInstance == null)
            mInstance = new UserAccount();
        return mInstance;
    }

    private UserAccount() {
    }

    public UserAccount(String mLogin, String mFullName, String mPass) {
        this.mLogin = mLogin;
        this.mFullName = mFullName;
        this.mPass = mPass;
    }

    // ---------------------------- getters and setters ----------------------------

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmLogin() {
        return mLogin;
    }

    public void setmLogin(String mLogin) {
        this.mLogin = mLogin;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "mID='" + mID + '\'' +
                ", mLogin='" + mLogin + '\'' +
                ", mFullName='" + mFullName + '\'' +
                ", mPass='" + mPass + '\'' +
                '}';
    }
}

