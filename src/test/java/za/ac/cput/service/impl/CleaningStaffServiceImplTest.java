package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.CleaningStaff;
import za.ac.cput.factory.CleaningStaffFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class CleaningStaffServiceImplTest {
    private static final CleaningStaff cleaningStaff1 = CleaningStaffFactory.createCleaningStaff("e33242343", "David","Langerhout");
    private static final CleaningStaff cleaningStaff2 = CleaningStaffFactory.createCleaningStaff("e33sef343", "Tania","Zania");
    private static final CleaningStaff cleaningStaff3 = CleaningStaffFactory.createCleaningStaff("e33242343", "Patricia","Hout");

    @Autowired
    private CleaningStaffServiceImpl service;

    @Test
    public void a_save() {
        System.out.println("Created: ");
        CleaningStaff created1 = service.save(cleaningStaff1);
        assertNotNull(created1);
        System.out.println(created1);

        CleaningStaff created2 = service.save(cleaningStaff2);
        assertNotNull(created2);
        System.out.println(created2);

        CleaningStaff created3 = service.save(cleaningStaff3);
        assertNotNull(created3);
        System.out.println(created3);

    }

    @Test
    public void b_read() {
        Optional<CleaningStaff> read = service.read(cleaningStaff1.getEmployeeId());
        assertAll(() -> assertTrue(read.isPresent()), () -> assertEquals(cleaningStaff1, read.get()));
        System.out.println("Read: " + read);
    }

    @Test
    public void f_delete() {
        boolean success = service.delete(cleaningStaff3.getEmployeeId());
        assertFalse(success);
        System.out.println("Deleted");
    }

    @Test
    public void d_getALl() {
        System.out.println("Get All");
        System.out.println(service.getAll());
    }

}
