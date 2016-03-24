package com.playninelives.response;

public class Pool implements MasterDetailItem{
    private int id;
    private String name;
    private String admin;
    private String tournament;
    private String password;

    public Pool(String name, String admin, String tournament, String password) {
        this.id = -1;
        this.name = name;
        this.admin = admin;
        this.tournament = tournament;
        this.password = password;
    }
    public Pool(int id, String name, String admin, String tournament, String password) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.tournament = tournament;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getIdAsString() {
        return String.valueOf(id);
    }

    @Override
    public String getContent() {
        return name;
    }

    @Override
    public String getDetails() {
        return admin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAdmin() {
        return admin;
    }
    public void setAdmin(String admin) {
        this.admin = admin;
    }
    public String getTournament() {
        return tournament;
    }
    public void setTournament(String tournament) {
        this.tournament = tournament;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}