package ua.sazonova.hospital.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.enam.DoctorType;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByType(DoctorType type);

    List<Doctor> findDoctorsByUserIsActive(Boolean isActive);
}
