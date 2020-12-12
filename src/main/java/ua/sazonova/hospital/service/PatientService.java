package ua.sazonova.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.repository.DoctorRepository;
import ua.sazonova.hospital.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient find(Patient patient){
        return patientRepository.getOne(patient.getId());
    }

    public void save(Patient patient){
        patientRepository.save(patient);
    }

    public void delete(Patient patient){
        patientRepository.delete(patient);
    }

    public Patient getById(Long id){
        if(patientRepository.findById(id).isPresent()){
            return patientRepository.findById(id).get();
        }
        return null;
    }

//
//
//

//
//    public Doctor createDoctor(Doctor doctor, List<Patient> patients) {
//        Doctor newDoc = new Doctor();
//        newDoc.setName(doctor.getName());
//        newDoc.setSurname(doctor.getSurname());
//        newDoc.setType(doctor.getType());
//        newDoc.setExperience(doctor.getExperience());
//        newDoc.setPatients(patients);
//        return doctorRepository.save(newDoc);
//    }
}
