package io.sample.mnms.irritatedtown.services.soap.services;

import io.sample.mnms.irritatedtown.services.soap.domain.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Simple in memory implementation of a person service provider.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
public class InMemoryPersonService implements PersonService {

    private static final Map<UUID, Person> personRegistry = new HashMap<>();

    // Create the read only in memory base of persons.
    // Since we want a stable service in terms of data providing (same request will produce same
    // response), we are fixing ids for persons.
    static {
        final var builder = Person.builder();
        builder.withLastName("doe")
                .withFirstName("jane")
                .withEmail("jane.doe@mydom.com")
                .withId("df8b3c45-451e-4750-b1a1-faaa9ba55e89");
        var p = builder.build();
        personRegistry.put(p.id(), p);
        builder.withId("045fd1b9-d7c9-49dc-8b46-8826969f0ea0")
                .withFirstName("john")
                .withEmail("john.doe@corp.io");
        p = builder.build();
        personRegistry.put(p.id(), p);
    }

    @Override
    public Person findById(final String personId) {
        final var id = UUID.fromString(personId);
        if (personRegistry.containsKey(id)) {
            return personRegistry.get(id);
        }
        // better to use optional here.
        return null;
    }

    @Override
    public List<Person> fetchPersons() {
        final var values = personRegistry.values();
        return values.stream().toList();
    }
}
