package com.playninelives.task;

import android.os.AsyncTask;

import com.playninelives.activity.TaskContext;

public abstract class NineLivesTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    protected NineLivesTaskHelper helper;
    protected TaskContext<?, Result> context;

    public NineLivesTask() {
        this(null);
    }

    public NineLivesTask(TaskContext<?, Result> context) {
        super();
        helper = new NineLivesTaskHelper(this);
        this.context = context;
    }

    public abstract String getPath();

    @Override
    protected void onPostExecute(Result result) {
        if (context != null) {
            context.onPostExecute(result);
        }
    }

}
