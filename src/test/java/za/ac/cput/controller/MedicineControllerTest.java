package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Medicine;
import za.ac.cput.factory.MedicineFactory;
import za.ac.cput.service.MedicineService;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MedicineControllerTest {
    private String baseUrl;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MedicineController controller;

    @Autowired
    private MedicineService service;
    private Medicine medicine;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.medicine = MedicineFactory.createMedicine("12124341", "300ml","Drug");
        this.service.save(medicine);
        this.baseUrl = "http://localhost:" + this.port + "/hospital-system/medicine/";
    }
    @Order(1)
    @Test
    void save() {
        String url = baseUrl + "save";
        System.out.println(url);
        ResponseEntity<Medicine> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .postForEntity(url, this.medicine, Medicine.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Order(2)
    @Test
    public void read() {
        String url = baseUrl + "read/" + this.medicine.getMedicineId();
        System.out.println(url);
        ResponseEntity<Medicine> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .getForEntity(url, Medicine.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Order(4)
    @Test
    public void delete() {
        String url = baseUrl + "delete/" + this.medicine.getMedicineId();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Order(3)
    @Test
    public void findAll() {
        String url = baseUrl + "find-all";
        System.out.println(url);
        ResponseEntity<Medicine[]> response = this.restTemplate
                .withBasicAuth("client-user", "1253208465b")
                .getForEntity(url, Medicine[].class);
        System.out.println("Show All:");
        System.out.println(Arrays.asList(Objects.requireNonNull(response.getBody())));
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertEquals(1, response.getBody().length));
    }

}