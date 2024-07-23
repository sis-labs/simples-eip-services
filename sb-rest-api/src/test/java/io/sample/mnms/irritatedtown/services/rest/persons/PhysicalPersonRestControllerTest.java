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
import java.util.Optional;
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

  @Test
  void findByIdExisting() {
    // GIVEN
    final var personId = "df8b3c45-451e-4750-b1a1-faaa9ba55e89";
    final var pId = UUID.fromString(personId);
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jdoe@mydom.com";
    final var person = new PhysicalPerson(pId, firstName, lastName, email);
    final var personDto = new PhysicalPersonDto(personId, firstName, lastName, email);
    final var personsDb = Optional.of(person);
    when(personsService.findById(personId)).thenReturn(personsDb);
    when(mapper.toDto(any(PhysicalPerson.class))).thenReturn(personDto);

    // WHEN
    final var response = controller.findById(personId);

    // THEN
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    final var body = response.getBody();
    assertEquals(personDto, body);
    verify(mapper, times(1)).toDto(any(PhysicalPerson.class));
    verify(personsService, times(1)).findById(any(String.class));
  }

  @Test
  void findByIdNonExisting() {
    // GIVEN
    final var personId = "cf04b7bf-b9b0-4f9f-977e-66fbae101fb4";
    final var pId = UUID.fromString(personId);
    when(personsService.findById(personId)).thenReturn(Optional.empty());

    // WHEN
    final var response = controller.findById(personId);

    // THEN
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(mapper, times(0)).toDto(any(PhysicalPerson.class));
    verify(personsService, times(1)).findById(any(String.class));
  }

  @Test
  void findByInvalidId() {
    // GIVEN
    final var personId = "aasedf230498aeasdf248aew0-984lqajsf";
    when(personsService.findById(personId)).thenThrow(new IllegalArgumentException("Invalid uuid"));

    // WHEN
    final var response = controller.findById(personId);

    // THEN
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
}