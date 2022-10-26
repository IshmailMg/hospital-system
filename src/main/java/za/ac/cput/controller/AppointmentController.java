package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Appointment;
import za.ac.cput.factory.AppointmentFactory;
import za.ac.cput.service.AppointmentService;

import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.Set;
import za.ac.cput.service.impl.AppointmentServiceImpl;
import java.util.List;

@RestController
@RequestMapping("hospital-system/appointment/")
@Slf4j
public class AppointmentController {
    private final AppointmentService service;

    @Autowired
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<Appointment> save(@Valid @RequestBody Appointment appointment) {
        log.info("Save request: {}", appointment);
        Name validateName;
        Appointment validatedAppointment;
        try {
            validatedAppointment = AppointmentFactory.createAppointment(appointment.getAppointmentId(), appointment.getAppointmentDate(), appointment.getAppointmentDuration(), appointment.getAppointmentTime());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Appointment saved = service.save(validatedAppointment);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Appointment> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        Appointment appointment = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("find-all")
    public ResponseEntity<Set<Appointment>> getAll() {
        Set<Appointment> appointments = this.service.getAll();
        return ResponseEntity.ok(appointments);
    }
}
