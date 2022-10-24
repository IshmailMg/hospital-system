package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.HospitalRoom;
import za.ac.cput.factory.HospitalRoomFactory;
import za.ac.cput.service.HospitalRoomService;

import javax.lang.model.element.Name;
import javax.validation.Valid;
import java.util.Set;

/*
    HospitalRoomController.java
    Controller for the Hospital Rooms
    Author: Fayaad Abrahams (218221630)
    Date: 6 October 2022
*/
@RestController
@RequestMapping("hospital-system/hospitalroom/")
@Slf4j
public class HospitalRoomController {
    private final HospitalRoomService service;

    @Autowired
    public HospitalRoomController(HospitalRoomService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<HospitalRoom> save(@Valid @RequestBody HospitalRoom hospitalRoom) {
        log.info("Save request: {}", hospitalRoom);
        Name validateName;
        HospitalRoom validatedHospitalRoom;
        try {
            validatedHospitalRoom = HospitalRoomFactory.createHospitalRoom(hospitalRoom.getRoomID(), hospitalRoom.getRoomFloor());
        } catch (IllegalArgumentException ex) {
            log.info("Save request error: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        HospitalRoom saved = service.save(validatedHospitalRoom);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<HospitalRoom> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        HospitalRoom hospitalRoom = this.service.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(hospitalRoom);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete request{}", id);
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("find-all")
    public ResponseEntity<Set<HospitalRoom>> getAll() {
        Set<HospitalRoom> hospitalRoomList = this.service.getAll();
        return ResponseEntity.ok(hospitalRoomList);
    }
}
