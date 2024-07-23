package io.sample.mnms.irritatedtown.services.rest.conf;

import io.sample.mnms.irritatedtown.services.rest.persons.PhysicalPersonDtoMapper;
import io.sample.mnms.irritatedtown.services.rest.persons.services.InMemoryPersonService;
import io.sample.mnms.irritatedtown.services.rest.persons.services.PersonsService;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for integration testing
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
@Configuration
public class TestContextConfiguration {

  @Bean
  public PhysicalPersonDtoMapper physicalPersonDtoMapper() {
    return Mappers.getMapper(PhysicalPersonDtoMapper.class);
  }

  @Bean
  public PersonsService personsService() {
    return new InMemoryPersonService();
  }
}