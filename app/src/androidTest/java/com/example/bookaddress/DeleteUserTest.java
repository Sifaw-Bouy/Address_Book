package com.example.bookaddress;
import android.content.Context;
import android.os.SystemClock;


import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class DeleteUserTest {
    @Rule
    public ActivityTestRule<MainActivity> Rule =
            new ActivityTestRule<>(MainActivity.class);

    /**
     * This looks at the avaible users and choose the first
     * choice and deletes it
     * @return void
     */
    @Test
    public void deleteUserTest() {
        //helps let the user information to load first then perform the action
        SystemClock.sleep(1000);
        //This deletes the first user information in the recycleView
        Espresso.onView(ViewMatchers.withId(R.id.userListView)).
                perform(RecyclerViewActions.actionOnItemAtPosition(0,ViewActions.click()));
        Espresso.onView(ViewMatchers.withId(R.id.deleteUser)).
                perform(ViewActions.click());
    }
}
