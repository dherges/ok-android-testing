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

import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static ext.espresso.TextViewMatcher.withError;
import static org.assertj.core.api.Assertions.fail;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

  private String mockUsername;
  private String mockPassword;

  @Rule
  public MockWebServerPlus mockWebServerPlus = new MockWebServerPlus();

  @Rule
  public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

  @Before
  public void initValidString() {
    // Specify a valid string.
    mockUsername = "ok@testing";
    mockPassword = "Espresso";

    TestingApplication.AUTH_BASE_URL = mockWebServerPlus.url("/");
  }

  @Test
  public void login_success() {
    mockWebServerPlus.server().enqueue(new MockResponse().setResponseCode(200));

    // Type text and then press the button.
    onView(withId(R.id.email))
      .perform(typeText(mockUsername));
    onView(withId(R.id.password))
      .perform(typeText(mockPassword));
    closeSoftKeyboard();
    onView(withId(R.id.sign_in_button))
      .perform(click());

    // Check that the text was changed.
    onView(withId(R.id.password))
      .check(matches(withError("This password is")));
  }

  @Test
  public void login_failure() {
    fail("Not yet implemented", new RuntimeException());
  }

}
