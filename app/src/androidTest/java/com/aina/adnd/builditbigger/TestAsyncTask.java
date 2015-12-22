package com.aina.adnd.builditbigger;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Tunde Aina on 12/16/2015.
 */
public class TestAsyncTask extends AndroidTestCase {

    public static final String LOG_TAG = TestAsyncTask.class.getSimpleName();
    CountDownLatch signal;
    String mResult;

    @Override
    public void setUp() throws Exception{
        super.setUp();

        signal = new CountDownLatch(1);
    }

    public void testGetJoke() {


        // Register mMessageReceiver to receive messages.
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,
                new IntentFilter("JokeRetrieved"));

        JokesEndpointAsyncTask jokesEndpointAsyncTask = new JokesEndpointAsyncTask();
        jokesEndpointAsyncTask.execute(getContext());

        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, "Joke: " + mResult.replace("\r","") + "\nLength: "
                + Integer.toString(mResult.length())
                + " characters");

        assertTrue(null != mResult && mResult.length() > 0);

        // Unregister since the activity is not visible
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);

    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // Extract data included in the Intent
            String result = intent.getStringExtra("Joke");

            mResult = result;

            signal.countDown();
        }
    };
}
