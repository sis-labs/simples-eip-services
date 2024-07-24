package io.sample.mnms.irritatedtown.services.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Wrapper class for the spring boot application.
 *
 * <p>The application publishes a simple soap web service using a code first approach for
 * his implementation. The idea behind that is only to create a functional lab which reproduces the behavior of the real
 * world application.</p>
 *
 * @author mgaspoz
 * @version 0.0.1
 * @since 0.0.1
 */
@SpringBootApplication
public class SoapWebserviceApplication {

  /**
   * Entry point of the application.
   *
   * @param args command line arguments
   */
  public static void main(String... args) {
    SpringApplication.run(SoapWebserviceApplication.class, args);
  }
}
