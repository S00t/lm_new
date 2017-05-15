package soot.letsmeet.models;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.UUID;

@DatabaseTable(tableName = "token")
public class Token implements Serializable {

    @DatabaseField(generatedId = true)
    private int mTokenId;
    @DatabaseField
    @SerializedName("ACCES_TOKEN")
    private String mAccessToken;
    @DatabaseField
    @SerializedName("EXPIRES_IN")
    private long mExpiresIn;
    @DatabaseField
    @SerializedName("EXPIRES_AT")
    private long mExpiresAt;
    @DatabaseField
    @SerializedName("TOKEN_TYP")
    private String mTokenType;
    @DatabaseField
    @SerializedName("REFRESH_TOKEN")
    private String mRefreshToken;

    public Token() {
    }

    public Token(String mAccessToken, Long mExpiresIn, String mTokenType, String mRefreshToken) {
        this.mAccessToken = mAccessToken;
        this.mExpiresIn = mExpiresIn;
        this.mExpiresAt = (mExpiresIn * 1000) + System.currentTimeMillis();
        this.mTokenType = mTokenType;
        this.mRefreshToken = mRefreshToken;
    }

    public String getmAccessToken() {
        return mAccessToken;
    }

    public long getmExpiresIn() {
        return mExpiresIn;
    }

    public long getmExpiresAt() {
        return mExpiresAt;
    }

    public String getmTokenType() {
        return mTokenType;
    }

    public String getmRefreshToken() {
        return mRefreshToken;
    }


}