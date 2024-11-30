import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.CountMatchingStrategy;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public final class MockHelloTestResource implements QuarkusTestResourceLifecycleManager {

  private static WireMockServer wireMockServer;

  public static final String BASE_PATH = "/hello";
  public static final String GREETING_PATH = "/greeting";

  @Override
  public Map<String, String> start() {
    wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
    wireMockServer.start();

    try {
      mockRedirect("Bob");
      mockRedirectFinal("Bob");
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }

    Map<String, String> serverConfig = new HashMap<>();
    serverConfig.put("test.baseurl", wireMockServer.baseUrl() + BASE_PATH);
    return serverConfig;
  }

  @Override
  public void stop() {
    if (null != wireMockServer) {
      wireMockServer.stop();
    }
  }

  public static void verify(
      CountMatchingStrategy count, RequestPatternBuilder requestPatternBuilder) {
    wireMockServer.verify(count, requestPatternBuilder);
  }

  public static List<ServeEvent> getEvents() {
    return wireMockServer.getAllServeEvents();
  }

  public static void resetRequestCount() {
    wireMockServer.resetRequests();
  }

  private void mockRedirect(String name) throws IOException {
    wireMockServer.stubFor(
            WireMock.post(urlPathEqualTo(BASE_PATH + GREETING_PATH))
//                    .withHeader("SoapAction", containing("HELLO"))
//                    .withRequestBody(matchingXPath("//NAME/text()", matching(name)))
                    .willReturn(
                            aResponse()
                                    .withStatus(302)
                                    .withHeader("location", wireMockServer.baseUrl() + BASE_PATH + "/redirect-test")));
  }

  private void mockRedirectFinal(String name) throws IOException {
    wireMockServer.stubFor(
            WireMock.post(urlPathEqualTo(BASE_PATH + "/redirect-test"))
                    .withHeader("SoapAction", containing("HELLO"))
                    .withRequestBody(matchingXPath("//NAME/text()", matching(name)))
                    .willReturn(
                            aResponse()
                                    .withStatus(200)
                                    .withHeader("content-type", "application/xml")
                                    .withBodyFile(String.format("greeting_%s_response.xml", name))));
  }
}
