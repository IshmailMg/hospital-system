package za.ac.cput.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.Appointment;
import za.ac.cput.repository.AppointmentRepository;

import static org.junit.jupiter.api.Assertions.*;

//Sinazo Mehlomkhulu(216076498

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class AppointmentServiceImplTest {
@Mock
private AppointmentRepository repository;

private static Appointment appointment1,appointment2;
AppointmentServiceImpl appointmentService;


@Order(1)
    @Test
    void save() {
   appointmentService = new AppointmentServiceImpl(repository);
   appointment1 = new Appointment.Builder().appointmentDate("23 November 2022").appointmentId("00012").build();
   appointment2 = new Appointment.Builder().appointmentDate("16 July 2022").appointmentId("45").build();
   repository.save(appointment1);
   repository.save(appointment2);

   assertAll(
           ()-> assertNotNull(appointment1.getAppointmentId()),
           ()-> assertNotNull(appointment2.getAppointmentId()),
           ()-> assertNotNull(appointment1.getAppointmentDate()),
           ()-> assertNotNull(appointment2.getAppointmentDate()));
        System.out.println("Appointment has been added");
    }
    @Order(2)

    @Test
    void read() {
      repository.getReferenceById(appointment1.getAppointmentId());
      repository.getReferenceById((appointment2.getAppointmentId()));
      assertAll(
              ()-> assertNotNull(appointment1.getAppointmentId()),
              ()-> assertNotNull(appointment2.getAppointmentId()),
              ()-> assertNotSame(appointment1.getAppointmentId(),appointment2.getAppointmentId()),
              ()-> assertNotEquals(appointment1.getAppointmentId(),appointment2.getAppointmentId())
              );
        System.out.println(appointment1.toString());
        System.out.println(appointment2.toString());
    }
@Order(4)
    @Test
    void delete() {
        repository.deleteById(appointment1.getAppointmentId());
        repository.deleteById(appointment2.getAppointmentId());

        assertAll(
                ()->assertNotNull(appointment1.getAppointmentId()),
                ()->assertNotNull(appointment2.getAppointmentId()),
                ()-> assertNotSame(appointment1.getAppointmentId(), appointment2.getAppointmentId()),
                ()-> assertNotEquals(appointment1.getAppointmentId(), appointment2.getAppointmentId())
        );
    System.out.println("Appointment successfully deleted");

    }
    @Order(3)
    @Test
    void findAll() {
        System.out.println(repository.findAll());
    }
}