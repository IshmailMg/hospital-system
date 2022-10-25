package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.SupplierFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
    SupplierServiceImplTest.java
    Service Implementation Test for Supplier
    Author: Nonzwakazi Mgxaji
    Student number: 213181584
    Date: 10 October 2022
*/

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
public class SupplierServiceImplTest {
    private static final Supplier supplier1 = SupplierFactory.createSupplier("1233", "1428 Main Road Rondebosch", "CA123456");
    private static final Supplier supplier2 = SupplierFactory.createSupplier("4566", "3 Heather Street Mandalay", "CY123456");
    private static final Supplier supplier3 = SupplierFactory.createSupplier("7899", "39 St Porter Lane Landsdowne", "CL748596");

    @Autowired
    private SupplierServiceImpl service;

    @Test
    void a_save() {
        System.out.println("Created: ");
        Supplier created1 = service.save(supplier1);
        assertNotNull(created1);
        System.out.println(created1);

        Supplier created2 = service.save(supplier2);
        assertNotNull(created2);
        System.out.println(created2);

        Supplier created3 = service.save(supplier3);
        assertNotNull(created3);
        System.out.println(created3);
    }

    @Test
    void b_read() {
        Optional<Supplier> read = service.read(supplier1.getSuppID());
        assertAll(() -> assertTrue(read.isPresent()), () -> assertEquals(supplier1, read.get()));
        System.out.println("Read: " + read);
    }

    @Test
    void f_delete() {
        boolean success = service.delete(supplier2.getSuppID());
        assertTrue(success);
        System.out.println("Deleted: " + true);
    }

    @Test
    void d_getALl() {
        System.out.println("Get All");
        System.out.println(service.getAll());
    }
}
