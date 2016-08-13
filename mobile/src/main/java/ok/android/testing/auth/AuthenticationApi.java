/*
 * ok-android-testing
 * https://github.com/dherges/ok-android-testing
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */

package ok.android.testing.auth;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Retrofit interface for an authentication API
 */
public interface AuthenticationApi {

  @POST("login")
  @FormUrlEncoded
  Call<ResponseBody> login(@Field("user") String username, @Field("pass") String password);

}
