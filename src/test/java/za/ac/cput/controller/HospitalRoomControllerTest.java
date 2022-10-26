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
import za.ac.cput.domain.Doctor;
import za.ac.cput.domain.HospitalRoom;
import za.ac.cput.factory.HospitalRoomFactory;
import za.ac.cput.service.HospitalRoomService;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/*
    HospitalRoomControllerTest.java
    Test for the Hospital Rooms Controller
    Author: Fayaad Abrahams (218221630)
    Date: 10 October 2022
*/
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HospitalRoomControllerTest {

    private String baseUrl;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private HospitalRoomController controller;

    @Autowired
    private HospitalRoomService hospitalRoomService;

    private HospitalRoom hospitalRoom;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.hospitalRoom = HospitalRoomFactory.createHospitalRoom("101", 2);
        this.hospitalRoomService.save(hospitalRoom);
        this.baseUrl = "http://localhost:" + this.port + "/hospital-system/hospitalroom/";
    }

    @Test
    void a_save() {
        String url = baseUrl + "save";
        System.out.println(url);
        ResponseEntity<HospitalRoom> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .postForEntity(url, this.hospitalRoom, HospitalRoom.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Test
    public void b_read() {
        String url = baseUrl + "read/" + this.hospitalRoom.getRoomID();
        System.out.println(url);
        ResponseEntity<HospitalRoom> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .getForEntity(url, HospitalRoom.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Test
    public void c_delete() {
        String url = baseUrl + "delete/" + this.hospitalRoom.getRoomID();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Test
    public void d_findAll() {
        String url = baseUrl + "find-all";
        System.out.println(url);
        ResponseEntity<HospitalRoom[]> response = this.restTemplate
                .withBasicAuth("client-user", "1253208465b")
                .getForEntity(url, HospitalRoom[].class);
        System.out.println("Show All:");
        System.out.println(Arrays.asList(Objects.requireNonNull(response.getBody())));
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertEquals(1, response.getBody().length));
    }

}