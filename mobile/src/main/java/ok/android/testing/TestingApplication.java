/*
 * ok-android-testing
 * https://github.com/dherges/ok-android-testing
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */

package ok.android.testing;

import ok.android.testing.auth.AuthenticationApi;
import ok.android.testing.auth.UserAuthentication;
import retrofit2.Retrofit;

/**
 * A demo application that is going to be tested.
 */
public class TestingApplication extends android.app.Application {

  public static String AUTH_BASE_URL = "";
  private AuthenticationApi authenticationApi;
  private UserAuthentication userAuthentication;

  public AuthenticationApi authenticationApi() {
    if (authenticationApi == null) {
      authenticationApi = new Retrofit.Builder()
        .baseUrl(AUTH_BASE_URL)
        .build()
        .create(AuthenticationApi.class);
    }

    return authenticationApi;
  }

  public UserAuthentication userAuthentication() {
    if (userAuthentication == null) {
      userAuthentication = new UserAuthentication(authenticationApi());
    }

    return userAuthentication;
  }

}
