package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.olivi.androidjokelibrary.JokeActivity;

import java.util.Random;


public class MainActivity extends ActionBarActivity implements JokeFetcher.JokeFetcherListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    //a member field that will get random joke indices
    //so we can *often* get a different joke on each button click
    Random mJokeIndexRandGen;

    ProgressBar mSpinner;
    TextView mInstructionsTextView;
    Button mTellJokeButton;
    boolean mStopSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJokeIndexRandGen = new Random();
        Log.i(LOG_TAG, "MainActivity started and loaded");
    }

    public void tellJoke(View view) {
        setUpViewReferences(view);
        startSpinner();
        new JokeFetcher(this).fetchJoke(mJokeIndexRandGen.nextInt(11));
    }

    private void setUpViewReferences(View view) {
        if (view != null) {
            View rootView = view.getRootView();
            mTellJokeButton = (Button) view;
            mInstructionsTextView = (TextView) rootView.findViewById(R.id.instructions_text_view);
            mSpinner = (ProgressBar) rootView.findViewById(R.id.progress_bar_joke);
        }
    }

    private void startSpinner() {
        if (mTellJokeButton != null) {
            mTellJokeButton.setVisibility(View.GONE);
            mInstructionsTextView.setVisibility(View.GONE);
            mSpinner.setVisibility(View.VISIBLE);
        }
    }

    private void stopSpinner() {
        if (mSpinner != null) {
            mSpinner.setVisibility(View.GONE);
            mInstructionsTextView.setVisibility(View.VISIBLE);
            mTellJokeButton.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void jokeFetched(String joke) {
        Log.i(LOG_TAG, "joke fetched! " + joke);
        Intent i = new Intent(this, JokeActivity.class);
        i.putExtra(JokeActivity.EXTRA_JOKE, joke);
        startActivity(i);
        mStopSpinner = true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mStopSpinner) {
            stopSpinner();
            mStopSpinner = false;
        }
    }
}
