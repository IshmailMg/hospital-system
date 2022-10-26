package za.ac.cput.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.CleaningStaff;
import za.ac.cput.repository.AppointmentRepository;
import za.ac.cput.service.AppointmentService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//Sinazo Mehlomakhulu(21607698)
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository repository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Appointment save(Appointment appointment) {

        return repository.save(appointment);
    }

    @Override
    public Optional<Appointment> read(String id) {
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
    public List<Appointment> getAll() {
        return this.repository.findAll();
    }
    @Override
    public Appointment findById(String id) {
        return repository.findById(id).orElse(new Appointment());
    }

    public List listAppointment()
    {
        return repository.findAll().stream().collect(Collectors.toList());
    }


}



