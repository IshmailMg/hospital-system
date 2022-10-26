package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Doctor;
import za.ac.cput.domain.HospitalRoom;
import za.ac.cput.domain.Secretary;
import za.ac.cput.factory.DoctorFactory;
import za.ac.cput.factory.HospitalRoomFactory;
import za.ac.cput.factory.SecretaryFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class SecretaryServiceImplTest {
    private static final Secretary secretary1 = SecretaryFactory.createSecretary("123", "Lady","Goober");
    private static final Secretary secretary2 = SecretaryFactory.createSecretary("124", "Manly","Manson");
    private static final Secretary secretary3 = SecretaryFactory.createSecretary("5", "David","Gably");


    @Autowired
    private SecretaryServiceImpl service;

    @Test
    public void a_save() {
        System.out.println("Created: ");
        Secretary created1 = service.save(secretary1);
        assertNotNull(created1);
        System.out.println(created1);

        Secretary created2 = service.save(secretary2);
        assertNotNull(created2);
        System.out.println(created2);

        Secretary created3 = service.save(secretary3);
        assertNotNull(created3);
        System.out.println(created3);
    }

    @Test
    public void b_read() {
        Optional<Secretary> read = service.read(secretary1.getSecID());
        assertAll(() -> assertTrue(read.isPresent()), () -> assertEquals(secretary1, read.get()));
        System.out.println("Read: " + read);
    }

    @Test
    public void f_delete() {
        boolean success = service.delete(secretary3.getSecID());
        assertTrue(success);
        System.out.println("Deleted: " + success);
    }

    @Test
    public void d_getAll() {
        System.out.println("Get All");
        System.out.println(service.getAll());
    }

}
