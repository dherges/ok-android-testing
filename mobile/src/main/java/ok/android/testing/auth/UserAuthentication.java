/*
 * ok-android-testing
 * https://github.com/dherges/ok-android-testing
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */

package ok.android.testing.auth;


import java.io.IOException;

public class UserAuthentication {

  private final AuthenticationApi authenticationApi;

  public UserAuthentication(AuthenticationApi authenticationApi) {
    this.authenticationApi = authenticationApi;
  }


  public boolean validateLogin(String username, String password) throws AuthenticationException {

    try {
      return authenticationApi
        .login(username, password)
        .execute()
        .code() == 200;
    } catch (IOException e) {
      throw new AuthenticationException("Could not connect to remote API", e);
    }
  }

  public boolean isEmailValid(String email) {
    //TODO: Replace this with your own logic
    return email.contains("@");
  }

  public boolean isPasswordValid(String password) {
    //TODO: Replace this with your own logic
    return password.length() > 4;
  }

}
