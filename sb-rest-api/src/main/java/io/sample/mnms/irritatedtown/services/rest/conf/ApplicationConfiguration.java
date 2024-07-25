package io.sample.mnms.irritatedtown.services.rest.conf;

import io.sample.mnms.irritatedtown.domain.transformations.PhysicalPersonMapper;
import io.sample.mnms.irritatedtown.presentation.mappers.PhysicalPersonDtoMapper;
import io.sample.mnms.irritatedtown.domain.services.PersonsService;
import io.sample.mnms.irritatedtown.clients.soap.SoapClientPersonService;
import io.service.sample.PersonInformation;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * This is them main configuration of the spring boot application.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
@Configuration
@Profile("!test")
public class ApplicationConfiguration {

  @Value("${application.remoteServices.persons.uri}")
  private String personsServiceUri;

  /**
   * Create a new instance of the soap service client using a plain old way.
   *
   * @return a new instance of the webservice client.
   */
  @Bean
  public PersonInformation personInformation() {
    final var factory = new JaxWsProxyFactoryBean();
    factory.setServiceClass(PersonInformation.class);
    factory.setAddress(personsServiceUri);
    return (PersonInformation) factory.create();
  }

  /**
   * Create a new instance of the mapper to use to transform internal representation of the domain to the exposed
   * representation on the REST side of the application.
   *
   * @return a new instance of the mapper.
   */
  @Bean
  public PhysicalPersonDtoMapper physicalPersonDtoMapper() {
    return Mappers.getMapper(PhysicalPersonDtoMapper.class);
  }

  /**
   * Create a new instance of the mapper to use to transform remote representation of a person to our internal
   * representation of a person.
   *
   * @return a new instance of the mapper.
   */
  @Bean
  public PhysicalPersonMapper physicalPersonMapper() {
    return Mappers.getMapper(PhysicalPersonMapper.class);
  }

  /**
   * Create a new instance of the service to use to fetch information from the remote service and perform internal
   * processes to respond to client request.
   *
   * @param personInformation    the soap service client
   * @param physicalPersonMapper the mapper o use to transform remote representation to local representation
   *
   * @return a new instance of the service.
   */
  @Bean
  public PersonsService personsService(final PersonInformation personInformation,
                                       final PhysicalPersonMapper physicalPersonMapper) {
    return new SoapClientPersonService(personInformation, physicalPersonMapper);
  }
}
