package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Supplier;
import za.ac.cput.factory.SupplierFactory;
import za.ac.cput.service.SupplierService;

import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("hospital-system/supplier/")
@Slf4j
public class SupplierController {
    private final SupplierService service;

    @Autowired
    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<Supplier> save(@Valid @RequestBody Supplier supplier) {
        log.info("Save request: {}", supplier);
        Name validateName;
        Supplier validatedSupplier;
        try {
            validatedSupplier = SupplierFactory.createSupplier(supplier.getSuppID(),supplier.getSuppAddress(),supplier.getSuppRegNum());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Supplier saved = service.save(validatedSupplier);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Supplier> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        Supplier supplier = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(supplier);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("find-all")
    public ResponseEntity<Set<Supplier>> getAll() {
        Set<Supplier> suppliers = this.service.getAll();
        return ResponseEntity.ok(suppliers);
    }
}
