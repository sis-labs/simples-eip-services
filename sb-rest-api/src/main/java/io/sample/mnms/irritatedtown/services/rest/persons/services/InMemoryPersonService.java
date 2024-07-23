package io.sample.mnms.irritatedtown.services.rest.persons.services;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryPersonService implements PersonsService {

  private static final List<PhysicalPerson> persons = new ArrayList<PhysicalPerson>();

  static {
    persons.add(new PhysicalPerson(UUID.fromString("df8b3c45-451e-4750-b1a1-faaa9ba55e89"), "Jane", "Doe", "jane.doe@mydom.com"));
    persons.add(new PhysicalPerson(UUID.fromString("045fd1b9-d7c9-49dc-8b46-8826969f0ea0"), "John", "Doe", "john.doe@corp.io"));
  }

  @Override
  public List<PhysicalPerson> fetchPersons() {
    return persons;
  }
}
