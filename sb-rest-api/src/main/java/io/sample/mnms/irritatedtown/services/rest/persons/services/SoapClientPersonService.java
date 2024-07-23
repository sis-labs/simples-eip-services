package io.sample.mnms.irritatedtown.services.rest.persons.services;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the person service which is using the remote SOAP service to fetch information.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
public class SoapClientPersonService implements PersonsService {
  @Override
  public List<PhysicalPerson> fetchPersons() {

    return List.of();
  }

  @Override
  public Optional<PhysicalPerson> findById(final UUID personId) {
    return Optional.empty();
  }
}
