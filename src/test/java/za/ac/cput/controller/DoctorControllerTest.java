package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Doctor;
import za.ac.cput.factory.DoctorFactory;
import za.ac.cput.service.DoctorService;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DoctorControllerTest {
    private String baseUrl;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DoctorController controller;

    @Autowired
    private DoctorService service;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.doctor = DoctorFactory.createDoctor("121", "David", "Mane", "04924323423");
        this.service.save(doctor);
        this.baseUrl = "http://localhost:" + this.port + "/hospital-system/doctor/";
    }

    @Order(1)
    @Test
    void save() {
        String url = baseUrl + "save";
        System.out.println(url);
        ResponseEntity<Doctor> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .postForEntity(url, this.doctor, Doctor.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Order(2)
    @Test
    public void read() {
        String url = baseUrl + "read/" + this.doctor.getDocID();
        System.out.println(url);
        ResponseEntity<Doctor> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .getForEntity(url, Doctor.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Order(4)
    @Test
    public void delete() {
        String url = baseUrl + "delete/" + this.doctor.getDocID();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Order(3)
    @Test
    public void findAll() {
        String url = baseUrl + "find-all";
        System.out.println(url);
        ResponseEntity<Doctor[]> response = this.restTemplate
                .withBasicAuth("client-user", "1253208465b")
                .getForEntity(url, Doctor[].class);
        System.out.println("Show All:");
        System.out.println(Arrays.asList(Objects.requireNonNull(response.getBody())));
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertEquals(4, response.getBody().length));
    }

}