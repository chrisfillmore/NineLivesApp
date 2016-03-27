package com.playninelives.activity;

import com.playninelives.task.NineLivesTask;

/**
 * Created by computer on 2016-03-27.
 */
public interface TaskContext<Task extends NineLivesTask, Result> {
    void onPostExecute(Result result);
}
