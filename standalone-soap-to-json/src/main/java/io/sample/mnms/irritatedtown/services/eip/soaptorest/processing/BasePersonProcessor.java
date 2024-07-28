package io.sample.mnms.irritatedtown.services.eip.soaptorest.processing;

import io.sample.mnms.irritatedtown.domain.services.PersonsService;

/**
 * Base class for processor which are dealing with the person service.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class BasePersonProcessor {

    /**
     * reference to the service to use.
     */
    protected final PersonsService personsService;

    /**
     * Create a new instance of the processor.
     *
     * @param personsService the reference to the {@link PersonsService} to use inside the processor.
     */
    protected BasePersonProcessor(final PersonsService personsService) {
        this.personsService = personsService;
    }
}
