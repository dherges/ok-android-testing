package ok.android.testing;

import com.orhanobut.mockwebserverplus.MockWebServerPlus;

import org.junit.Rule;
import org.junit.Test;

import ok.android.testing.auth.AuthenticationApi;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationApiTest {

  @Rule
  public MockWebServerPlus mockWebServer = new MockWebServerPlus();

  private AuthenticationApi mockApi() {
    return new AuthenticationApi.Builder()
      .baseUrl(mockWebServer.url("/"))
      .build();
  }

  @Test
  public void builder_returnsInstance() throws Exception {
    assertThat(mockApi()).isNotNull();
  }

  @Test
  public void login_200Ok() throws Exception {
    mockWebServer.enqueue(new MockResponse().setResponseCode(200));

    Response<ResponseBody> response = mockApi().login("foo", "bar").execute();

    assertThat(response.code()).isEqualTo(200);
    final RecordedRequest recordedRequest = mockWebServer.takeRequest();
    assertThat(recordedRequest.getMethod()).isEqualTo("POST");
    assertThat(recordedRequest.getPath()).isEqualTo("/login");
    assertThat(recordedRequest.getBody().readUtf8()).isEqualTo("user=foo&pass=bar");
  }

}
