package io.sample.mnms.irritatedtown.services.eip.soaptorest.processing;

import io.sample.mnms.irritatedtown.domain.services.InMemoryPersonService;
import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * This processor is dedicated to fetch the list of persons from the remote service.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
public class FetchPersonsProcessor extends BasePersonProcessor implements Processor {
  /**
   * Create a new instance of the processor.
   *
   * @param personsService the reference to the {@link PersonsService} to use inside the processor.
   */
  public FetchPersonsProcessor(final PersonsService personsService) {
    super(personsService);
  }

  @Override
  public void process(final Exchange exchange) {
    // The implementation is pretty straightforward since we are in the POC phase. The final implementation will be
    // more detailed. By the way, you have to take care about the location of the logic you are implementing since the
    // common-client is designed to embed common business logic related to the client side (infrastructure side) of the
    // service. The common-domain may also use to implement business logic related to the domain.
    // Note: keep variable / call separated in order to let unit tests behave correctly
    final var persons = personsService.fetchPersons();
    exchange.getIn().setBody(persons);
  }
}
