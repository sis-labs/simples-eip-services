package io.sample.mnms.irritatedtown.services.eip.soaptorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point for the spring boot backed comml routing.
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
@SpringBootApplication
public class SbSoapToRestApplication {

  /**
   * Entry point of the application
   *
   * @param args command line arguments
   */
  public static void main(final String... args) {
    SpringApplication.run(SbSoapToRestApplication.class, args);
  }
}
