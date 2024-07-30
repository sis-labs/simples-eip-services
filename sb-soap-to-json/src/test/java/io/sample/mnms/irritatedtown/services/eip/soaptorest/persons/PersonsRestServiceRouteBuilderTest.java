package io.sample.mnms.irritatedtown.services.eip.soaptorest.persons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonsRestServiceRouteBuilderTest {

  @Test
  void checkHappyPath() {
    // TODO: complete the implementation of the test

    // GIVEN
    final var hostname = "localhost";
    final var port = 8084;

    // WHEN
    final var route = new PersonsRestServiceRouteBuilder(hostname, port);

    // THEN
    assertNotNull(route);
  }
}