package com.aina.adnd.jokedisplay;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeDisplayFragment extends Fragment {


    public JokeDisplayFragment() {
        // Required empty public constructor
    }

    private String mJoke;
    TextView jokeTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();

        if (args != null) {
            mJoke = args.getString(JokeDisplayActivity.JOKE);
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_joke_display, container, false);

        jokeTextView = (TextView) rootView.findViewById(R.id.jokeDisplayTextView);
        jokeTextView.setText(mJoke);

        return rootView;
    }

}
