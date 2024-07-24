package io.sample.mnms.irritatedtown.services.rest.persons.services;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import io.sample.mnms.irritatedtown.domain.transformations.PhysicalPersonMapper;
import io.service.sample.Person;
import io.service.sample.PersonInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SoapClientPersonServiceTest {

  @Mock
  private PersonInformation personInformation;
  @Mock
  private PhysicalPersonMapper physicalPersonMapper;
  @InjectMocks
  private SoapClientPersonService soapClientPersonService;

  private Person createPerson(final String id, final String firstName, final String lastName, final String email) {
    final Person person = new Person();
    person.setId(id);
    person.setFirstName(firstName);
    person.setLastName(lastName);
    return person;
  }

  @Test
  void checkFetchPersons() {
    // GIVEN
    final var id = "32ffc733-262f-46d3-957d-0b803e8da8b1";
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jane.doe@mydomain.com";
    final var person = createPerson(id, firstName, lastName, email);
    when(personInformation.fetchPersons()).thenReturn(List.of(person));
    final var physicalPerson = new PhysicalPerson(UUID.fromString(id), firstName, lastName, email);
    when(physicalPersonMapper.toPhysicalPerson(person)).thenReturn(physicalPerson);

    // WHEN
    final var persons = soapClientPersonService.fetchPersons();

    // THEN
    assertNotNull(persons);
    assertEquals(1, persons.size());
    assertEquals(physicalPerson, persons.get(0));
    verify(personInformation, times(1)).fetchPersons();
    verify(physicalPersonMapper, times(1)).toPhysicalPerson(person);
  }

  @Test
  void fetchPersonSuccess() {
    // GIVEN
    final var id = "32ffc733-262f-46d3-957d-0b803e8da8b1";
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jane.doe@mydomain.com";
    final var person = createPerson(id, firstName, lastName, email);
    when(personInformation.findById(id)).thenReturn(person);
    final var physicalPerson = new PhysicalPerson(UUID.fromString(id), firstName, lastName, email);
    when(physicalPersonMapper.toPhysicalPerson(person)).thenReturn(physicalPerson);

    // WHEN
    final var pers = soapClientPersonService.findById(id);

    // THEN
    assertNotNull(pers);
    assertTrue(pers.isPresent());
    assertEquals(physicalPerson, pers.get());
    verify(personInformation, times(1)).findById(id);
    verify(physicalPersonMapper, times(1)).toPhysicalPerson(person);
  }

  @Test
  void fetchNonExistingPerson() {
    // GIVEN
    final var id = UUID.randomUUID().toString();
    when(personInformation.findById(id)).thenReturn(null);

    // WHEN
    final var pers = soapClientPersonService.findById(id);

    // THEN
    assertTrue(pers.isEmpty());
    verify(personInformation, times(1)).findById(id);
    verify(physicalPersonMapper, times(0)).toPhysicalPerson(any(Person.class));
  }
}
