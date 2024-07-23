package io.sample.mnms.irritatedtown.services.rest;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping(
    value = "/persons",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class PhysicalPersonRestController {

  private final PhysicalPersonDtoMapper mapper;

  public PhysicalPersonRestController(final PhysicalPersonDtoMapper mapper) {
    this.mapper = mapper;
  }

  public ResponseEntity<PhysicalPersonsDto> fetchAll() {
    final var persons = new ArrayList<PhysicalPerson>();
    persons.add(new PhysicalPerson(UUID.fromString("df8b3c45-451e-4750-b1a1-faaa9ba55e89"), "Jane", "Doe", "jane.doe@mydom.com"));
    persons.add(new PhysicalPerson(UUID.fromString("045fd1b9-d7c9-49dc-8b46-8826969f0ea0"), "John", "Doe", "john.doe@corp.io"));

    final var dto = new PhysicalPersonsDto(persons.stream().map(mapper::toDto).toList());

    return ResponseEntity.ok(dto);
  }

}
