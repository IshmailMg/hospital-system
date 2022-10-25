package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Secretary;
import za.ac.cput.factory.SecretaryFactory;
import za.ac.cput.service.SecretaryService;

import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("hospital-system/secretary/")
@Slf4j
public class SecretaryController {
    private final SecretaryService service;

    @Autowired
    public SecretaryController(SecretaryService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<Secretary> save(@Valid @RequestBody Secretary secretary) {
        log.info("Save request: {}", secretary);
        Name validateName;
        Secretary validatedSecretary;
        try {
            validatedSecretary = SecretaryFactory.createSecretary(secretary.getSecID(), secretary.getSecFirstName(), secretary.getSecLastName());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Secretary saved = service.save(validatedSecretary);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Secretary> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        Secretary secretary = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(secretary);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("find-all")
    public ResponseEntity<Set<Secretary>> getAll() {
        Set<Secretary> secretaries = this.service.getAll();
        return ResponseEntity.ok(secretaries);
    }
}
