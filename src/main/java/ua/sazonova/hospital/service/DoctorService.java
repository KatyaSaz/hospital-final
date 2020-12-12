package ua.sazonova.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor find(Doctor doctor){
        return doctorRepository.getOne(doctor.getId());
    }

    public void save(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public void delete(Doctor doctor){
        doctorRepository.delete(doctor);
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor getById(Long id){
        if(doctorRepository.findById(id).isPresent()){
            return doctorRepository.findById(id).get();
        }
        return null;
    }

    public Doctor createDoctor(Doctor doctor, List<Patient> patients) {
        Doctor newDoc = new Doctor();
        newDoc.setName(doctor.getName());
        newDoc.setSurname(doctor.getSurname());
        newDoc.setType(doctor.getType());
        newDoc.setExperience(doctor.getExperience());
        newDoc.setPatients(patients);
        return doctorRepository.save(newDoc);
    }
}
