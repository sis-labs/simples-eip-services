package io.sample.mnms.irritatedtown.services.eip.soaptorest.persons;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Route which defines rest endpoint to presents to clients.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class PersonsRestServiceRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    // Creating rest web service from raw using '/rest' as base context.
    // We are using '/rest' for rest endpoints and '/soap' for SOAP endpoint in this project.
    rest("/rest")

        // Route to fetch the list of persons
        .get("/persons")
        .apiDocs(true)
        .consumes("application/json")
        .produces("application/json")
        .to("direct:requestPersons")

        // Route to fetch a person using his id.
        .get("/persons/{id}")
        .apiDocs(true)
        .consumes("application/json")
        .produces("application/json")
        .to("direct:requestPerson");
  }
}
