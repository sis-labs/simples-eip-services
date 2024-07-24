package io.sample.mnms.irritatedtown.domain.transformations;

import io.service.sample.Person;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PhysicalPersonMapperTest {
  @Test
  void checkTransformation() {
    // GIVEN
    final var id = UUID.randomUUID();
    final var firstName = "Jane";
    final var lastName = "Doe";
    final var email = "jane.doe@example.com";

    final var person = new Person();
    person.setId(id.toString());
    person.setFirstName(firstName);
    person.setLastName(lastName);
    person.setEmail(email);

    // WHEN
    final var mapper = Mappers.getMapper(PhysicalPersonMapper.class);
    final var result = mapper.toPhysicalPerson(person);

    // THEN
    assertNotNull(result);
    assertEquals(id, result.id());
    assertEquals(firstName, result.firstName());
    assertEquals(lastName, result.lastName());
    assertEquals(email, result.email());
  }
}