package ua.sazonova.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.repository.DoctorRepository;
import ua.sazonova.hospital.repository.PatientRepository;

import java.util.ArrayList;
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

//    public void update(Patient updatedPatient){
//        Patient needUpdatePat = patientRepository.getOne(updatedPatient.getId());
//        patientRepository.save()
//    }

    public void delete(Patient patient){
        patientRepository.delete(patient);
    }

    public Patient getById(Long id){
        if(patientRepository.findById(id).isPresent()){
            return patientRepository.findById(id).get();
        }
        return null;
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

//    public List<Patient> getPatientsOfOneDoctor(Long id){
//        List<Patient> allPat = getAllPatients();
//        List<Patient> res = new ArrayList<>();
//        for(Patient p:res){
//            if(p.getDoctor().getId().equals(id)){
//                res.add(p);
//            }
//        }
//        return res;
//    }

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
