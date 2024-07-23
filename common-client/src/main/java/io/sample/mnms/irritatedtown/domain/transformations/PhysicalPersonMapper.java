package io.sample.mnms.irritatedtown.domain.transformations;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import io.service.sample.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

/**
 * This mapper defines all mappings from / to a physical person.
 *
 * @author mgaspoz
 * @version 0.0.1
 * @implNote We are using <a href="https://mapstruct.org/">mapstruct</a> internally, see <a
 * href="https://mapstruct.org/documentation/1.6/reference/html/">the documentation</a>.
 * @since 0.0.1
 */
@Mapper
public interface PhysicalPersonMapper {

  // Note: when properties have the same name and the same type, they are automatically mapped.
  // See documentation: https://mapstruct.org/documentation/1.6/reference/html/

  /**
   * Transform a remote {@link Person} to a {@link PhysicalPerson}.
   *
   * @param person the person to transform
   *
   * @return the person transformed
   */
  @Mapping(source = "id", target = "id")
  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "email", target = "email")
  PhysicalPerson toPhysicalPerson(Person person);

  /**
   * Map a string to a uuid.
   *
   * <p>This implementation is mandatory to transform the {@link Person#id} to the {@link PhysicalPerson#id}. We
   * should use a common implementation shared by all mapper which have to do the same thing. See the documentation to
   * show how it works.</p>
   *
   * @param id the id to map
   *
   * @return the {@link UUID} representation of the id
   */
  default UUID toUuid(final String id) {
    return UUID.fromString(id);
  }
}
