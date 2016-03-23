package com.playninelives.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.playninelives.R;
import com.playninelives.response.Game;
import com.playninelives.task.GetDataTask;
import com.playninelives.task.NineLivesTask;

import java.net.URL;

public class PickActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TableLayout tableLayout;

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
        if (id == R.id.action_settings) {
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

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private TableRow createGameTableRow(final Game game) {
        TableRow tr = (TableRow)(LayoutInflater.from(this).inflate(R.layout.pick_table_row, tableLayout, false));

        final Button homeButton = (Button)tr.getChildAt(0);
        final Button awayButton = (Button)tr.getChildAt(1);

        homeButton.setText(game.getTeam1());
        awayButton.setText(game.getTeam2());

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                (new MakePickTask(game.getTeam1(), game.getId())).execute();
                homeButton.getBackground().setColorFilter(Color.parseColor("#59C1DA"), PorterDuff.Mode.DARKEN);
                awayButton.setEnabled(false);
            }
        });

        awayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                (new MakePickTask(game.getTeam2(), game.getId())).execute();
                awayButton.getBackground().setColorFilter(Color.parseColor("#59C1DA"), PorterDuff.Mode.DARKEN);
                homeButton.setEnabled(false);
            }
        });

        return tr;
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
            return "games/pick/";
        }

        public void execute() {
            super.execute(helper.createUrl("nathan@digitalda.ca", String.valueOf(gameId), team));
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
        protected void onPostExecute(final Game[] games) {

            for (Game game : games) {
                tableLayout.addView(createGameTableRow(game));
            }

        }
    }
}
