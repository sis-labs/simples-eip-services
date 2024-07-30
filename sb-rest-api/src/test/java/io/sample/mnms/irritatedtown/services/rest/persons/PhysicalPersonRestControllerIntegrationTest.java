package io.sample.mnms.irritatedtown.services.rest.persons;

import io.sample.mnms.irritatedtown.services.rest.SbStandaloneApplication;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = {
        SbStandaloneApplication.class
    },
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class PhysicalPersonRestControllerIntegrationTest {

  private final String scheme = "http";
  private final String hostname = "localhost";
  private final String context = "/rest";
  private final String personsSlug = "/persons";

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private String withUrl(final String hostname, final int port, final String context, final String slug) {
    return withUrl(hostname, port, context, slug, null);
  }

  private String withUrl(final String hostname,
                         final int port,
                         final String context,
                         final String slug,
                         final String personId) {
    return String.format("%s://%s:%s%s%s%s", scheme, hostname, port, context, slug,
        StringUtils.isEmpty(personId) ? "" : "/" + personId);
  }

  private HttpHeaders withHeaders() {
    final var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    return headers;
  }

  @Test
  void fetchAllPersons() throws JSONException {
    // GIVEN
    final var expected = "{\"items\": [{\"id\":\"df8b3c45-451e-4750-b1a1-faaa9ba55e89\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@mydom.com\"},{\"id\":\"045fd1b9-d7c9-49dc-8b46-8826969f0ea0\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@corp.io\"}]}";
    final var headers = withHeaders();

    // WHEN
    final var entity = new HttpEntity<>(null, headers);
    final var response = restTemplate.exchange(
        withUrl(hostname, port, context, personsSlug),
        HttpMethod.GET,
        entity,
        String.class);

    // THEN
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    final var body = response.getBody();
    JSONAssert.assertEquals(expected.toLowerCase(), body.toLowerCase(), false);
  }

  @Test
  void findExistingPerson() throws JSONException {
    // GIVEN
    final var personId = "df8b3c45-451e-4750-b1a1-faaa9ba55e89";
    final var headers = withHeaders();
    final var expected = "{\"id\":\"df8b3c45-451e-4750-b1a1-faaa9ba55e89\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@mydom.com\"}";

    // WHEN
    final var entity = new HttpEntity<>(null, headers);
    final var url = withUrl(hostname, port, context, personsSlug, personId);
    final var response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        String.class);

    // THEN
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    final var body = response.getBody();
    JSONAssert.assertEquals(expected.toLowerCase(), body.toLowerCase(), false);
  }

}