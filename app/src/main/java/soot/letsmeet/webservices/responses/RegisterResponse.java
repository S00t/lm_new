package soot.letsmeet.webservices.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Soot on 23/05/2017.
 */

public class RegisterResponse {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("full_name")
    @Expose
    private String fullName;

    /**
     * No args constructor for use in serialization
     */
    public RegisterResponse() {
    }

    /**
     * @param id
     * @param email
     * @param fullName
     */
    public RegisterResponse(Long id, String email, String fullName) {
        super();
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegisterResponse withId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterResponse withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public RegisterResponse withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
