package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * Route Builder Dedicated to create REST endpoint.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
public class RestServiceRouteBuilder extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    // basic configuration to expose web service with Apache Camel.
    restConfiguration().component("jetty").host("localhost").port(8081).bindingMode(RestBindingMode.json);

    // Creating rest web service from raw using '/rest' as base context.
    // We are using '/rest' for rest endpoints and '/soap' for SOAP endpoint in this project.
    rest("/rest")

        // Route to fetch the list of persons
        .get("/persons")
        .consumes("application/json")
        .produces("application/json")
        .to("direct:requestPersons")

        // Route to fetch a person using his id.
        .get("/persons/{id}")
        .consumes("application/json")
        .produces("application/json")
        .to("direct:requestPerson");
  }
}
