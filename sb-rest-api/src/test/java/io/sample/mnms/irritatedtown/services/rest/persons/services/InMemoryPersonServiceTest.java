package io.sample.mnms.irritatedtown.services.rest.persons.services;

import org.junit.jupiter.api.Test;

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
}