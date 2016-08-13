/*
 * ok-android-testing
 * https://github.com/dherges/ok-android-testing
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */

package ok.android.testing.auth;

public class AuthenticationException extends Exception {

  public AuthenticationException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

}
