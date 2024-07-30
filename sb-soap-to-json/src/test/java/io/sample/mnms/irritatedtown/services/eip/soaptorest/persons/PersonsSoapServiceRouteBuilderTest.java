package io.sample.mnms.irritatedtown.services.eip.soaptorest.persons;

import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class PersonsSoapServiceRouteBuilderTest {
  @Test
  void checkHappyPath() {
    // TODO: complete the implementation of the test.
    // GIVEN
    final var personService = mock(PersonsService.class);

    // WHEN
    final var route = new PersonsSoapServiceRouteBuilder(personService);

    // THEN
    assertNotNull(route);
  }
}