package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Nurse;
import za.ac.cput.factory.NurseFactory;
import za.ac.cput.service.NurseService;

import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.Set;

/*
    NurseController.java
    Controller for the Nurses
    Author: Fayaad Abrahams (218221630)
    Date: 6 October 2022
*/
@RestController
@Controller
@RequestMapping("hospital-system/nurse/")
@Slf4j
public class NurseController {
    private final NurseService service;

    @Autowired
    public NurseController(NurseService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<Nurse> save(@Valid @RequestBody Nurse nurse) {
        log.info("Save request: {}", nurse);
        Name validateName;
        Nurse validatedNurse;
        try {
            validatedNurse = NurseFactory.createNurse(nurse.getNurseID(), nurse.getNurseFirstName(), nurse.getNurseLastName());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Nurse saved = service.save(validatedNurse);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Nurse> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        Nurse nurse = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(nurse);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("find-all")
    public ResponseEntity<Set<Nurse>> getAll() {
        Set<Nurse> nurseSet = this.service.getAll();
        return ResponseEntity.ok(nurseSet);
    }
}

