package io.sample.mnms.irritatedtown.services.rest.persons.services;

import io.sample.mnms.irritatedtown.domain.PhysicalPerson;
import io.sample.mnms.irritatedtown.domain.transformations.PhysicalPersonMapper;
import io.service.sample.PersonInformation;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the person service which is using the remote SOAP service to fetch information.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
public class SoapClientPersonService implements PersonsService {

  /**
   * The uri of the remote service to fetch information from
   */
  private final String serviceUri;

  /**
   * Mapper to use to transform remote object to domain object.
   */
  private final PhysicalPersonMapper physicalPersonMapper;

  /**
   * Create a new instance of the service.
   *
   * @param serviceUri           the uri of the service to fetch information from
   * @param physicalPersonMapper the mapper to use to transform remote object to local object
   */
  public SoapClientPersonService(final String serviceUri,
                                 final PhysicalPersonMapper physicalPersonMapper) {
    this.serviceUri = serviceUri;
    this.physicalPersonMapper = physicalPersonMapper;
  }

  /**
   * Create a new instance of the service client and return it.
   *
   * @return a new instance of the service client
   *
   * @implNote we should cache the instance of the service to avoid to recreate it each time we are calling a method of
   * the service since it may be a bit expensive in terms of performance. We are considering it is sufficient for the
   * POC to recreate the instance of the service each time.
   */
  private PersonInformation getService() {
    final var factory = new JaxWsProxyFactoryBean();
    factory.setServiceClass(PersonInformation.class);
    factory.setAddress(serviceUri);
    return (PersonInformation) factory.create();
  }

  @Override
  public List<PhysicalPerson> fetchPersons() {
    final var service = getService();
    return service.fetchPersons().stream().map(physicalPersonMapper::toPhysicalPerson).toList();
  }

  @Override
  public Optional<PhysicalPerson> findById(final UUID personId) {
    final var service = getService();
    final var person = service.findById(personId.toString());
    if (null == person) {
      return Optional.empty();
    }
    return Optional.of(person).map(physicalPersonMapper::toPhysicalPerson);
  }
}
