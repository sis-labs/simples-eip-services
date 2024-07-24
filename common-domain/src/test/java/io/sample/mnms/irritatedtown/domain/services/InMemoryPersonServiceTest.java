package io.sample.mnms.irritatedtown.domain.services;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPersonServiceTest {
  private final InMemoryPersonService service = new InMemoryPersonService();

  @Test
  void fetchAllSuccess() {
    // GIVEN
    final var size = 2;

    // WHEN
    final var persons = service.fetchPersons();

    // THEN
    assertEquals(size, persons.size());
  }

  @Test
  void findExisting() {
    // GIVEN
    final var id = "df8b3c45-451e-4750-b1a1-faaa9ba55e89";
    final var personId = UUID.fromString(id);

    // WHEN
    final var oPerson = service.findById(personId);

    // THEN
    assertNotNull(oPerson);
    assertTrue(oPerson.isPresent());
    final var person = oPerson.get();
    assertEquals(personId, person.id());
    assertEquals("jane", person.firstName().toLowerCase());
  }

  @Test
  void findExistingFromString() {
    // GIVEN
    final var id = "df8b3c45-451e-4750-b1a1-faaa9ba55e89";

    // WHEN
    final var oPerson = service.findById(id);

    // THEN
    assertTrue(oPerson.isPresent());
  }

  @Test
  void findByIdWithInvalidId() {
    // GIVEN
    final var id = "df8b3c45asdf451e1234750awb1a1dddfaaa9ba55e89";

    assertThrows(IllegalArgumentException.class, () -> {
      service.findById(id);
    });
  }

  @Test
  void findNonExisting() {
    // GIVEN
    final var id = "cf04b7bf-b9b0-4f9f-977e-66fbae101fb4";
    final var personId = UUID.fromString(id);

    // WHEN
    final var oPerson = service.findById(personId);

    // THEN
    assertTrue(oPerson.isEmpty());
  }
}