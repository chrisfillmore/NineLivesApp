package com.playninelives.response;

public class UserPool {

    private int id;
    private int userId;
    private int poolId;

    public int getUserLives() {
        return userLives;
    }

    public void setUserLives(int userLives) {
        this.userLives = userLives;
    }

    private int userLives;
    private String userName;
    public UserPool(int userId, int poolId, String userName) {
        super();
        this.id = -1;
        this.userId = userId;
        this.poolId = poolId;
        this.userName = userName;
    }
    public UserPool(int id, int userId, int poolId, String userName) {
        super();
        this.id = id;
        this.userId = userId;
        this.poolId = poolId;
        this.userName = userName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getPoolId() {
        return poolId;
    }
    public void setPoolId(int poolId) {
        this.poolId = poolId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
