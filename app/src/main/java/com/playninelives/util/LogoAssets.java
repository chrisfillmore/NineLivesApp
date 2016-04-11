package com.playninelives.util;

import android.util.SparseArray;

import com.playninelives.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by computer on 2016-04-09.
 */
public class LogoAssets {

    private static Map<String, Integer> logoIds;
    private static SparseArray<String> teamIds;

    private static final String
            DUCKS = "Ducks",
            COYOTES = "Coyotes",
            BRUINS = "Bruins",
            SABRES = "Sabres",
            FLAMES = "Flames",
            HURRICANES = "Hurricanes",
            BLACKHAWKS = "Blackhawks",
            AVALANCHE = "Avalanche",
            BLUE_JACKETS = "Blue Jackets",
            STARS = "Stars",
            RED_WINGS = "Red Wings",
            OILERS = "Oilers",
            PANTHERS = "Panthers",
            KINGS = "Kings",
            WILD = "Wild",
            CANADIENS = "Canadiens",
            PREDATORS = "Predators",
            DEVILS = "Devils",
            ISLANDERS = "Islanders",
            RANGERS = "Rangers",
            FLYERS = "Flyers",
            PENGUINS = "Penguins",
            SENATORS = "Senators",
            SHARKS = "Sharks",
            BLUES = "Blues",
            LIGHTNING = "Lightning",
            MAPLE_LEAFS = "Maple Leafs",
            CANUCKS = "Canucks",
            CAPITALS = "Capitals",
            JETS = "Jets";

    static {
        // logos
        logoIds = new HashMap<String, Integer>();
        logoIds.put(DUCKS, R.mipmap.logo_ducks);
        logoIds.put(COYOTES, R.mipmap.logo_coyotes);
        logoIds.put(BRUINS, R.mipmap.logo_bruins);
        logoIds.put(SABRES, R.mipmap.logo_sabres);
        logoIds.put(FLAMES, R.mipmap.logo_flames);
        logoIds.put(HURRICANES, R.mipmap.logo_hurricanes);
        logoIds.put(BLACKHAWKS, R.mipmap.logo_blackhawks);
        logoIds.put(AVALANCHE, R.mipmap.logo_avalanche);
        logoIds.put(BLUE_JACKETS, R.mipmap.logo_blue_jackets);
        logoIds.put(STARS, R.mipmap.logo_stars);
        logoIds.put(RED_WINGS, R.mipmap.logo_red_wings);
        logoIds.put(OILERS, R.mipmap.logo_oilers);
        logoIds.put(PANTHERS, R.mipmap.logo_panthers);
        logoIds.put(KINGS, R.mipmap.logo_kings);
        logoIds.put(WILD, R.mipmap.logo_wild);
        logoIds.put(CANADIENS, R.mipmap.logo_canadiens);
        logoIds.put(PREDATORS, R.mipmap.logo_predators);
        logoIds.put(DEVILS, R.mipmap.logo_devils);
        logoIds.put(ISLANDERS, R.mipmap.logo_islanders);
        logoIds.put(RANGERS, R.mipmap.logo_rangers);
        logoIds.put(FLYERS, R.mipmap.logo_flyers);
        logoIds.put(PENGUINS, R.mipmap.logo_penguins);
        logoIds.put(SENATORS, R.mipmap.logo_senators);
        logoIds.put(SHARKS, R.mipmap.logo_sharks);
        logoIds.put(BLUES, R.mipmap.logo_blues);
        logoIds.put(LIGHTNING, R.mipmap.logo_lightning);
        logoIds.put(MAPLE_LEAFS, R.mipmap.logo_maple_leafs);
        logoIds.put(CANUCKS, R.mipmap.logo_canucks);
        logoIds.put(CAPITALS, R.mipmap.logo_capitals);
        logoIds.put(JETS, R.mipmap.logo_jets);

        // team ids
        teamIds = new SparseArray<String>();
        teamIds.put(1, DUCKS);
        teamIds.put(2, COYOTES);
        teamIds.put(3, BRUINS);
        teamIds.put(4, SABRES);
        teamIds.put(5, FLAMES);
        teamIds.put(6, HURRICANES);
        teamIds.put(7, BLACKHAWKS);
        teamIds.put(8, AVALANCHE);
        teamIds.put(9, BLUE_JACKETS);
        teamIds.put(10, STARS);
        teamIds.put(11, RED_WINGS);
        teamIds.put(12, OILERS);
        teamIds.put(13, PANTHERS);
        teamIds.put(14, KINGS);
        teamIds.put(15, WILD);
        teamIds.put(16, CANADIENS);
        teamIds.put(17, PREDATORS);
        teamIds.put(18, DEVILS);
        teamIds.put(19, ISLANDERS);
        teamIds.put(20, RANGERS);
        teamIds.put(21, FLYERS);
        teamIds.put(22, PENGUINS);
        teamIds.put(23, SENATORS);
        teamIds.put(24, SHARKS);
        teamIds.put(25, BLUES);
        teamIds.put(26, LIGHTNING);
        teamIds.put(27, MAPLE_LEAFS);
        teamIds.put(28, CANUCKS);
        teamIds.put(29, CAPITALS);
        teamIds.put(30, JETS);
    }


    public static int getLogoId(String team) {
        return logoIds.get(team);
    }

    public static int getLogoId(int teamId) {
        String teamName = teamIds.get(teamId);
        return getLogoId(teamName);
    }
}
