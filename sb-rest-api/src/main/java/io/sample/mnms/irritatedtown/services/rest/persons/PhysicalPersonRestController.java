package io.sample.mnms.irritatedtown.services.rest.persons;

import io.sample.mnms.irritatedtown.services.rest.persons.services.PersonsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller implementation to act as a REST proxy to the SOAP backend.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
@RestController
@RequestMapping(
    value = "/persons",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class PhysicalPersonRestController {

  private final PhysicalPersonDtoMapper mapper;
  private final PersonsService personsService;

  public PhysicalPersonRestController(final PhysicalPersonDtoMapper mapper, final PersonsService personsService) {
    this.mapper = mapper;
    this.personsService = personsService;
  }

  public ResponseEntity<PhysicalPersonsDto> fetchAll() {
    final var persons = personsService.fetchPersons();
    final var dto = new PhysicalPersonsDto(persons.stream().map(mapper::toDto).toList());
    return ResponseEntity.ok(dto);
  }

}
