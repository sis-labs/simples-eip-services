package io.sample.mnms.irritatedtown.services.eip.soaptorest.persons.processors;

import io.sample.mnms.irritatedtown.domain.services.PersonsService;

/**
 * Base class for processors which are related to persons management.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class BasePersonsProcessor {
  /**
   * Reference to the service to use to interact with person
   */
  protected final PersonsService personsService;

  /**
   * Create a new instance of the service.
   *
   * @param personsService the reference of the service to use to interact with person remote service.
   */
  protected BasePersonsProcessor(final PersonsService personsService) {
    this.personsService = personsService;
  }
}
