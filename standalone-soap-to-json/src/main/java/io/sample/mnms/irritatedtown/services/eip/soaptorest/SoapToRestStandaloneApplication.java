package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Application which is running our Camel EIP set.
 *
 * @author mgaspoz
 * @version 0.0.1
 * @since 0.0.1
 */
public class SoapToRestStandaloneApplication {

  private static final Logger log = LogManager.getLogger(SoapToRestStandaloneApplication.class);

  public static void main(final String... args) {
    // Setting up system context to allow log4j (logger) to behave correctly
    final var logFile = "engine.log";
    System.setProperty("LOG_FILE", logFile);

    // Running the application
    log.info("Starting the application");
    try (final var context = new DefaultCamelContext()) {
      context.addRoutes(new SoapExchangeRouteBuilder());
      context.addRoutes(new RestServiceRouteBuilder());
      context.start();
      while (true) {
        // TODO: handle the logic to listen for external event and implement a graceful shutdown.
        Thread.sleep(1000);
      }
    } catch (final Exception e) {
      // TODO: implement the logic to handle such exception
      log.error("An error occurred while starting the application", e);
      throw new RuntimeException(e);
    }
  }
}
