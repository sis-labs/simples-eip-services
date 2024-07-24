package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import io.sample.mnms.irritatedtown.services.eip.soaptorest.processing.FetchPersonProcessor;
import io.sample.mnms.irritatedtown.services.eip.soaptorest.processing.FetchPersonsProcessor;
import org.apache.camel.builder.RouteBuilder;

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

    // Processing request to fetch the list of persons
    from("direct:requestPersons")
        .log("Request the list of persons")
        .process(new FetchPersonsProcessor())
        .log("Request successfully processed")
        .transform().body();

    // Processing request to fetch the details of a given person
    from("direct:requestPerson")
        .log("Request details about a person")
        .process(new FetchPersonProcessor())
        .transform().body();
  }
}
