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

/**
 * Define the configuration of the webservice.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
@Configuration
public class WebserviceConfiguration {

    /**
     * Define the instance of the person provider service to use.
     *
     * @return a new instance of the {@link PersonService}
     */
    @Bean
    PersonService personService() {
        return new InMemoryPersonService();
    }

    /**
     * Create a new instance of the {@link Endpoint} which will be use by the system to define the
     * SOAP endpoint.
     *
     * @param bus             bus instance to use (auto wired)
     * @param metricsProvider metrics provider instance to use (auto wired)
     * @param service         person provider service to use
     * @return a new instance of the {@link Endpoint}.
     */
    @Bean
    Endpoint endpoint(final Bus bus, final MetricsProvider metricsProvider, final PersonService service) {
        final var ep = new EndpointImpl(bus, service, null, null, new MetricsFeature[]{new MetricsFeature(metricsProvider)});
        ep.publish("/services/persons");
        return ep;
    }
}
