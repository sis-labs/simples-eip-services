package io.sample.mnms.irritatedtown.services.soap.domain;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Representation of a person in the SOAP service side.
 *
 * <p>This implementation is a simulation of the representation of a person inside the
 * citizen application.</p>
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
@XmlRootElement(name = "person")
public class Person implements Serializable {

    /**
     * The unique id of the person
     */
    private UUID id;

    /**
     * The first name of the person
     */
    private String firstName;

    /**
     * The last name of the person
     */
    private String lastName;

    /**
     * The email of the person
     */
    private String email;

    /**
     * Create a new instance of a person.
     *
     * @param id        the unique id of a person
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     * @param email     the email of the person
     */
    public Person(final UUID id, final String firstName, final String lastName, final String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person() {
        this(UUID.randomUUID(), "", "", "");
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public UUID id() {
        return getId();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String firstName() {
        return getFirstName();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String lastName() {
        return getLastName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String email() {
        return getEmail();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Builder for a person in order to avoid to have to implement many contructor.
     *
     * <p>This builder defines default values for mandatory fields and empty values for others.</p>
     *
     * @author miga
     * @version 0.0.1
     * @since 0.0.1
     */
    public static class Builder {
        private UUID id = UUID.randomUUID();
        private String firstName = "";
        private String lastName = "";
        private String email = "";

        Builder() {
            // hide constructor
        }

        public Builder withId(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder withId(final String id) {
            return withId(UUID.fromString(id));
        }

        public Builder withFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(final String email) {
            this.email = email;
            return this;
        }

        public Person build() {
            return new Person(id, firstName, lastName, email);
        }
    }
}
