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
import za.ac.cput.domain.HospitalRoom;
import za.ac.cput.domain.MedicalAid;
import za.ac.cput.factory.MedicalAidFactory;
import za.ac.cput.service.MedicalAidService;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MedicalAidControllerTest {

    private String baseUrl;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MedicalAidController controller;

    @Autowired
    private MedicalAidService medicalAidService;

    private MedicalAid medicalAid;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.medicalAid = MedicalAidFactory.createMedicalAid("101312313", "Discovery","Grassy Park");
        this.medicalAidService.save(medicalAid);
        this.baseUrl = "http://localhost:" + this.port + "/hospital-system/medicalaid/";
    }

    @Test
    void a_save() {
        String url = baseUrl + "save";
        System.out.println(url);
        ResponseEntity<HospitalRoom> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .postForEntity(url, this.medicalAid, HospitalRoom.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Test
    public void b_read() {
        String url = baseUrl + "read/" + this.medicalAid.getMedicalNum();
        System.out.println(url);
        ResponseEntity<MedicalAid> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .getForEntity(url, MedicalAid.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Test
    public void c_delete() {
        String url = baseUrl + "delete/" + this.medicalAid.getMedicalNum();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Test
    public void d_findAll() {
        String url = baseUrl + "find-all";
        System.out.println(url);
        ResponseEntity<MedicalAid[]> response = this.restTemplate
                .withBasicAuth("client-user", "1253208465b")
                .getForEntity(url, MedicalAid[].class);
        System.out.println("Show All:");
        System.out.println(Arrays.asList(Objects.requireNonNull(response.getBody())));
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertEquals(2, response.getBody().length));
    }

}