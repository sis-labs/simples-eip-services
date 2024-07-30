package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import io.sample.mnms.irritatedtown.domain.services.PersonsService;
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
  private final PersonsService personsService;

  public SoapExchangeRouteBuilder(final PersonsService personsService) {
    super();
    this.personsService = personsService;
  }

  @Override
  public void configure() throws Exception {

    // Processing request to fetch the list of persons
    from("direct:requestPersons")
        .id(ID)
        .log("Request the list of persons")
        .process(new FetchPersonsProcessor(personsService))
        .log("Request successfully processed")
        .transform().body();

    // Processing request to fetch the details of a given person
    from("direct:requestPerson")
        .log("Request details about a person")
        .process(new FetchPersonProcessor(personsService))
        .transform().body();
  }
}
