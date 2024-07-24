package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * Route Builder Dedicated to create SOAP Client endpoint.
 *
 * @author mgaspoz
 * @version 0.0.1
 * @since 0.0.1
 */
public class SoapExchangeRouteBuilder extends RouteBuilder {
  @Override
  public void configure() throws Exception {

    /*from("timer://foo?fixedRate=true&period=400")
        .setBody(constant("This is the payload"))
        .log("${body}")
        // TODO: parameterize the output location.
        .to("direct:timerExchange");*/

    // Processing request to fetch the list of persons
    from("direct:requestPersons")
        .log("Request the list of persons")
        .transform().constant("Hello World");

    // Processing request to fetch the details of a given person
    from("direct:requestPerson")
        .log("Request details about a person")
        .transform().constant("Bye World");
  }
}
