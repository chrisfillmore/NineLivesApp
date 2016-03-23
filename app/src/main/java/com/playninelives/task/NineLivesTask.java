package com.playninelives.task;

import android.os.AsyncTask;

/**
 * Created by computer on 2016-03-09.
 */
public abstract class NineLivesTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    protected NineLivesTaskHelper helper;

    public NineLivesTask() {
        super();
        helper = new NineLivesTaskHelper(this);
    }

    public abstract String getPath();

}
