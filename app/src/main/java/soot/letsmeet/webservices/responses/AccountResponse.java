package soot.letsmeet.webservices.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Soot on 20/05/2017.
 */

public class AccountResponse {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("nick_name")
    @Expose
    private String nickName;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("show_name")
    @Expose
    private Boolean showName;
    @SerializedName("gravatar_url")
    @Expose
    private String gravatarUrl;

    /**
     * No args constructor for use in serialization
     *
     */
    public AccountResponse() {
    }

    /**
     *
     * @param showName
     * @param phoneNumber
     * @param nickName
     * @param email
     * @param fullName
     * @param gravatarUrl
     */
    public AccountResponse(String email, String fullName, String nickName, String phoneNumber, Boolean showName, String gravatarUrl) {
        super();
        this.email = email;
        this.fullName = fullName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.showName = showName;
        this.gravatarUrl = gravatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountResponse withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public AccountResponse withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public AccountResponse withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AccountResponse withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Boolean getShowName() {
        return showName;
    }

    public void setShowName(Boolean showName) {
        this.showName = showName;
    }

    public AccountResponse withShowName(Boolean showName) {
        this.showName = showName;
        return this;
    }

    public String getGravatarUrl() {
        return gravatarUrl;
    }

    public void setGravatarUrl(String gravatarUrl) {
        this.gravatarUrl = gravatarUrl;
    }

    public AccountResponse withGravatarUrl(String gravatarUrl) {
        this.gravatarUrl = gravatarUrl;
        return this;
    }

}

