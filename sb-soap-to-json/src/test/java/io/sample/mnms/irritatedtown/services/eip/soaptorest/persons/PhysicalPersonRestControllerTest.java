package io.sample.mnms.irritatedtown.services.eip.soaptorest.persons;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import io.sample.mnms.irritatedtown.presentation.dtos.PhysicalPersonDto;
import io.sample.mnms.irritatedtown.presentation.mappers.PhysicalPersonDtoMapper;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhysicalPersonRestControllerTest {

  @Mock
  private PhysicalPersonDtoMapper mapper;
  @Mock
  private PersonsService personService;
  @InjectMocks
  private PhysicalPersonRestController controller;

  @Test
  void fetchAllSuccess() {
    // GIVEN
    final var personIdStr = "cd67e1c9-bb6c-4a00-b745-8c9b0ea79aa9";
    final var personId = UUID.fromString(personIdStr);
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jane.doe@example.com";
    final var physicalPerson = new PhysicalPerson(personId, firstName, lastName, email);
    final var dto = new PhysicalPersonDto(personIdStr, firstName, lastName, email);
    when(mapper.toDto(physicalPerson)).thenReturn(dto);
    when(personService.fetchPersons()).thenReturn(List.of(physicalPerson));

    // WHEN
    final var response = controller.fetchAll();

    // THEN
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    final var body = response.getBody();
    assertNotNull(body);
    assertEquals(1, body.items().size());
    assertEquals(dto, body.items().get(0));
    verify(personService, times(1)).fetchPersons();
    verify(mapper, times(1)).toDto(physicalPerson);
  }

  @Test
  void findByIdExisting() {
    // GIVEN
    final var personIdStr = "cd67e1c9-bb6c-4a00-b745-8c9b0ea79aa9";
    final var personId = UUID.fromString(personIdStr);
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jane.doe@example.com";
    final var physicalPerson = new PhysicalPerson(personId, firstName, lastName, email);
    final var dto = new PhysicalPersonDto(personIdStr, firstName, lastName, email);
    when(mapper.toDto(physicalPerson)).thenReturn(dto);
    when(personService.findById(personIdStr)).thenReturn(Optional.of(physicalPerson));

    // WHEN
    final var response = controller.fetchPersonById(personIdStr);

    // THEN
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    final var body = response.getBody();
    assertNotNull(body);
    assertEquals(dto, body);
  }

  @Test
  void findNotExisting() {
    // GIVEN
    final var personIdStr = "cd67e1c9-bb6c-4a00-b745-8c9b0ea79aa9";
    when(personService.findById(personIdStr)).thenReturn(Optional.empty());

    // WHEN
    final var response = controller.fetchPersonById(personIdStr);

    // THEN
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(personService, times(1)).findById(personIdStr);
    verify(mapper, times(0)).toDto(any());
  }
}