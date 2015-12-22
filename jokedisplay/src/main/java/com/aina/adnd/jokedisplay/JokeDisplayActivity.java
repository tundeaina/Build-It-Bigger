package com.aina.adnd.jokedisplay;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class JokeDisplayActivity extends AppCompatActivity {

    public final static String JOKE = "NextJoke";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        Intent intent = this.getIntent();

        if(intent != null && intent.hasExtra(JOKE)) {

            Bundle bundle = new Bundle();
            bundle.putString(JOKE,intent.getStringExtra(JOKE));

            JokeDisplayFragment jokeDisplayFragment = new JokeDisplayFragment();
            jokeDisplayFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.joke_display_container, jokeDisplayFragment);
            transaction.commit();

        }
    }

    public void tellJoke(View view){
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        startActivity(intent);
    }
}
