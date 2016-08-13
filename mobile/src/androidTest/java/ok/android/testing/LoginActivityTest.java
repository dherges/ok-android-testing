/*
 * ok-android-testing
 * https://github.com/dherges/ok-android-testing
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */

package ok.android.testing;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.orhanobut.mockwebserverplus.MockWebServerPlus;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

  private String mStringToBetyped;

  @Rule
  public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

  @Rule
  public MockWebServerPlus mockWebServerPlus = new MockWebServerPlus();

  @Before
  public void initValidString() {
    // Specify a valid string.
    mStringToBetyped = "Espresso";
  }

  @Test
  public void enterText() {
    // Type text and then press the button.
    onView(withId(R.id.email))
      .perform(typeText(mStringToBetyped), closeSoftKeyboard());
    onView(withId(R.id.password))
      .perform(typeText(mStringToBetyped), closeSoftKeyboard());

    onView(withId(R.id.email_sign_in_button)).perform(click());

    // Check that the text was changed.
    onView(withId(R.id.email))
      .check(matches(withText(mStringToBetyped)));
  }

}
