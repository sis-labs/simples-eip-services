package io.sample.mnms.irritatedtown.services.eip.soaptorest.processing;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FetchPersonProcessorTest {
  @Mock
  private PersonsService personService;
  @InjectMocks
  private FetchPersonProcessor processor;

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
    when(message.getHeader("id", String.class)).thenReturn(personId);
    when(message.getBody()).thenReturn(person);
    when(personService.findById(personId)).thenReturn(Optional.of(person));

    // WHEN
    processor.process(exchange);
    final var result = exchange.getIn().getBody();

    // THEN
    assertNotNull(result);
    assertEquals(person, result);
    verify(personService, times(1)).findById(personId);
    verify(message, times(1)).setBody(person);
  }
}