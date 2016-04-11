package com.playninelives.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.playninelives.R;
import com.playninelives.task.NineLivesTask;
import com.playninelives.util.Session;

import java.net.URL;

public class CreatePoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pool);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Button createPoolButton = (Button)findViewById(R.id.btnCreatePool);
        createPoolButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = getTextFromInput(R.id.inputPoolName);
                String password = getTextFromInput(R.id.inputPoolPassword);
                CreatePoolTask task = new CreatePoolTask(name, password);
                task.execute();
                Snackbar.make(v, "Pool Created", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(CreatePoolActivity.this, PoolListActivity.class);
                startActivity(i);
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private String getTextFromInput(int id) {
        return ((TextView)findViewById(id)).getText().toString();
    }

    private class CreatePoolTask extends NineLivesTask<URL, Void, Void> {

        String name;
        String password;

        public CreatePoolTask(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public void execute() {
            String userId = String.valueOf(new Session(CreatePoolActivity.this).getUserId());
            super.execute(helper.createUrl(name, userId, password));
        }

        @Override
        public String getPath() {
            return "pool/";
        }

        @Override
        protected Void doInBackground(URL... urls) {
            String response = helper.downloadUrl(urls[0]);
            return null;
        }

    }

}
