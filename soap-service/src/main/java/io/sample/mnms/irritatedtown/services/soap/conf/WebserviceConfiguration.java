package io.sample.mnms.irritatedtown.services.soap.conf;

import io.sample.mnms.irritatedtown.services.soap.services.InMemoryPersonService;
import io.sample.mnms.irritatedtown.services.soap.services.PersonService;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebserviceConfiguration {
  @Bean
  PersonService personService() {
    return new InMemoryPersonService();
  }

  @Bean
  Endpoint endpoint(final Bus bus, final MetricsProvider metricsProvider, final PersonService service) {
    final var ep = new EndpointImpl(bus, service, null, null, new MetricsFeature[]{new MetricsFeature(metricsProvider)});
    ep.publish("/services/persons");
    return ep;
  }
}
