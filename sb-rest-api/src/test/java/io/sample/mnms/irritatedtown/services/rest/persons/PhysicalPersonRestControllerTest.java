package io.sample.mnms.irritatedtown.services.rest.persons;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import io.sample.mnms.irritatedtown.services.rest.persons.services.PersonsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhysicalPersonRestControllerTest {

  @Mock
  private PhysicalPersonDtoMapper mapper;

  @Mock
  private PersonsService personsService;

  @InjectMocks
  private PhysicalPersonRestController controller;

  @Test
  void fetchAllPersons() {
    // GIVEN
    final var pId = UUID.randomUUID();
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jdoe@mydom.com";
    final var person = new PhysicalPerson(pId, firstName, lastName, email);
    final var personDto = new PhysicalPersonDto(pId.toString(), firstName, lastName, email);
    final var personsDb = List.of(person);
    when(personsService.fetchPersons()).thenReturn(personsDb);
    when(mapper.toDto(any(PhysicalPerson.class))).thenReturn(personDto);

    // WHEN
    final var response = controller.fetchAll();

    // THEN
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    final var body = response.getBody();
    assertNotNull(body.items());
    assertEquals(1, body.items().size());
    assertEquals(personDto, body.items().get(0));
    verify(mapper, times(1)).toDto(any(PhysicalPerson.class));
    verify(personsService, times(1)).fetchPersons();
  }
}