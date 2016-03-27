package com.playninelives.response;

/**
 * Created by computer on 2016-03-27.
 */
public class GamePick {

    private Game game;
    private Picks pick;

    public GamePick(Game game, Picks pick) {
        this.game = game;
        this.pick = pick;
    }

    public boolean isPicked() {
        return pick != null;
    }

    public boolean isTeam1() {
        return pick.getTeamName().equals(game.getTeam1());
    }

    public boolean isTeam2() {
        return pick.getTeamName().equals(game.getTeam2());
    }
}
