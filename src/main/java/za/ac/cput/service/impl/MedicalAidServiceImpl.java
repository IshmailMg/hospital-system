package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.MedicalAid;
import za.ac.cput.repository.MedicalAidRepository;
import za.ac.cput.service.MedicalAidService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
   MedicalAidServiceImpl.java
    Service impl for  MedicalAid
    Author: Shina Kara (219333181)
    Date: 13 August 2022
*/
@Service
public class MedicalAidServiceImpl implements MedicalAidService {

    private final MedicalAidRepository repository;

    @Autowired
    public MedicalAidServiceImpl(MedicalAidRepository repository) {
        this.repository = repository;
    }


    @Override
    public MedicalAid save(MedicalAid medicalAid) {
        return this.repository.save(medicalAid);
    }

    @Override
    public Optional<MedicalAid> read(String id) {
        return this.repository.findById(id) ;
    }

    @Override
    public boolean delete(String id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Set<MedicalAid> getAll() {
        return this.repository.findAll().stream().collect(Collectors.toSet());
    }
}
