package ua.sazonova.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> getAllByDoctor_IdEqualsOrderByNameAsc(Long docId);
    List<Patient> getAllByDoctor_IdEqualsOrderByNameDesc(Long docId);
    List<Patient> getAllByDoctor_IdEqualsOrderByYearAsc(Long docId);
    List<Patient> getAllByDoctor_IdEqualsOrderByYearDesc(Long docId);
    List<Patient> getAllByDoctor_IdEquals(Long docId);

    List<Patient> findPatientsByUserIsActive(Boolean isActive);
}
