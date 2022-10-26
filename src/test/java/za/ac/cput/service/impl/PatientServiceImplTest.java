package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Patient;
import za.ac.cput.factory.PatientFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    PatientServiceImplTest.java
    Entity: PatientServiceImplTest
    Author: Ishmail T Mgwena (215088417)
    Date: 15 August 2022
*/
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class PatientServiceImplTest {
    private static final Patient firstPatient = PatientFactory.createPatient("4567889","Tom","Davis","21 Ford Street","072000890y");
    private static final Patient secondPatient = PatientFactory.createPatient("98765699","David","Smith","38 Durban Road","0817890448");
    private static final Patient thirdPatient = PatientFactory.createPatient("3789733","Thabo","Hadebe","398 Claremont Road","9873339993");

    @Autowired
    private PatientServiceImpl service;

    @Test
    public void x_save(){
        System.out.println("Created patients: ");
        Patient onePatientCreated = service.save(firstPatient);
        assertNotNull(onePatientCreated);
        System.out.println(onePatientCreated);

        Patient secondPatientCreated = service.save(secondPatient);
        assertNotNull(secondPatientCreated);
        System.out.println(secondPatientCreated);

        Patient thirdPatientCreated = service.save(thirdPatient);
        assertNotNull(thirdPatientCreated);
        System.out.println(thirdPatientCreated);
    }
    @Test
    public void y_read(){
        Optional<Patient> read = service.read(firstPatient.getPatIdNum());
        assertAll(() -> assertTrue(read.isPresent()), () -> assertEquals(firstPatient, read.get()));
        System.out.println("Read patient:" + read);
    }
    @Test
    public void s_delete() {
        boolean success = service.delete(secondPatient.getPatIdNum());
        assertTrue(success);
        System.out.println("Deleted: ");
    }
    @Test
    public void z_getALl() {
        System.out.println("Get All");
        System.out.println(service.getAll());
    }
}