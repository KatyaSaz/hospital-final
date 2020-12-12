package ua.sazonova.hospital.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.sazonova.hospital.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
