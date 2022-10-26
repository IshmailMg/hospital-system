package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Doctor;
import za.ac.cput.repository.DoctorRepository;
import za.ac.cput.service.DoctorService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.repository.save(doctor);
    }

    @Override
    public Optional<Doctor> read(String id) {
        return this.repository.findById(id);
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
    public Set<Doctor> getAll() {
        return this.repository.findAll().stream().collect(Collectors.toSet());
    }
}
