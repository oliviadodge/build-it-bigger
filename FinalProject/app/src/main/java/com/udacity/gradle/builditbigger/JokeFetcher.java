package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.olivi.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by olivi on 10/19/2015.
 */
public class JokeFetcher {
    private JokeFetcherListener jokeFetcherListener;

    public JokeFetcher(JokeFetcherListener jokeFetcherListener) {
        this.jokeFetcherListener = jokeFetcherListener;
    }

    public interface JokeFetcherListener {
        void jokeFetched(String joke);
    }

    public EndpointsAsyncTask fetchJoke(Integer index) {
        EndpointsAsyncTask jokeTask = new EndpointsAsyncTask();
        jokeTask.execute(index);
        return jokeTask;
    }

    public class EndpointsAsyncTask extends AsyncTask<Integer, Void, String> {
        private MyApi myApi = null;
        private Integer index;

        @Override
        protected String doInBackground(Integer... params) {
            if (myApi == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("https://builditbigger-1097.appspot.com/_ah/api/");
                // end options for devappserver

                myApi = builder.build();
            }

            index = params[0];

            try {
                return myApi.tellJoke(index).execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            jokeFetcherListener.jokeFetched(result);
        }
    }
}
