package io.sample.mnms.irritatedtown.services.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application to run the standalone application.
 *
 * @author mgaspoz
 * @version 0.0.1
 * @since 0.0.1
 */
@SpringBootApplication
public class SbStandaloneApplication {

  /**
   * Entry point of the application.
   *
   * @param args command line arguments
   */
  public static void main(final String... args) {
    SpringApplication.run(SbStandaloneApplication.class, args);
  }
}
