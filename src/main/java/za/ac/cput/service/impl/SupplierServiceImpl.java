package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.HospitalRoom;
import za.ac.cput.domain.Supplier;
import za.ac.cput.repository.SupplierRepository;
import za.ac.cput.service.SupplierService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
    SupplierServiceImpl.java
    Services Implementation for Supplier
    Author: Nonzwakazi Mgxaji
    Student number: 213181584
    Date: 16 August 2022
*/

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl (SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public Supplier getSupplier(String id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return this.supplierRepository.save(supplier);
    }

    @Override
    public Set<Supplier> getAll() {
        return new HashSet<>(this.supplierRepository.findAll());
    }

    @Override
    public Supplier findById(String id) {
        return supplierRepository.findById(id).orElse(new Supplier());
    }

    @Override
    public Optional<Supplier> read(String id) {
        return this.supplierRepository.findById(id);
    }

    @Override
    public boolean delete(String id) {
        this.supplierRepository.deleteById(id);
        return false;
    }
    public List listSupplier() {
        return supplierRepository.findAll().stream().collect(Collectors.toList());
    }
}
