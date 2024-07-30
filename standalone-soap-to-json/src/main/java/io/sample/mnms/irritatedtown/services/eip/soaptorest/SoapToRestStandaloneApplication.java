package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import io.sample.mnms.irritatedtown.domain.services.InMemoryPersonService;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.Thread.sleep;

/**
 * Application which is running our Camel EIP set.
 *
 * @author mgaspoz
 * @version 0.0.1
 * @since 0.0.1
 */
public class SoapToRestStandaloneApplication {

    private static final Logger log = LogManager.getLogger(SoapToRestStandaloneApplication.class);

    private static volatile boolean running = false;

    public static void main(final String... args) {
        // Setting up system context to allow log4j (logger) to behave correctly
        final var logFile = "engine.log";
        System.setProperty("LOG_FILE", logFile);

        // Running the application
        log.info("Starting the application");
        try (final var context = new DefaultCamelContext()) {
            final var personService = new InMemoryPersonService();

            // Ensure graceful shutdown for the JVM.
            final var shutdownHook = new ShutdownHook(context);
            Runtime.getRuntime().addShutdownHook(shutdownHook);

            // preparing the context
            running = true; // This is a bit random but it makes the job for now.

            // Creating the behavior of the application.
            context.addRoutes(new SoapExchangeRouteBuilder(personService));
            context.addRoutes(new RestServiceRouteBuilder());
            context.start();
            while (running) {
                // TODO: handle the logic to listen for external event and implement a graceful shutdown.
                sleep(1000);
            }
        } catch (final Exception e) {
            // TODO: implement the logic to handle such exception
            log.error("An error occurred while starting the application", e);
            running = false;
        } finally {
            log.info("Shutting down the application");
        }
    }

    static class ShutdownHook extends Thread {
        private static final Logger log = LogManager.getLogger(ShutdownHook.class);
        private final DefaultCamelContext camelContext;

        public ShutdownHook(final DefaultCamelContext camelContext) {
            this.camelContext = camelContext;
        }

        @Override
        public void run() {
            log.info("Shutting down CamelContext");
            try {
                camelContext.stopAllRoutes();
                camelContext.stop();
                log.info("CamelContext stopped");
            } catch (Exception e) {
                log.error("Error when shutting down CamelContext", e);
            } finally {
                running = false;
            }
        }
    }
}
