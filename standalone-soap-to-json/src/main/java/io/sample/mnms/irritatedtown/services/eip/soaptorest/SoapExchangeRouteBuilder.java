package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import org.apache.camel.builder.RouteBuilder;

public class SoapExchangeRouteBuilder extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("timer://foo?fixedRate=true&period=400")
        .setBody(constant("This is the payload"))
        .log("${body}")
        // TODO: parameterize the output location.
        .to("file:C:\\Temp\\check");

    from("rest:get:hello/{me}")
        .transform().simple("Bye ${header.me}");
  }
}
