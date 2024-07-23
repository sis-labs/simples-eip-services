package io.sample.mnms.irritatedtown.domain;

import java.util.UUID;

/**
 * Define the representation of a physical person in our domain.
 *
 * <p>This class is useless. It is only dedicated to represent the principle of mapping we have to perform to
 * transform a remote representation of a {@link io.service.sample.Person} to the local representation of a real
 * physical person.</p>
 *
 * @param id        the unique id of the person
 * @param firstName the first name of the person
 * @param lastName  the last name of the person
 * @param email     the email of the person
 *
 * @author mgaspoz
 * @version 0.0.1
 * @since 0.0.1
 */
public record PhysicalPerson(UUID id, String firstName, String lastName, String email) {
}
