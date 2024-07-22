package io.sample.mnms.irritatedtown.services.soap.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
  @Test
  void checkBuilder() {
    // GIVEN
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jdoe@corporation.com";

    // WHEN
    final var person = Person.builder()
        .withFirstName(firstName)
        .withLastName(lastName)
        .withEmail(email)
        .build();

    // THEN
    assertNotNull(person.id());
    assertEquals(firstName, person.firstName());
    assertEquals(lastName, person.lastName());
    assertEquals(email, person.email());
  }
}