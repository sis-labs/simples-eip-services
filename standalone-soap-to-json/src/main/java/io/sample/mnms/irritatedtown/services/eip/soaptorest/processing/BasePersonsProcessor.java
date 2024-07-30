package io.sample.mnms.irritatedtown.services.eip.soaptorest.processing;

import io.sample.mnms.irritatedtown.domain.services.PersonsService;

/**
 * Base class for processors which interacts with persons.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class BasePersonsProcessor {
  /**
   * Service to use to interact with person data
   */
  protected final PersonsService personsService;

  /**
   * Create a new instance of the processor.
   *
   * @param personsService the service to interact with person data.
   */
  protected BasePersonsProcessor(final PersonsService personsService) {
    this.personsService = personsService;
  }
}
