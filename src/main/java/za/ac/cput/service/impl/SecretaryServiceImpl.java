package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Secretary;
import za.ac.cput.repository.SecretaryRepository;
import za.ac.cput.service.SecretaryService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SecretaryServiceImpl implements SecretaryService {

    private final SecretaryRepository repository;

    @Autowired
    public SecretaryServiceImpl(SecretaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Secretary save(Secretary secretary) {
        return this.repository.save(secretary);
    }

    @Override
    public Optional<Secretary> read(String id) {
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
    public Set<Secretary> getAll() {
        return this.repository.findAll().stream().collect(Collectors.toSet());
    }
}
