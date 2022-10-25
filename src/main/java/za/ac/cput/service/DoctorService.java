package za.ac.cput.service;

import za.ac.cput.domain.Doctor;

import java.util.Set;

public interface DoctorService extends IService<Doctor, String> {
    Set<Doctor> getAll();
}