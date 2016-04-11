package com.playninelives.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.playninelives.R;

import com.playninelives.response.MasterDetailItem;
import com.playninelives.response.Pool;
import com.playninelives.task.GetDataTask;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Pools. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PoolDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PoolListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PoolListActivity.this, CreatePoolActivity.class);
                startActivity(i);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        loadPools();

    }

    private void loadPools() {
        new GetPoolTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, Pool[] pools) {
        List<MasterDetailItem> poolList = new ArrayList<MasterDetailItem>();

        for (Pool pool : pools) {
            poolList.add(pool);
        }

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(poolList));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<MasterDetailItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<MasterDetailItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pool_list_content, parent, false);
            //view.setBackgroundColor(Color.parseColor("#333333"));
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            //holder.mIdView.setText(mValues.get(position).getIdAsString());
            holder.mContentView.setText(mValues.get(position).getContent());
            String leaderText = "Leader: " + mValues.get(position).getDetails();
            holder.mLeaderView.setText(leaderText);

            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
                //holder.mContentView.setBackgroundColor(Color.parseColor("#F5F5F5"));
            }

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(PoolDetailFragment.ARG_POOL_ID, holder.mItem.getIdAsString());
                        arguments.putString(PoolDetailFragment.ARG_POOL_NAME, holder.mItem.getContent());
                        PoolDetailFragment fragment = new PoolDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.pool_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, PoolDetailActivity.class);
                        Bundle args = new Bundle();

                        args.putString(PoolDetailFragment.ARG_POOL_ID, holder.mItem.getIdAsString());
                        args.putString(PoolDetailFragment.ARG_POOL_NAME, holder.mItem.getContent());
                            intent.putExtra(PoolDetailFragment.ARG_POOL_DETAIL, args);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            //public final TextView mIdView;
            public final TextView mContentView;
            public final TextView mLeaderView;
            public MasterDetailItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                //mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
                mLeaderView = (TextView) view.findViewById(R.id.pool_leader);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

    private class GetPoolTask extends GetDataTask<Pool[]> {

        @Override
        public String getPath() {
            return "pool/";
        }

        public GetPoolTask() {
            super(Pool[].class);
        }

        @Override
        protected void onPostExecute(final Pool[] pools) {
            View recyclerView = findViewById(R.id.pool_list);
            assert recyclerView != null;
            setupRecyclerView((RecyclerView) recyclerView, pools);

            if (findViewById(R.id.pool_detail_container) != null) {
                // The detail container view will be present only in the
                // large-screen layouts (res/values-w900dp).
                // If this view is present, then the
                // activity should be in two-pane mode.
                mTwoPane = true;
            }
        }

    }
}
