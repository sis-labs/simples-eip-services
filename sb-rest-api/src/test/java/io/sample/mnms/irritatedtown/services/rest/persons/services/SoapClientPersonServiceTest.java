package io.sample.mnms.irritatedtown.services.rest.persons.services;

import io.sample.mnms.irritatedtown.domain.transformations.PhysicalPersonMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SoapClientPersonServiceTest {
  @Test
  void connectToService() {
    // GIVEN
    final var serviceUri = "http://localhost:8080/soap/services/persons";
    final var mapper = Mappers.getMapper(PhysicalPersonMapper.class);

    // WHEN
    final var service = new SoapClientPersonService(serviceUri, mapper);
    final var response = service.fetchPersons();

    // THEN
    assertNotNull(response);
    assertFalse(response.isEmpty());
    assertEquals(2, response.size());
  }

  @Test
  void fetchExistingPerson() {
    // GIVEN
    final var serviceUri = "http://localhost:8080/soap/services/persons";
    final var mapper = Mappers.getMapper(PhysicalPersonMapper.class);
    final var personId = "df8b3c45-451e-4750-b1a1-faaa9ba55e89";

    // WHEN
    final var service = new SoapClientPersonService(serviceUri, mapper);
    final var response = service.findById(personId);

    // THEN
    assertNotNull(response);
    assertFalse(response.isEmpty());
    final var person = response.get();
    assertEquals(personId, person.id().toString());
  }
}