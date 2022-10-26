package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Driver;
import za.ac.cput.factory.DriverFactory;
import za.ac.cput.service.DriverService;
import za.ac.cput.service.impl.DriverServiceImpl;
import java.util.Arrays;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

/*
    DriverControllerTest.java
    Test for the Driver Controller
    Author: Nonzwakazi Mgxaji
    Date: 16 October 2022
*/
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DriverControllerTest {
    public static String SECURITY_USERNAME = "admin-user";
    public static String SECURITY_PASSWORD = "65ff7492d30";

    private String baseUrl;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private DriverController controller;
    @Autowired
    private DriverServiceImpl driverService;

    private Driver driver;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.driver = DriverFactory.createDriver("29", "Nzwakie", "Mgxaji", "CA123456");
        this.baseUrl = "http://localhost:" + this.port + "/hospital-system/driver/";
    }

    @Test
    void a_save() {
        String url = baseUrl + "save/";
        System.out.println(url);
        ResponseEntity<Driver> response = this.restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .postForEntity(url, this.driver, Driver.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Test
    public void b_read() {
        String url = baseUrl + "find/" + this.driver.getDriverID();
        System.out.println(url);
        ResponseEntity<Driver> response = this.restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .getForEntity(url, Driver.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }


    @Test
    public void c_delete() {
        String url = baseUrl + "delete/" + this.driver.getDriverID();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Test
    public void d_findAll() {
        String url = baseUrl + "get-all/";
        System.out.println(url);
        ResponseEntity<Driver[]> response = this.restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .getForEntity(url, Driver[].class);
        assertNotNull(response);
        assertAll(()->assertEquals(HttpStatus.OK,response.getStatusCode()));
        System.out.println("Show All:");
        System.out.println(Arrays.asList(Objects.requireNonNull(response.getBody())));
    }
}