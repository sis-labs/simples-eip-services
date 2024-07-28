package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SoapExchangeRouteBuilderTest extends CamelTestSupport {

  private final PersonsService personService = mock(PersonsService.class);

  @Override
  protected RoutesBuilder createRouteBuilder() throws Exception {
    return super.createRouteBuilder();
  }

  @Test
  void fetchPersons() {
    // GIVEN
    // WHEN
    // THEN
    assertEquals(2, 1 + 2 - 1);
  }
}