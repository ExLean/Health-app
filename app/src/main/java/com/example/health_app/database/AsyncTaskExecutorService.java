package com.example.health_app.database;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Because AsyncTask is deprecated
public abstract class AsyncTaskExecutorService < Params, Progress, Result > {

    private ExecutorService executor;
    private Handler handler;

    protected AsyncTaskExecutorService() {
        executor = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public Handler getHandler() {
        if (handler == null) {
            synchronized(AsyncTaskExecutorService.class) {
                handler = new Handler(Looper.getMainLooper());
            }
        }
        return handler;
    }

    protected void onPreExecute() {
        // Override this method whereever you want to perform task before background execution get started
    }

    protected abstract Result doInBackground(Params[] params) throws MalformedURLException;

    protected abstract void onPostExecute(Result result);

    protected void onProgressUpdate(@NotNull Progress value) {
        // Override this method whereever you want update a progress result
    }

    // used for push progress resport to UI
    public void publishProgress(@NotNull Progress value) {
        getHandler().post(() -> onProgressUpdate(value));
    }

//    public void execute(String type, String username, String password) {
//        execute(null);
//    }

    public void execute(Params... params) {
        getHandler().post(() -> {
            onPreExecute();
            executor.execute(() -> {
                Result result = null;
                try {
                    result = doInBackground(params);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Result finalResult = result;
                getHandler().post(() -> onPostExecute(finalResult));
            });
        });
    }

    public void shutDown() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }

    public boolean isCancelled() {
        return executor == null || executor.isTerminated() || executor.isShutdown();
    }
}
