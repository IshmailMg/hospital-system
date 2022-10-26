package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Secretary;
import za.ac.cput.factory.SecretaryFactory;
import za.ac.cput.service.SecretaryService;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecretaryControllerTest {
    private String baseUrl;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SecretaryController controller;

    @Autowired
    private SecretaryService service;
    private Secretary secretary;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.secretary = SecretaryFactory.createSecretary("1213131", "David", "Mane");
        this.service.save(secretary);
        this.baseUrl = "http://localhost:" + this.port + "/hospital-system/secretary/";
    }

    @Order(1)
    @Test
    void save() {
        String url = baseUrl + "save";
        System.out.println(url);
        ResponseEntity<Secretary> response = this.restTemplate.withBasicAuth("admin-user", "65ff7492d30").postForEntity(url, this.secretary, Secretary.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Order(2)
    @Test
    public void read() {
        String url = baseUrl + "read/" + this.secretary.getSecID();
        System.out.println(url);
        ResponseEntity<Secretary> response = this.restTemplate.withBasicAuth("admin-user", "65ff7492d30").getForEntity(url, Secretary.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Order(4)
    @Test
    public void delete() {
        String url = baseUrl + "delete/" + this.secretary.getSecID();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Order(3)
    @Test
    public void findAll() {
        String url = baseUrl + "find-all";
        System.out.println(url);
        ResponseEntity<Secretary[]> response = this.restTemplate.withBasicAuth("client-user", "1253208465b").getForEntity(url, Secretary[].class);
        System.out.println("Show All:");
        System.out.println(Arrays.asList(Objects.requireNonNull(response.getBody())));
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertEquals(4, response.getBody().length));
    }

}