package io.sample.mnms.irritatedtown.services.rest;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PhysicalPersonDtoMapperTest {
  @Test
  void mapSuccessfully() {
    // GIVEN
    final var idStr = "01c04a73-2d6c-4b70-9d73-1de332da6e6e";
    final var id = UUID.fromString(idStr);
    final var firstName = "jane";
    final var lastName = "doe";
    final var email = "jdoe@mydom.com";

    final var person = new PhysicalPerson(id, firstName, lastName, email);

    // WHEN
    final var mapper = Mappers.getMapper(PhysicalPersonDtoMapper.class);
    final var result = mapper.toDto(person);

    // THEN
    assertNotNull(result);
    assertEquals(idStr, result.id());
    assertEquals(firstName, result.firstName());
    assertEquals(lastName, result.lastName());
    assertEquals(email, result.email());
  }
}