package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EndpointsAndroidTest extends AndroidTestCase {

    Context mContext;
    private static final String LOG_TAG = EndpointsAndroidTest.class.getSimpleName();

    @Override
    protected void setUp() throws Exception {
        mContext = getContext();
    }

    public void testVerifyEndpointsAsyncTaskResult() {
        String joke = "";
        try {
            JokeFetcher jokeTask = new JokeFetcher(new JokeFetcher.JokeFetcherListener() {
                @Override
                public void jokeFetched(String joke) {
                    //Empty implementation:
                    //Test will finish before any code in here could be run
                }
            });
            Random rand = new Random();
            joke = jokeTask.fetchJoke(rand.nextInt(11)).get(30, TimeUnit.SECONDS);
        } catch (Exception e){
            fail("Timed out");
        }

        assertNotNull("Joke fetched from server is null!", joke);
        assertTrue("Joke fetched from server has zero length!", joke.length() > 0);
    }
}
