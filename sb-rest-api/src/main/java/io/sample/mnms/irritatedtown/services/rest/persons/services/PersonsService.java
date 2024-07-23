package io.sample.mnms.irritatedtown.services.rest.persons.services;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;

import java.util.List;

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
}
