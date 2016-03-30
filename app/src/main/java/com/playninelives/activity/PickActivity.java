package com.playninelives.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.playninelives.R;
import com.playninelives.response.Game;
import com.playninelives.response.GamePick;
import com.playninelives.response.Picks;
import com.playninelives.task.GetDataTask;
import com.playninelives.task.NineLivesTask;
import com.playninelives.util.Session;

import java.net.URL;

public class PickActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    TableLayout tableLayout = null;
    Game[] games = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pick);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        tableLayout = (TableLayout)findViewById(R.id.pickTable);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        GetGamesTask task = new GetGamesTask();
        task.execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pick, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_signout) {
            new Session(PickActivity.this).signOut();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pools) {
            Intent i = new Intent(this, PoolListActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "I'm playing Nine Lives. Come join me!");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private String getUserId() {
        return String.valueOf(new Session(this).getUserId());
    }

    private TableRow createGameTableRow(final Game game, Picks pick) {
        TableRow tr = (TableRow)(LayoutInflater.from(this).inflate(R.layout.pick_table_row, tableLayout, false));

        final Button homeButton = (Button)tr.getChildAt(0);
        final Button awayButton = (Button)tr.getChildAt(1);

        homeButton.setText(game.getTeam1());
        awayButton.setText(game.getTeam2());

        GamePick gamePick = new GamePick(game, pick);

        if (gamePick.isPicked()) {
            if (gamePick.isTeam1()) {
                toggleSelectButtons(homeButton, awayButton);
            } else {
                toggleSelectButtons(awayButton, homeButton);
            }
        } else {
            homeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    makePick(game.getTeam1(), game.getId());
                    toggleSelectButtons(homeButton, awayButton);
                }
            });

            awayButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    makePick(game.getTeam2(), game.getId());
                    toggleSelectButtons(awayButton, homeButton);
                }
            });
        }

        return tr;
    }

    private void makePick(String team, int gameId) {
        (new MakePickTask(team, gameId)).execute();
    }

    private void toggleSelectButtons(Button selected, Button unselected) {
        selected.getBackground().setColorFilter(Color.parseColor("#E91E63"), PorterDuff.Mode.DARKEN);
        selected.setTextColor(Color.parseColor("#FFFFFF"));
        selected.setEnabled(false);
        unselected.setEnabled(false);
    }

    private Picks getPick(Game g, Picks[] picks) {
        if (picks == null) {
            return null;
        }

        int gameId = g.getId();
        Picks pick = null;

        for (Picks p : picks) {
            if (p.getGame() == gameId) {
                pick = p;
                break;
            }
        }

        return pick;
    }

    private class MakePickTask extends NineLivesTask<URL, Void, Void> {

        private String team;
        private int gameId;

        public MakePickTask(String team, int gameId) {
            this.team = team;
            this.gameId = gameId;
        }

        @Override
        public String getPath() {
            return "games/picks/";
        }

        public void execute() {
            super.execute(helper.createUrl(getUserId(), String.valueOf(gameId), team));
        }

        @Override
        protected Void doInBackground(URL... urls) {
            String response = helper.downloadUrl(urls[0]);
            return null;
        }
    }


    private class GetGamesTask extends GetDataTask<Game[]> {
        public GetGamesTask() {
            super(Game[].class);
        }

        @Override
        public String getPath() {
            return "games/";
        }

        @Override
        protected void onPostExecute(final Game[] g) {
            games = g;
            new GetPicksTask(getUserId()).execute();
        }
    }

    private class GetPicksTask extends GetDataTask<Picks[]> {

        String user;

        public GetPicksTask(String user) {
            super(Picks[].class);
            this.user = user;
        }

        @Override
        public String getPath() {
            return "games/picks/" + user;
        }

        @Override
        protected void onPostExecute(final Picks[] picks) {
            if (games == null || tableLayout == null) {
                return;
            }

            for (Game game : games) {
                Picks p = getPick(game, picks);
                tableLayout.addView(createGameTableRow(game, p));
            }
        }
    }
}
