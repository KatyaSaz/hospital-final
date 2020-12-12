package ua.sazonova.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sazonova.hospital.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
