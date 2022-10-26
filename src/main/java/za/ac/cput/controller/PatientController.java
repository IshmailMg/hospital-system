package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Patient;
import za.ac.cput.factory.PatientFactory;
import za.ac.cput.service.PatientService;

import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("hospital-system/patient/")
@Slf4j
public class PatientController {
    private final PatientService service;

    @Autowired
    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<Patient> save(@Valid @RequestBody Patient patient) {
        log.info("Save request: {}", patient);
        Name validateName;
        Patient validatedPatient;
        try {
            validatedPatient = PatientFactory.createPatient(patient.getPatIdNum(), patient.getPatFirstName(), patient.getPatLastName(), patient.getPatAddress(), patient.getPatCellNum());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Patient saved = service.save(validatedPatient);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Patient> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        Patient cleaningStaff = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(cleaningStaff);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("find-all")
    public ResponseEntity<Set<Patient>> getAll() {
        Set<Patient> cleaningStaffs = this.service.getAll();
        return ResponseEntity.ok(cleaningStaffs);
    }
}
