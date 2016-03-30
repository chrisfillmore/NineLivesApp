package com.playninelives.task;

import android.os.AsyncTask;

public abstract class NineLivesTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    protected NineLivesTaskHelper helper;

    public NineLivesTask() {
        super();
        helper = new NineLivesTaskHelper(this);

    }

    public abstract String getPath();

}
