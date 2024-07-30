package io.sample.mnms.irritatedtown.services.eip.soaptorest.persons;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
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

  private final String hostname;
  private final int port;

  public PersonsRestServiceRouteBuilder(@Value("${application.rest.hostname}") final String hostname,
                                        @Value("${application.rest.port}") final int port) {
    this.hostname = hostname;
    this.port = port;
  }

  @Override
  public void configure() throws Exception {
    restConfiguration().component("jetty")
        .host(hostname)
        .port(port)
        .bindingMode(RestBindingMode.json);
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
