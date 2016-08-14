/*
 * ok-android-testing
 * https://github.com/dherges/ok-android-testing
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */

package ext.espresso;

import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class TextViewMatcher {


  public static Matcher<View> withError(final String expected) {
    return new TypeSafeMatcher<View>() {

      @Override
      public boolean matchesSafely(View view) {
        if (!(view instanceof EditText)) {
          return false;
        }
        EditText editText = (EditText) view;
        return editText.getError().toString().equals(expected);
      }

      @Override
      public void describeTo(Description description) {

      }
    };
  }
}
