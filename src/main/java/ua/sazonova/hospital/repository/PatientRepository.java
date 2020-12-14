package ua.sazonova.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sazonova.hospital.entity.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> getPatientsByDoctorEquals(Long id);
}
