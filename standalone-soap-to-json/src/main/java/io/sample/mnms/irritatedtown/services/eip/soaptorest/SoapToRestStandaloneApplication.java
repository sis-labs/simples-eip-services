package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoapToRestStandaloneApplication {

  private static final Logger log = LogManager.getLogger(SoapToRestStandaloneApplication.class);

  public static void main(final String... args) {
    // Setting up system context to allow log4j (logger) to behave correctly
    final var logFile = "engine.log";
    System.setProperty("LOG_FILE", logFile);

    // Starting the application
    log.info("Starting the application");

    try(final var context = new DefaultCamelContext()) {
      context.addRoutes(new SoapExchangeRouteBuilder());
      context.start();
      var n = 10;
      while(n-- > 0) {
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
