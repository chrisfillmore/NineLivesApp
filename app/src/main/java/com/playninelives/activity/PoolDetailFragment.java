package com.playninelives.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.playninelives.R;
import com.playninelives.response.PoolDetail;
import com.playninelives.task.GetDataTask;

import java.net.URL;

/**
 * A fragment representing a single Pool detail screen.
 * This fragment is either contained in a {@link PoolListActivity}
 * in two-pane mode (on tablets) or a {@link PoolDetailActivity}
 * on handsets.
 */
public class PoolDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    CollapsingToolbarLayout appBarLayout = null;
    View root = null;

    public PoolDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            Activity activity = this.getActivity();
            appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.pool_detail, container, false);
        return root;
    }

    private class GetPoolDetail extends GetDataTask<PoolDetail> {

        String id;

        public GetPoolDetail(String id) {
            this.id = id;
        }

        public AsyncTask<URL, String, PoolDetail> execute() {
            return super.execute(id);
        }

        @Override
        public String getPath() {
            return "pool/";
        }

        @Override
        public void onPostExecute(PoolDetail poolDetail) {
            if (appBarLayout != null) {
                appBarLayout.setTitle(poolDetail.getPool().getContent());
            }

            if (root != null) {
                ((TextView) root.findViewById(R.id.pool_detail)).setText(poolDetail.getPool().getDetails());
            }
        }
    }
}
