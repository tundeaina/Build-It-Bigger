package com.aina.adnd.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.aina.adnd.jokeserver.jokesApi.JokesApi;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Tunde Aina on 12/15/2015.
 */
public class JokesEndpointAsyncTask extends AsyncTask<Context, Void, String> {
    private static JokesApi jokeApi = null;
    private Context context;
    public static final String LOG_TAG = JokesEndpointAsyncTask.class.getSimpleName();

    @Override
    protected String doInBackground(Context... params) {
        if(jokeApi == null) {
            /*
            // Only do this once
            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
                    // end options for devappserver
            */

            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://android-nanodegree-joker.appspot.com/_ah/api/");

            jokeApi = builder.build();
        }

        context = params[0];

        try {
            return jokeApi.nextJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        Intent intent = new Intent("JokeRetrieved");
        // add data
        intent.putExtra("Joke", result);
        Log.d(LOG_TAG, "mResult: ------------> " + result);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
