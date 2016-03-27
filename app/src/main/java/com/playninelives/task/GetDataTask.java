package com.playninelives.task;

import android.os.AsyncTask;

import java.net.URL;

public abstract class GetDataTask<T> extends NineLivesTask<URL, String, T> {

    private Class<T> c = null;

    public GetDataTask() {
        this(null);
    }

    public GetDataTask(Class<T> c) {
        super();
        this.c = c;
    }

    public AsyncTask<URL, String, T> execute(String... s) {
        return super.execute(helper.createUrl(s));
    }

    public AsyncTask<URL, String, T> execute() {
        return execute("");
    }

    @Override
    protected T doInBackground(URL... urls) {
        String data = helper.downloadUrl(urls[0]);
        return helper.gson.fromJson(data, c);
    }

}
