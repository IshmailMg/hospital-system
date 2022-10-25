package za.ac.cput.service;

import za.ac.cput.domain.Supplier;

import java.util.Set;

/*
    SupplierService.java
    Services for Supplier
    Author: Nonzwakazi Mgxaji
    Student number: 213181584
    Date: 14 August 2022
*/

public interface SupplierService extends IService<Supplier, String>{
    Supplier findById(String id);

    Set<Supplier> getAll();
}
