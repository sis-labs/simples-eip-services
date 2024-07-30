package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SoapToRestStandaloneApplicationTest {

  @Mock
  private DefaultCamelContext camelContext;

  @InjectMocks
  private SoapToRestStandaloneApplication.ShutdownHook shutdownHook;

  @Test
  void runSuccess() throws Exception {
    shutdownHook.run();
    verify(camelContext, times(1)).stopAllRoutes();
    verify(camelContext, times(1)).stop();
  }
}