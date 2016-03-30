package com.playninelives.response;

public class PoolDetail {

    public PoolDetail(Pool pool, User[] users) {
        this.pool = pool;
        this.users = users;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    private Pool pool;
    private User[] users;

}
