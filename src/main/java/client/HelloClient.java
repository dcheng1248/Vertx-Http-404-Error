package client;

import entity.HELLO_REQUEST;
import entity.HELLO_RESPONSE;
import entity.HelloService;
import io.quarkiverse.cxf.annotation.CXFClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HelloClient {

  private final HelloService helloService;

  @Inject
  public HelloClient(
      @CXFClient("hello-client") HelloService helloService) {
    this.helloService = helloService;
  }

  public HELLO_RESPONSE getGreeting(String name) {

    HELLO_REQUEST request = new HELLO_REQUEST();
    request.setNAME("Bob");

    return helloService.sayHello(request);
  }
}
