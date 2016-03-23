package com.playninelives.response;

/**
 * Created by computer on 2016-03-09.
 */
public class LoginResponse {

    public boolean isSuccess() {
        return success;
    }

    public boolean isInvalidPassword() {
        return invalidPassword;
    }

    public boolean isUnregisteredUser() {
        return unregisteredUser;
    }

    public int getId() {
        return id;
    }

    public LoginResponse(boolean success, boolean invalidPassword, boolean unregisteredUser, int id) {
        this.success = success;
        this.invalidPassword = invalidPassword;
        this.unregisteredUser = unregisteredUser;
        this.id = id;
    }

    private boolean success;
    private boolean invalidPassword;
    private boolean unregisteredUser;
    private int id;

}
