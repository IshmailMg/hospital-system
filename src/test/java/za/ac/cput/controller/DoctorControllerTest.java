package za.ac.cput.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.Doctor;
import za.ac.cput.service.impl.DoctorService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DoctorController doctorController;

    @Autowired
    public DoctorService doctorService;

    private Doctor doctor;

    @Test
    void save()
    {
        this.doctor = new Doctor("02Mark95", "Mark", "Zuckerberg", "0236547895");
        this.doctorService.saveDoctor(doctor);
        assertNotNull(doctor);
    }

    @Test
    void read()
    {
        Doctor doctorIdProvided = new Doctor("07sJulie14", "", "", "");
        Doctor checkDocId = this.doctorService.getDoctor(doctorIdProvided.getDocID());
        System.out.println(checkDocId);
        assertSame(checkDocId, doctorIdProvided);
    }

    @Test
    void delete()
    {
        Doctor doctorId = new Doctor("02Mark95", "", "", "");
        boolean deleted = doctorService.deleteDoctor(doctorId.getDocID());
        assertTrue(deleted);
    }

    @Test
    void findAll()
    {
        String url = "http://localhost:" + this.port + "/hospital-system/doctor/get-all";

        ResponseEntity<Doctor[]> response = this.restTemplate
                .withBasicAuth("user", "user123")
                .getForEntity(url, Doctor[].class);
        assertSame(HttpStatus.OK, response.getStatusCode());
    }

}
