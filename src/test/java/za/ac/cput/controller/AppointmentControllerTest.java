package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.CleaningStaff;
import za.ac.cput.factory.AppointmentFactory;
import za.ac.cput.factory.CleaningStaffFactory;
import za.ac.cput.service.AppointmentService;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

//Sinazo Mehlomakhulu(216076498)

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppointmentControllerTest {
    private String baseUrl;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AppointmentController controller;
    @Autowired
    private AppointmentServiceImpl service;

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.appointment = AppointmentFactory.createAppointment("12", "23 November 2022","30Min","10:00");
        this.service.save(appointment);
        this.baseUrl = "http://localhost:" + this.port + "/hospital-system/appointment/";
    }

    @Test
    void a_save() {
        String url = baseUrl + "save";
        System.out.println(url);
        ResponseEntity<Appointment> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .postForEntity(url, this.appointment, Appointment.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Order(2)
    @Test
    public void read() {
        String url = baseUrl + "read/" + this.appointment.getAppointmentId();
        System.out.println(url);
        ResponseEntity<Appointment> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .getForEntity(url, Appointment.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }


    @Test
    public void delete() {
        String url = baseUrl + "delete/" + this.appointment.getAppointmentId();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Order(3)
    @Test
    public void d_findAll() {
        String url = baseUrl + "all/";
        System.out.println(url);
        ResponseEntity<Appointment[]> response = this.restTemplate
                .withBasicAuth("client-user", "1253208465b")
                .getForEntity(url, Appointment[].class);
        System.out.println("Show All:");
        System.out.println(Arrays.asList(Objects.requireNonNull(response.getBody())));
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertEquals(3, response.getBody().length));
    }


}




