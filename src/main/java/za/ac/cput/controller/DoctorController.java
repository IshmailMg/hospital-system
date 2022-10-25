package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Doctor;
import za.ac.cput.factory.DoctorFactory;
import za.ac.cput.service.DoctorService;

import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("hospital-system/doctor/")
@Slf4j
public class DoctorController {
    private final DoctorService service;

    @Autowired
    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<Doctor> save(@Valid @RequestBody Doctor doctor) {
        log.info("Save request: {}", doctor);
        Name validateName;
        Doctor validatedDoctor;
        try {
            validatedDoctor = DoctorFactory.createDoctor(doctor.getDocID(), doctor.getFirstName(), doctor.getLastName(), doctor.getCellNum());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Doctor saved = service.save(validatedDoctor);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Doctor> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        Doctor doctor = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("find-all")
    public ResponseEntity<Set<Doctor>> getAll() {
        Set<Doctor> doctors = this.service.getAll();
        return ResponseEntity.ok(doctors);
    }
}
