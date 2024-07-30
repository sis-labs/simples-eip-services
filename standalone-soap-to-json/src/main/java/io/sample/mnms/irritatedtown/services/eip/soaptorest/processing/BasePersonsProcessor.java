package io.sample.mnms.irritatedtown.services.eip.soaptorest.processing;

import io.sample.mnms.irritatedtown.domain.services.PersonsService;

public abstract class BasePersonsProcessor {
  protected final PersonsService personsService;

  protected BasePersonsProcessor(final PersonsService personsService) {
    this.personsService = personsService;
  }
}
