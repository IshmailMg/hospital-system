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
import za.ac.cput.domain.Nurse;
import za.ac.cput.factory.NurseFactory;
import za.ac.cput.service.NurseService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/*
    NurseControllerTest.java
    Test for the Nurse Controller
    Author: Fayaad Abrahams (218221630)
    Date: 10 October 2022
*/
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NurseControllerTest {
    public static String SECURITY_USERNAME = "user";
    public static String SECURITY_PASSWORD = "password";

    private String baseUrl;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private NurseController controller;
    @Autowired
    private NurseService nurseService;

    private Nurse nurse;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.nurse = NurseFactory.createNurse("12", "Jonno", "Moriarty");
        this.nurseService.save(nurse);
        this.baseUrl = "http://localhost:" + this.port + "/hospital-system/nurse/";
    }

    @Test
    void a_save() {
        String url = baseUrl + "save";
        System.out.println(url);
        ResponseEntity<Nurse> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .postForEntity(url, this.nurse, Nurse.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Test
    public void b_read() {
        String url = baseUrl + "read/" + this.nurse.getNurseID();
        System.out.println(url);
        ResponseEntity<Nurse> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .getForEntity(url, Nurse.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }


    @Test
    public void c_delete() {
        String url = baseUrl + "delete/" + this.nurse.getNurseID();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Test
    public void d_findAll() {
        String url = baseUrl + "find-all";
        System.out.println(url);
        ResponseEntity<Nurse[]> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .getForEntity(url, Nurse[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertEquals(17, response.getBody().length));
    }

}