package com.playninelives.response;

public class Picks {

    private int id;
    private int user;
    private int team;
    private String teamName;
    private int game;



    public Picks(int id, int user, int team, String teamName, int game) {
        this.id = id;
        this.user = user;
        this.team = team;
        this.teamName = teamName;
        this.game = game;

    }
    public Picks(int user, int team, String teamName, int game) {
        this.id = -1;
        this.user = user;
        this.team = team;
        this.teamName = teamName;
        this.game = game;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public Picks() {
    }
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;

    }

}
