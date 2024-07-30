package io.sample.mnms.irritatedtown.services.eip.soaptorest.conf;

import io.sample.mnms.irritatedtown.domain.services.InMemoryPersonService;
import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import io.sample.mnms.irritatedtown.presentation.mappers.PhysicalPersonDtoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public PersonsService personService() {
    return new InMemoryPersonService();
  }

  @Bean
  public PhysicalPersonDtoMapper physicalPersonDtoMapper() {
    return Mappers.getMapper(PhysicalPersonDtoMapper.class);
  }
}
