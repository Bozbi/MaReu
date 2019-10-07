package com.sbizzera.mareu.view;



import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.sbizzera.mareu.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListMeetingsActivityTest {

    @Rule
    public ActivityTestRule<ListMeetingsActivity> mActivityTestRule = new ActivityTestRule<>(ListMeetingsActivity.class);

    @Test
    public void listMeetingsActivityTest() {
        onView(withId(R.id.fab_add_meeting)).perform(click());
        onView(withId(R.id.edtxt_meeting_title)).check(matches(isDisplayed()));
    }

}
