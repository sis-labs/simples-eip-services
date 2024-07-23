package io.sample.mnms.irritatedtown.clients.soap;

import io.service.sample.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoapClientGenTest {
  @Test
  void checkPersonClassCompliance() {
    // GIVEN
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jdoe@example.com";

    // WHEN
    final var person = new Person();
    person.setFirstName(firstName);
    person.setLastName(lastName);
    person.setEmail(email);

    // THEN
    assertEquals(firstName, person.getFirstName());
    assertEquals(lastName, person.getLastName());
    assertEquals(email, person.getEmail());
  }
}
