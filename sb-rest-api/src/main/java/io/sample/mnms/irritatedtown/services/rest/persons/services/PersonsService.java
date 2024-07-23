package io.sample.mnms.irritatedtown.services.rest.persons.services;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Definition of the client which is fetching data from the remote data store.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
public interface PersonsService {

  /**
   * Fetch the list of persons.
   *
   * @return the list of person
   */
  List<PhysicalPerson> fetchPersons();

  /**
   * Find a person using his id.
   *
   * @param personId the id of the person to fetch information for
   * @return the person if it exists or an empty optional if it doesn't
   */
  Optional<PhysicalPerson> findById(UUID personId);

  /**
   * Find a person using his id.
   *
   * @param personId the unique id of the person to fetch information for
   * @return the person if it exists or an empty optional otherwise.
   */
  default Optional<PhysicalPerson> findById(String personId) {
    return findById(UUID.fromString(personId));
  }
}
