package com.playninelives.activity;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.playninelives.R;
import com.playninelives.task.GetDataTask;
import com.playninelives.task.NineLivesTask;
import com.playninelives.util.Session;

import java.net.URL;

/**
 * An activity representing a single Pool detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link PoolListActivity}.
 */
public class PoolDetailActivity extends AppCompatActivity {

    String poolId = null;
    String userId = null;

    private static final String fragmentId = "PoolDetailFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pool_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        Bundle poolDetail = getIntent().getBundleExtra(PoolDetailFragment.ARG_POOL_DETAIL);
        poolId = poolDetail.getString(PoolDetailFragment.ARG_POOL_ID);
        userId = String.valueOf(new Session(this).getUserId());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (poolId == null || userId == null) {
                    return;
                }

                new JoinPoolTask(userId, poolId).execute();

                //Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            loadPoolDetailFragment();
            /*
            Bundle arguments = new Bundle();
            arguments.putBundle(PoolDetailFragment.ARG_POOL_DETAIL, getIntent().getBundleExtra(PoolDetailFragment.ARG_POOL_DETAIL));
            PoolDetailFragment fragment = new PoolDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.pool_detail_container, fragment)
                    .commit();*/
        }
    }

    private void loadPoolDetailFragment() {
        Bundle arguments = new Bundle();
        arguments.putBundle(PoolDetailFragment.ARG_POOL_DETAIL, getIntent().getBundleExtra(PoolDetailFragment.ARG_POOL_DETAIL));
        PoolDetailFragment fragment = new PoolDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.pool_detail_container, fragment, fragmentId)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, PoolListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class JoinPoolTask extends NineLivesTask<URL, String, Void> {

        String userId, poolId;

        public JoinPoolTask(String userId, String poolId) {
            this.userId = userId;
            this.poolId = poolId;
        }

        @Override
        public String getPath() {
            return "pool/insert/";
        }

        public AsyncTask<URL, String, Void> execute() {
            return super.execute(helper.createUrl(userId, poolId));
        }

        @Override
        protected Void doInBackground(URL... urls) {
            String response = helper.downloadUrl(urls[0]);
            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            PoolDetailFragment f = (PoolDetailFragment)getSupportFragmentManager().findFragmentByTag(fragmentId);
            f.loadPlayerList();
            //loadPoolDetailFragment();
            System.out.println("CHRIS: join pool postExecute");
        }
    }
}
