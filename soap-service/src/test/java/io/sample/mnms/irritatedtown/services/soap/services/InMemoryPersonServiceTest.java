package io.sample.mnms.irritatedtown.services.soap.services;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryPersonServiceTest {
  @Test
  void loadList() {
    // GIVEN
    final var service = new InMemoryPersonService();

    // WHEN
    final var persons = service.fetchPersons();

    // THEN
    assertNotNull(persons);
    assertEquals(2, persons.size());
    assertEquals(1, persons.stream()
        .filter(p -> p.id().equals(UUID.fromString("df8b3c45-451e-4750-b1a1-faaa9ba55e89")))
        .toList()
        .size());
    assertEquals(1, persons.stream()
        .filter(p -> p.id().equals(UUID.fromString("045fd1b9-d7c9-49dc-8b46-8826969f0ea0")))
        .toList()
        .size());
  }

  @Test
  void findById() {
    // GIVEN
    final var personId = "df8b3c45-451e-4750-b1a1-faaa9ba55e89";
    final var service = new InMemoryPersonService();

    // WHEN
    final var person = service.findById(personId);

    // THEN
    assertNotNull(person);
    assertEquals("jane", person.firstName());
  }
}