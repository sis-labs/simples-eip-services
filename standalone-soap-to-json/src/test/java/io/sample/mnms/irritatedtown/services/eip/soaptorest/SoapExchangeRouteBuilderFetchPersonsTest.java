package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class SoapExchangeRouteBuilderFetchPersonsTest extends CamelTestSupport {

  private final PersonsService personsService = mock(PersonsService.class);

  @Override
  protected RoutesBuilder createRouteBuilder() throws Exception {
    return new SoapExchangeRouteBuilder(personsService);
  }

  @Override
  protected void doPostSetup() throws Exception {
    // use doPostSetup instead of setupResources since we have to use
    // the camel context which is created after the invocation of the
    // setupResource but before the doPostSetup.
    AdviceWith.adviceWith(context, SoapExchangeRouteBuilder.ID, a -> {
      a.onCompletion().onCompleteOnly().to("mock:result");
    });
  }

  @Override
  protected void cleanupResources() throws Exception {
    super.cleanupResources();
  }

  @Test
  void validate() throws InterruptedException {
    // GIVEN
    final var personId = UUID.randomUUID();
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jane.doe@mydom.com";
    final var physicalPersons = List.of(new PhysicalPerson(personId, firstName, lastName, email));
    when(personsService.fetchPersons()).thenReturn(physicalPersons);
    final var mockEndpoint = getMockEndpoint("mock:result");

    mockEndpoint.expectedMessageCount(1);
    final var message = mockEndpoint.message(0);
    message.body().isEqualTo(physicalPersons);

    // WHEN
    template.sendBody("direct:requestPersons", null);

    // THEN
    MockEndpoint.assertIsSatisfied(context);
    verify(personsService, times(1)).fetchPersons();
  }
}
