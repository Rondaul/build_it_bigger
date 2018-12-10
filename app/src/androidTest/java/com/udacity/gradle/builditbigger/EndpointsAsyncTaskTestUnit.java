
package com.udacity.gradle.builditbigger;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndpointsAsyncTaskTestUnit {

    private static final String LOG_TAG = "AsyncTest";
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void testJokeIsNotEmpty() throws Exception {

        JokeAsyncTask.AsyncListener listener = new JokeAsyncTask.AsyncListener() {
            @Override
            public void changeProgressBarStatus(boolean isChanged) {

            }

            @Override
            public void startJokeActivity(String result) {

            }
        };
        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(listener);
        jokeAsyncTask.execute();
        String joke = jokeAsyncTask.get(10, TimeUnit.SECONDS);
        Log.d(LOG_TAG, "Retrieved a non-empty string successfully: " + joke);
        Assert.assertTrue(!joke.equals(""));
    }

    @Test
    public void testVerifyResponse() {
        onView(withId(R.id.tellJokeButton)).perform(click());
        onView(withId(R.id.tv_joke)).check(matches(isDisplayed()));
    }

}

