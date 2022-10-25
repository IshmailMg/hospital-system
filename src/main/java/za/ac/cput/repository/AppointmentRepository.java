package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Appointment;


/*
   AppointmentRepository.java
   Repository for Apppointment
   Author: Sinazo Mehlomakhulu(216076498)
   Date: 06 August 2022
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,String>{
    Appointment save(Appointment appointment);
}