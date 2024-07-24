package io.sample.mnms.irritatedtown.presentation.mappers;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import io.sample.mnms.irritatedtown.presentation.dtos.PhysicalPersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper
public interface PhysicalPersonDtoMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "email", target = "email")
  PhysicalPersonDto toDto(PhysicalPerson person);

  default String fromUuid(final UUID uuid) {
    return uuid.toString();
  }
}
