package com.aina.adnd.builditbigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aina.adnd.Joke;
import com.aina.adnd.jokedisplay.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private Joke joke = new Joke();
    private final static String JOKE = "NextJoke";
    MainActivityFragment mainActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityFragment = new MainActivityFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_main_container, mainActivityFragment);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register mMessageReceiver to receive messages.
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("JokeRetrieved"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister since the activity is not visible
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){

        fetchJoke();
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            mainActivityFragment.stopSpinner();

            // Extract data included in the Intent
            String result = intent.getStringExtra("Joke");
            Log.d(LOG_TAG, "Joke Received: " + result.replace("\r",""));

            intent = new Intent(context, JokeDisplayActivity.class);
            intent.putExtra(JokeDisplayActivity.JOKE, result);
            startActivity(intent);
        }
    };


    private void fetchJoke() {
        if (isNetworkAvailable()) {

            JokesEndpointAsyncTask jokesEndpointAsyncTask = new JokesEndpointAsyncTask();
            jokesEndpointAsyncTask.execute(getApplicationContext());

            mainActivityFragment.startSpinner();

        } else {
            Toast.makeText(this
                    , getResources().getString(R.string.prompt_connectivity)
                    , Toast.LENGTH_LONG).show();
        }
    }

    //Based on a stackoverflow snippet
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
