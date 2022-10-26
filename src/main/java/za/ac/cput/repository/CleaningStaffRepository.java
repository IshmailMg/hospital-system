package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.CleaningStaff;
/*
CleaningStaff Repository.java
        Repository for CleaningStaff
        Author: Sinazo Mehlomakhulu(216076498)
        Date: 06 August 2022

 */
@Repository
public interface CleaningStaffRepository extends JpaRepository<CleaningStaff, String> {
}