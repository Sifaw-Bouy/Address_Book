package com.example.bookaddress;
import android.content.Context;


import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;



import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class UpdateUserTest {
    /**
     * This sets up the view of the app for testing
     */
    @Rule
    public ActivityTestRule<updateUser> Rule =
            new ActivityTestRule<>(updateUser.class);

    /**
     * This test that the user update method works
     * @return void
     */
    @Test
    public void updateUserTest() {
        Espresso.onView(ViewMatchers.withId(R.id.updateFirstN)).
                perform(ViewActions.replaceText("Test1"));
        Espresso.onView(ViewMatchers.withId(R.id.updateLastN)).
                perform(ViewActions.replaceText("Johnny1"));
        Espresso.onView(ViewMatchers.withId(R.id.upDate)).
                perform(ViewActions.click());
    }

}
