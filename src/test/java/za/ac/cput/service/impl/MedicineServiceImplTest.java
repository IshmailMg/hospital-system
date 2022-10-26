package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Medicine;
import za.ac.cput.factory.MedicineFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
class MedicineServiceImplTest {
    private static final Medicine oneMedicine = MedicineFactory.createMedicine("389773","55","Panado");
    private static final Medicine twoMedicine = MedicineFactory.createMedicine("5899004","30","PainKillers");
    private static final Medicine threeMedicine = MedicineFactory.createMedicine("5899009","39","Helax");

    @Autowired
    private MedicineServiceImpl service;

    @Test
    public void m_save() {
        System.out.println("Created Medicine: ");
        Medicine oneMedicineCreated = service.save(oneMedicine);
        assertNotNull(oneMedicineCreated);
        System.out.println(oneMedicineCreated);

        Medicine twoMedicineCreated = service.save(twoMedicine);
        assertNotNull(twoMedicineCreated);
        System.out.println(twoMedicineCreated);

        Medicine threeMedicineCreated = service.save(threeMedicine);
        assertNotNull(threeMedicineCreated);
        System.out.println(threeMedicineCreated);
    }

    @Test
    public void s_read() {
        Optional<Medicine> read = service.read(oneMedicine.getMedicineId());
        assertAll(() -> assertTrue(read.isPresent()),() -> assertEquals(oneMedicine, read.get()));
        System.out.println("Read medicine: " + read);
    }

    @Test
    public void i_delete() {
        boolean success = service.delete(twoMedicine.getMedicineId());
        assertTrue(success);
        System.out.println("Deleted: ");
    }

    @Test
    void f_getAll() {
        System.out.println("Get All");
        System.out.println(service.getAll());
    }
}