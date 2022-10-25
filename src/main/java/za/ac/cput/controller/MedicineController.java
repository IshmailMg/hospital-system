package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Medicine;
import za.ac.cput.factory.MedicineFactory;
import za.ac.cput.service.MedicineService;

import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("hospital-system/medicine/")
@Slf4j
public class MedicineController {
    private final MedicineService service;

    @Autowired
    public MedicineController(MedicineService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<Medicine> save(@Valid @RequestBody Medicine medicine) {
        log.info("Save request: {}", medicine);
        Name validateName;
        Medicine validatedMedicine;
        try {
            validatedMedicine = MedicineFactory.createMedicine(medicine.getMedicineId(), medicine.getMedicineAmount(), medicine.getMedicineAmount());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Medicine saved = service.save(validatedMedicine);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Medicine> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        Medicine medicine = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(medicine);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("find-all")
    public ResponseEntity<Set<Medicine>> getAll() {
        Set<Medicine> medicines = this.service.getAll();
        return ResponseEntity.ok(medicines);
    }
}
