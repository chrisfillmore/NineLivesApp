package com.playninelives.response;

public class Picks {

    private int id;
    private int user;
    private int team;
    private String teamName;
    private int game;
    private int lives;

    public Picks(int user, int team, String teamName, int game, int lives){
        this(-1, user, team, teamName, game, lives);
    }
    public Picks(int id, int user, int team, String teamName, int game, int lives) {
        this.id = id;
        this.user = user;
        this.team = team;
        this.teamName = teamName;
        this.game = game;
        this.lives = lives;
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

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
