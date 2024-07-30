package io.sample.mnms.irritatedtown.services.eip.soaptorest.processing;

import io.sample.mnms.irritatedtown.domain.services.InMemoryPersonService;
import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Simple processor used to fetch information about a given person.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
public class FetchPersonProcessor extends BasePersonsProcessor implements Processor {
  public FetchPersonProcessor(final PersonsService personsService) {
    super(personsService);
  }

  @Override
  public void process(final Exchange exchange) throws Exception {
    final var personId = exchange.getIn().getHeader("id", String.class);
    final var person = personsService.findById(personId);
    if (person.isPresent()) {
      exchange.getIn().setBody(person.get());
    } else {
      exchange.setException(new RuntimeException("Person not found"));
    }
  }
}
