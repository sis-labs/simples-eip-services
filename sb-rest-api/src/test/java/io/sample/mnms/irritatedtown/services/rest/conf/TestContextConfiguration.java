package io.sample.mnms.irritatedtown.services.rest.conf;

import io.sample.mnms.irritatedtown.presentation.mappers.PhysicalPersonDtoMapper;
import io.sample.mnms.irritatedtown.domain.services.InMemoryPersonService;
import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration for integration testing
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
@Configuration
@Profile("test")
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
