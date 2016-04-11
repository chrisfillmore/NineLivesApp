package com.playninelives.response;

/**
 * Created by computer on 2016-03-16.
 */
public class Game {

    private int id;
    private String team1;
    private int score1;
    private String team2;
    private int score2;
    private String date;

    public String getTeam1img() {
        return team1img;
    }

    public void setTeam1img(String team1img) {
        this.team1img = team1img;
    }

    public String getTeam2img() {
        return team2img;
    }

    public void setTeam2img(String team2img) {
        this.team2img = team2img;
    }

    private String team1img;
    private String team2img;

    public Game() {
    }

    public Game(int id, String team1, int score1, String team2, int score2, String date) {
        super();
        this.id = id;
        this.team1 = team1;
        this.score1 = score1;
        this.team2 = team2;
        this.score2 = score2;
        this.date = date;
    }

    public Game(String team1, int score1, String team2, int score2, String date) {
        super();
        this.id = -1;
        this.team1 = team1;
        this.score1 = score1;
        this.team2 = team2;
        this.score2 = score2;
        this.date = date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}