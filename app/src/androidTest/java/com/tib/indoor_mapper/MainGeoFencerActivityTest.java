package com.tib.indoor_mapper;

import android.app.Activity;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainGeoFencerActivityTest {

    Activity activity = null;

    @Rule
    public ActivityTestRule<MainGeoFencerActivity> mActivityRule = new ActivityTestRule<>(MainGeoFencerActivity.class);

    @Before
    public void setUp() {
        activity = mActivityRule.getActivity();
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        activity.runOnUiThread(wakeUpDevice);
    }

    @Test
    public void buttonsShouldBeClickable() {
        onView(withId(R.id.button_log_start)).check(matches(isClickable()));
        onView(withId(R.id.button_log_stop)).check(matches(isClickable()));
        onView(withId(R.id.button_fence)).check(matches(isClickable()));
    }

    @Test
    public void shouldLoadGeoFenceActivityWhenGeoFenceButtonClicked() {
        onView(withId(R.id.button_fence)).perform(click());

        onView(withId(R.id.geofence_view)).check(matches(isDisplayed()));
    }

}
