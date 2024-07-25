package io.sample.mnms.irritatedtown.clients.soap;

import io.sample.mnms.irritatedtown.domain.transformations.PhysicalPersonMapper;
import io.service.sample.PersonInformation;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is an integration test which is only working if the 'soap-service' is running. This should be relocated in the
 * 'it' project which is dedicated to integration tests.
 *
 * <p>Since we don't need about the spring context here, we can bypass the expensive context load related to the use
 * of this annotation. We are creating the service "manually" in the initialisation of the test.
 * </p>
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
class SoapClientPersonServiceIntegrationTest {

  private PersonInformation personInformation(final String serviceUri) {
    final var factory = new JaxWsProxyFactoryBean();
    factory.setServiceClass(PersonInformation.class);
    factory.setAddress(serviceUri);
    return (PersonInformation) factory.create();
  }

  @Test
  void connectToService() {
    // GIVEN
    final var serviceUri = "http://localhost:8080/soap/services/persons";
    final var remoteService = personInformation(serviceUri);
    final var mapper = Mappers.getMapper(PhysicalPersonMapper.class);

    // WHEN
    final var service = new SoapClientPersonService(remoteService, mapper);
    final var response = service.fetchPersons();

    // THEN
    assertNotNull(response);
    assertFalse(response.isEmpty());
    assertEquals(2, response.size());
  }

  @Test
  void fetchExistingPerson() {
    // GIVEN
    final var serviceUri = "http://localhost:8080/soap/services/persons";
    final var mapper = Mappers.getMapper(PhysicalPersonMapper.class);
    final var remoteService = personInformation(serviceUri);
    final var personId = "df8b3c45-451e-4750-b1a1-faaa9ba55e89";

    // WHEN
    final var service = new SoapClientPersonService(remoteService, mapper);
    final var response = service.findById(personId);

    // THEN
    assertNotNull(response);
    assertFalse(response.isEmpty());
    final var person = response.get();
    assertEquals(personId, person.id().toString());
  }
}