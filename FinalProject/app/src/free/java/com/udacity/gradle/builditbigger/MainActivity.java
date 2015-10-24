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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;


public class MainActivity extends ActionBarActivity implements JokeFetcher.JokeFetcherListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    //a member field that will get random joke indices
    //so we can *often* get a different joke on each button click
    boolean mStopSpinner;
    InterstitialAd mInterstitialAd;
    Random mJokeIndexRandGen;
    ProgressBar mSpinner;
    TextView mInstructionsTextView;
    Button mTellJokeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "onCreate() called");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                startJokeTask();
            }
        });
        requestNewInterstitial();

        mJokeIndexRandGen = new Random();
        Log.i(LOG_TAG, "MainActivity started and loaded");
    }



    //Helper method to start loading the interstitial ad
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void tellJoke(View view) {
        setUpViewReferences(view);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            startSpinner();
        } else {
            startJokeTask();
        }
    }

    private void setUpViewReferences(View view) {
        if (view != null) {
            View rootView = view.getRootView();
            mTellJokeButton = (Button) view;
            mInstructionsTextView = (TextView) rootView.findViewById(R.id.instructions_text_view);
            mSpinner = (ProgressBar) rootView.findViewById(R.id.progress_bar_joke);
            Log.i(LOG_TAG, "view references set up and mSpinner is not null " + (mSpinner != null));
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
    protected void onResume() {
        super.onResume();

        if (mStopSpinner) {
            stopSpinner();
            mStopSpinner = false;
        }
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

    public void startJokeTask() {
        startSpinner();
        new JokeFetcher(this).fetchJoke(mJokeIndexRandGen.nextInt(11));
    }

    @Override
    public void jokeFetched(String joke) {
        Log.i(LOG_TAG, "joke fetched! " + joke);
        Intent i = new Intent(this, JokeActivity.class);
        i.putExtra(JokeActivity.EXTRA_JOKE, joke);
        startActivity(i);
        mStopSpinner = true;
    }

}
