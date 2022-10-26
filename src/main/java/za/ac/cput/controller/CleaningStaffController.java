package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.CleaningStaff;
import za.ac.cput.factory.CleaningStaffFactory;
import za.ac.cput.domain.Driver;
import za.ac.cput.domain.HospitalRoom;
import za.ac.cput.service.CleaningStaffService;
import za.ac.cput.service.impl.CleaningStaffServiceImpl;
import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("hospital-system/cleaningStaff/")
@Slf4j
public class CleaningStaffController {
    private CleaningStaffServiceImpl service;

    @Autowired
    public CleaningStaffController(CleaningStaffServiceImpl service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<CleaningStaff> save(@Valid @RequestBody CleaningStaff cleaningStaff) {
        log.info("Save request: {}", cleaningStaff);
        Name validateName;
        CleaningStaff validatedCleaningStaff;
        try {
            validatedCleaningStaff = CleaningStaffFactory.createCleaningStaff(cleaningStaff.getEmployeeId(), cleaningStaff.getEmployeeFirstName(), cleaningStaff.getEmployeeLastName());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        CleaningStaff saved = service.save(validatedCleaningStaff);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<CleaningStaff> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        CleaningStaff cleaningStaff = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(cleaningStaff);
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("find-all")
    public ResponseEntity<Set<CleaningStaff>> getAll() {
        Set<CleaningStaff> cleaningStaffs = this.service.getAll();
        return ResponseEntity.ok(cleaningStaffs);
        
    }

    @GetMapping("/all")
    public List<CleaningStaff> getAll(){return this.service.getAll();}

}
