package io.sample.mnms.irritatedtown.services.eip.soaptorest.persons;

import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import io.sample.mnms.irritatedtown.presentation.dtos.PhysicalPersonDto;
import io.sample.mnms.irritatedtown.presentation.dtos.PhysicalPersonsDto;
import io.sample.mnms.irritatedtown.presentation.mappers.PhysicalPersonDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PhysicalPersonRestController {
  private final PersonsService personService;
  private final PhysicalPersonDtoMapper physicalPersonDtoMapper;

  public PhysicalPersonRestController(final PersonsService personService,
                                      final PhysicalPersonDtoMapper physicalPersonDtoMapper) {
    this.personService = personService;
    this.physicalPersonDtoMapper = physicalPersonDtoMapper;
  }

  @GetMapping("")
  public ResponseEntity<PhysicalPersonsDto> fetchAll() {
    final var persons = personService.fetchPersons()
                            .stream().map(physicalPersonDtoMapper::toDto).toList();
    return ResponseEntity.ok(new PhysicalPersonsDto(persons));
  }

  @GetMapping("/{personId}")
  public ResponseEntity<PhysicalPersonDto> fetchPersonById(@PathVariable("personId") final String personId) {
    final var person = personService.findById(personId);
    return person.map(physicalPerson -> ResponseEntity.ok(physicalPersonDtoMapper.toDto(physicalPerson)))
               .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
