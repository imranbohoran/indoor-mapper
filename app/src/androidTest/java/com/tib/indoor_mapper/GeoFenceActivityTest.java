package com.tib.indoor_mapper;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class GeoFenceActivityTest {

    @Rule
    public ActivityTestRule<GeoFenceActivity> activityTestRule = new ActivityTestRule<>(GeoFenceActivity.class);

    @Test
    public void shouldRenderGeoFenceActivityWithRequiredComponents() {
        onView(withId(R.id.text_lng)).check(matches(isDisplayed()));
        onView(withId(R.id.text_lat)).check(matches(isDisplayed()));
        onView(withId(R.id.button_alert)).check(matches(isDisplayed()));
    }


    @Test
    public void alertButtonClickable() {
        onView(withId(R.id.button_alert)).check(matches(isClickable()));
    }
}
