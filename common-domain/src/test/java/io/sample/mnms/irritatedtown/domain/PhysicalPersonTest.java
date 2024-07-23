package io.sample.mnms.irritatedtown.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PhysicalPersonTest {
  @Test
  void checkCreation() {
    // GIVEN
    final var id = UUID.randomUUID();;
    final var firstName = "John";
    final var lastName = "Doe";
    final var email = "john.doe@example.com";

    // WHEN
    final var person = new PhysicalPerson(id, firstName, lastName, email);

    // THEN
    assertEquals(id, person.id());
    assertEquals(firstName,person.firstName());
    assertEquals(lastName,person.lastName());
    assertEquals(email,person.email());
  }
}