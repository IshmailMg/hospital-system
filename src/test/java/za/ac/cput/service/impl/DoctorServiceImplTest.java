package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Doctor;
import za.ac.cput.domain.HospitalRoom;
import za.ac.cput.factory.DoctorFactory;
import za.ac.cput.factory.HospitalRoomFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class DoctorServiceImplTest {
    private static final Doctor doctor1 = DoctorFactory.createDoctor("12", "Langly","Goober","0786549023");
    private static final Doctor doctor2 = DoctorFactory.createDoctor("13", "David","Draven","0313139023");
    private static final Doctor doctor3 = DoctorFactory.createDoctor("14", "Fauna","Flowers","08972398345");

    @Autowired
    private DoctorServiceImpl service;

    @Test
    public void a_save() {
        System.out.println("Created: ");
        Doctor created1 = service.save(doctor1);
        assertNotNull(created1);
        System.out.println(created1);

        Doctor created2 = service.save(doctor2);
        assertNotNull(created2);
        System.out.println(created2);

        Doctor created3 = service.save(doctor3);
        assertNotNull(created3);
        System.out.println(created3);
    }

    @Test
    public void b_read() {
        Optional<Doctor> read = service.read(doctor1.getDocID());
        assertAll(() -> assertTrue(read.isPresent()), () -> assertEquals(doctor1, read.get()));
        System.out.println("Read: " + read);
    }

    @Test
    public void f_delete() {
        boolean success = service.delete(doctor3.getDocID());
        assertTrue(success);
        System.out.println("Deleted: " + success);
    }

    @Test
    public void d_getAll() {
        System.out.println("Get All");
        System.out.println(service.getAll());
    }

}
