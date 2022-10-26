package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Invoice;
import za.ac.cput.factory.InvoiceFactory;
import za.ac.cput.service.InvoiceService;

import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("hospital-system/invoice/")
@Slf4j
public class InvoiceController {
    private final InvoiceService service;

    @Autowired
    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<Invoice> save(@Valid @RequestBody Invoice invoice) {
        log.info("Save request: {}", invoice);
        Name validateName;
        Invoice validatedInvoice;
        try {
            validatedInvoice = InvoiceFactory.createInvoice(invoice.getInvoiceNum(), invoice.getInvoiceAmount(), invoice.getInvoiceType(), invoice.getInvoiceDate());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Invoice saved = service.save(validatedInvoice);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Invoice> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        Invoice invoice = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(invoice);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("find-all")
    public ResponseEntity<Set<Invoice>> getAll() {
        Set<Invoice> invoices = this.service.getAll();
        return ResponseEntity.ok(invoices);
    }
}
