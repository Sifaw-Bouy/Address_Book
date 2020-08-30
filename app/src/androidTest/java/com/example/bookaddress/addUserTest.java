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

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class addUserTest {
    /**
     * This sets up the view of the app for testing
     */
    @Rule
    public ActivityTestRule<MainActivity> Rule =
            new ActivityTestRule<>(MainActivity.class);

    /**
     * This test the inputs when the user click 'Add'
     * to create a new user
     * @return void
     */
    @Test
    public void createUserTest() {
        Espresso.onView(ViewMatchers.withId(R.id.createUser)).
                perform(ViewActions.click());
        //After the button click Espresso notices the view change to create user page/view
        Espresso.onView(ViewMatchers.withId(R.id.FirstName)).
                perform(ViewActions.replaceText("Test"));
        Espresso.onView(ViewMatchers.withId(R.id.LastName)).
                perform(ViewActions.replaceText("Johnny"));
        Espresso.onView(ViewMatchers.withId(R.id.addressInput)).
                perform(ViewActions.replaceText("5576 Test DD road HelloWord 22042"));
        Espresso.onView(ViewMatchers.withId(R.id.sendToDatabase)).
                perform(ViewActions.click());

        //SystemClock.sleep(1000); // gives the human time to view

    }


}