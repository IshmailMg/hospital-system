package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.MedicalAid;
import za.ac.cput.factory.HospitalRoomFactory;
import za.ac.cput.factory.MedicalAidFactory;
import za.ac.cput.service.MedicalAidService;

import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("hospital-system/medicalaid/")
@Slf4j
public class MedicalAidController {
    private final MedicalAidService service;

    @Autowired
    public MedicalAidController(MedicalAidService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<MedicalAid> save(@Valid @RequestBody MedicalAid medicalAid) {
        log.info("Save request: {}", medicalAid);
        Name validateName;
        MedicalAid validatedMedicalAid;
        try {
            validatedMedicalAid = MedicalAidFactory.createMedicalAid(medicalAid.getMedicalNum(), medicalAid.getMedicalName(), medicalAid.getMedicAddr());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        MedicalAid saved = service.save(validatedMedicalAid);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<MedicalAid> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        MedicalAid medicalAid = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(medicalAid);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("find-all")
    public ResponseEntity<Set<MedicalAid>> getAll() {
        Set<MedicalAid> medicalAids = this.service.getAll();
        return ResponseEntity.ok(medicalAids);
    }
}
