package io.sample.mnms.irritatedtown.services.eip.soaptorest.processing;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import io.service.sample.FetchPersons;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FetchPersonsProcessorTest {
  @Mock
  private PersonsService personService;

  @InjectMocks
  private FetchPersonsProcessor processor;

  @SuppressWarnings("unchecked")
  @Test
  void fetchPersons() {
    // GIVEN
    final var personId = "e0fcbfa6-7321-48bf-b35c-98428c78dd8d";
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jdoe@example.com";
    final var person = new PhysicalPerson(UUID.fromString(personId), firstName, lastName, email);
    final var exchange = mock(Exchange.class);
    final var message = mock(Message.class);
    when(exchange.getIn()).thenReturn(message);
    when(message.getBody()).thenReturn(List.of(person));
    when(personService.fetchPersons()).thenReturn(List.of(person));

    // WHEN
    processor.process(exchange);
    final var result = (List<PhysicalPerson>) exchange.getIn().getBody();

    // THEN
    assertNotNull(result);
    assertEquals(1, result.size());
    verify(personService, times(1)).fetchPersons();
    verify(message, times(1)).setBody(any());
  }
}