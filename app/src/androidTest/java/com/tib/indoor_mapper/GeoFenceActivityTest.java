package com.tib.indoor_mapper;


import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

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

    @Test
    public void alertButtonClickShouldCaptureCoordinates() {
        onView(withId(R.id.text_lng)).perform(ViewActions.typeText("1.34"));
        onView(withId(R.id.text_lat)).perform(ViewActions.typeText("0.34"));
        onView(withId(R.id.button_alert)).perform(click());

        onView(withId(R.id.txt_info_current_location)).check(matches(withText("Current set location - Lng: 1.34, Lat: 0.34")));

    }
}
