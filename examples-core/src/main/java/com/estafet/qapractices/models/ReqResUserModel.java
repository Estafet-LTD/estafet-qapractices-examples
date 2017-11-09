package com.estafet.qapractices.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Petar Petrov on 07-Nov-17.
 */
public class ReqResUserModel {

    @Expose
    private String id;

    @Expose
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @SerializedName("last_name")
    private String lastName;

    @Expose
    private String avatar;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setAvatar(final String avatar) {
        this.avatar = avatar;
    }
}
