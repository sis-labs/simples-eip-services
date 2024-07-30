package io.sample.mnms.irritatedtown.services.eip.soaptorest.persons.processors;

import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FetchPersonProcessor extends BasePersonsProcessor implements Processor {
  public FetchPersonProcessor(final PersonsService personsService) {
    super(personsService);
  }

  @Override
  public void process(final Exchange exchange) throws Exception {
    final var message = exchange.getIn();
    final var personId = message.getHeader("id", String.class);
    final var person = personsService.findById(personId);
    if (person.isPresent()) {
      message.setBody(person.get());
    } else {
      exchange.setException(new RuntimeException("Person not found"));
    }
  }
}
