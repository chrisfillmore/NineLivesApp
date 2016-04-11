package com.playninelives.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.playninelives.R;
import com.playninelives.response.PoolDetail;
import com.playninelives.response.User;
import com.playninelives.response.UserPool;
import com.playninelives.task.GetDataTask;

import java.net.URL;

/**
 * A fragment representing a single Pool detail screen.
 * This fragment is either contained in a {@link PoolListActivity}
 * in two-pane mode (on tablets) or a {@link PoolDetailActivity}
 * on handsets.
 */
public class PoolDetailFragment extends Fragment {

    public static final String ARG_POOL_ID = "pool_id";
    public static final String ARG_POOL_NAME = "pool_name";
    public static final String ARG_POOL_DETAIL = "pool_detail";



    private String poolName = "";

    CollapsingToolbarLayout appBarLayout = null;
    LinearLayout root = null;

    String poolId;

    public PoolDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments().getBundle(ARG_POOL_DETAIL);

        try {
            poolId = args.getString(ARG_POOL_ID);
            Activity activity = this.getActivity();
            appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

            poolName = args.getString(ARG_POOL_NAME);
            loadPlayerList();

        } catch (NullPointerException e) {
            // Snackbar: invalid pool
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (LinearLayout) inflater.inflate(R.layout.pool_detail, container, false);

        if (appBarLayout != null) {
            appBarLayout.setTitle(poolName);
        }

        return root;
    }


    public void loadPlayerList() {
        new GetPoolPlayers(poolId).execute();
    }

    private void populateUserList(UserPool[] users) {

        root.removeAllViews();

        for (int i = 0; i < users.length; i++) {
            UserPool user = users[i];
            System.out.println("CHRIS: " + user.getUserName());
            LinearLayout l = (LinearLayout)LayoutInflater.from(getContext()).inflate(R.layout.pool_member_row, root, false);
            TextView userNameField = (TextView)l.findViewById(R.id.pool_member_name);
            String text = String.format("%d. %s", i + 1, user.getUserName());
            //userNameField.setText((i + 1) + " - " + user.getUserName());
            userNameField.setText(text);

            TextView livesField = (TextView)l.findViewById(R.id.pool_member_lives);
            livesField.setText(String.valueOf(user.getUserLives()));
            root.addView(l);
        }


    }

    private class GetPoolPlayers extends GetDataTask<UserPool[]> {

        String id;

        public GetPoolPlayers(String id) {
            super(UserPool[].class);
            this.id = id;
        }

        public AsyncTask<URL, String, UserPool[]> execute() {
            return super.execute(id);
        }

        @Override
        public String getPath() {
            return "pool/";
        }

        @Override
        public void onPostExecute(UserPool[] users) {

            if (root != null) {
                //((TextView) root.findViewById(R.id.pool_detail)).setText(playerList(users));
                populateUserList(users);
            }

        }
    }

}
