package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import za.ac.cput.domain.Driver;
import za.ac.cput.service.impl.DriverServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    DriverController.java
    Contoller for Driver
    Author: Nonzwakazi Mgxaji
    Student number: 213181584
    Date: 13 October 2022
*/

@RestController
@RequestMapping("hospital-system/driver")
public class DriverController {
    private DriverServiceImpl driverService;
    @Autowired
    public DriverController(DriverServiceImpl driverService){
        this.driverService = driverService;
    }

    //Read method
    @GetMapping("/find/{id}")
    public Driver find(@PathVariable String id){
        return driverService.findById(id);
    }

    //Save / Create Method
    @PostMapping("/save")
    public Driver save(@RequestBody Driver driver){
        return driverService.save(driver);
    }

    //Delete method
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id){
        return driverService.delete(id);
    }

    @GetMapping("/get-all")
    public List<Driver> getAll(){return this.driverService.getAll();}



}