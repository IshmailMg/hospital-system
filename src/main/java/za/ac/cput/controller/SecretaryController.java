package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Secretary;
import za.ac.cput.service.impl.SecretaryService;

import java.util.List;

@RestController
@RequestMapping(path = "hospital-system/secretary")
@Slf4j
public class SecretaryController {

    private final SecretaryService secretaryService;

    @Autowired
    public SecretaryController(SecretaryService secretaryService)
    {
        this.secretaryService = secretaryService;
    }

    @PostMapping("create")
    public Secretary createSecretary(@RequestBody Secretary secretary)
    {
        return secretaryService.save(secretary);
    }

    @GetMapping("read/{id}")
    public Secretary readSecretary(@PathVariable String id)
    {
        return secretaryService.get(id);
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteSecretary(@RequestParam("id") String id)
    {
        return secretaryService.delete(id);
    }

    @GetMapping("get-all")
    public List<Secretary> listAllSecretaries()
    {
        return secretaryService.listSecretaries();
    }

}
