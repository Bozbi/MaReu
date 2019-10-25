package com.sbizzera.mareu;


import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.sbizzera.mareu.view.ListMeetingsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListMeetingsActivityTest {

    DateTimeFormatter mFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY,HH:mm:ss");

    @Rule
    public ActivityTestRule<ListMeetingsActivity> mActivityTestRule = new ActivityTestRule<>(ListMeetingsActivity.class);


    @Test
    public void insertMeetingWithSucces() {
        //Creating
        String title = String.format("Test#" + LocalDateTime.now().format(mFormatter));
        System.out.println(LocalDateTime.now());
        System.out.println(title);
        onView(withId(R.id.add_meeting_fab)).perform(click());
        onView(withId(R.id.edtxt_meeting_title)).perform(replaceText(title));
        onView(withId(R.id.container_location)).perform(click());
        onData(is("Peach")).perform(click());
        onView(withId(R.id.container_contacts)).perform(click());
        onView(withId(R.id.edtxt_add_contact)).perform(replaceText("bosbizz@oc.com"));
        onView(withId(R.id.txt_add_contact)).perform(click());
        onView(withContentDescription("Revenir en haut de la page")).perform(click());
        onView(withId(R.id.btn_save_meeting)).perform(click());
        //Check meeting has been created
        onView(withId(R.id.meeting_list_recycler_view)).check(matches(hasDescendant(withText(title))));
    }

    @Test
    public void onFabClick_AddMeetingActivity_isLaunchedWithSucces() {
        onView(withId(R.id.add_meeting_fab)).perform(click());
        onView(withId(R.id.edtxt_meeting_title)).check(matches(isDisplayed()));
    }

    @Test
    public void onAddContactCLick_AddContactActivity_isLaunched() {
        onView(withId(R.id.add_meeting_fab)).perform(click());
        onView(withId(R.id.container_contacts)).perform(click());
        onView(withId(R.id.edtxt_add_contact)).check(matches(isDisplayed()));
    }


}
