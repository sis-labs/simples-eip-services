package io.sample.mnms.irritatedtown.services.eip.soaptorest.persons;

import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import io.sample.mnms.irritatedtown.services.eip.soaptorest.persons.processors.FetchPersonProcessor;
import io.sample.mnms.irritatedtown.services.eip.soaptorest.persons.processors.FetchPersonsProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Route to handle messages to forward to the remote person service.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class PersonsSoapServiceRouteBuilder extends RouteBuilder {

  /**
   * Unique id of the route in the camel context
   */
  public static final String ID = "PERSONS_SOAP_EXCHANGE";

  /**
   * Instance of the service to use to interact with persons
   */
  private final PersonsService personsService;

  /**
   * Create a new instance of the route.
   *
   * @param personsService the instance of the service to use to interact with persons,
   */
  public PersonsSoapServiceRouteBuilder(final PersonsService personsService) {
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
