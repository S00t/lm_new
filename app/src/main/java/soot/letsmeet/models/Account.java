package soot.letsmeet.models;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Soot on 03/05/2017.
 */
@DatabaseTable(tableName = "account")
public class Account extends BaseJsonModel implements Serializable {

    @DatabaseField(id = true)
    @SerializedName("email")
    private String email;
    @DatabaseField
    @SerializedName("nick_name")
    @Nullable
    private String nickName;
    @DatabaseField
    @SerializedName("full_name")
    private String fullName;
    @DatabaseField
    @SerializedName("phone_number")
    @Nullable
    private String phoneNumber;
    @DatabaseField
    @SerializedName("show_name")
    @Nullable
    private Boolean showName;
    @DatabaseField
    @SerializedName("gravatar_url")
    @Nullable
    private String avatarUrl;

    public Account(Parcel source) {
        this.email = source.readString();
        this.nickName = source.readString();
        this.fullName = source.readString();
        this.phoneNumber = source.readString();
        this.showName = source.readByte() != 0;
        this.avatarUrl = source.readString();
    }




    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", showName=" + showName +
                ", gravatarUrl='" + avatarUrl + '\'' +
                '}';
    }

    public Account() {
    }

    public Account(String email, String nickName, String fullName, String phoneNumber, Boolean showName, String avatarUrl) {
        this.email = email;
        this.nickName = nickName;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.showName = showName;
        this.avatarUrl = avatarUrl;
    }

    public Account(String email, String nickName, String avatarUrl, String fullName, Boolean showName) {
        this.email = email;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.fullName = fullName;
        this.showName = showName;
    }


//==================================================================================================
//    Gettery i Settery
//==================================================================================================

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getShowName() {
        return showName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickName(@Nullable String nickName) {
        this.nickName = nickName;
    }

    public void setFullName(@Nullable String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(@Nullable String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setShowName(@Nullable Boolean showName) {
        this.showName = showName;
    }

    public void setAvatarUrl(@Nullable String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}