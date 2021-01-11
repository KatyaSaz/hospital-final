package ua.sazonova.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.repository.DoctorRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private UserService userService;
    private User user;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, UserService userService) {
        this.doctorRepository = doctorRepository;
        this.userService = userService;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transactional
    public void createDoctor(Doctor doctor) {
        userService.save(user);
        doctor.setUser(user);
        save(doctor);
        find(doctor).getUser().setIdMoreInfo(doctor.getId());
    }

    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void delete(Doctor doctor) {
        List<Patient> patients = doctor.getPatients();
        for (Patient pat : patients) {
            pat.setDoctor(doctorRepository.getOne(Doctor.DEFAULT_DOCTOR_ID));
        }
        doctorRepository.delete(doctor);
    }

    public void updateIsActive(Long id) {
        Doctor doctor = getById(id);
        doctor.getUser().setIsActive(true);
        save(doctor);
    }

    public Doctor find(Doctor doctor) {
        return doctorRepository.getOne(doctor.getId());
    }

    public Doctor getById(Long id) {
        if (doctorRepository.findById(id).isPresent()) {
            return doctorRepository.findById(id).get();
        }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getNonActive() {
        return doctorRepository.findDoctorsByUserIsActive(false);
    }

    public List<Doctor> getByOneType(DoctorType type) {
        return doctorRepository.findByType(type);
    }

    public List<Doctor> sortedList(String field, String direction) {
        Sort sort = direction.equals(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() :
                Sort.by(field).descending();
        return doctorRepository.findAll(sort);
    }
}
