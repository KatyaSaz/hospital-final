package ua.sazonova.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.repository.PatientRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    private UserService userService;
    private User user;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserService userService) {
        this.patientRepository = patientRepository;
        this.userService = userService;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transactional
    public void create(Patient patient){
        userService.save(user);
        patient.setUser(user);
        save(patient);
        find(patient).getUser().setIdMoreInfo(patient.getId());
    }

    public void save(Patient patient){
        patientRepository.save(patient);
    }

    public void delete(Patient patient){
        patientRepository.delete(patient);
    }

    public void updateIsActive(Long id){
        Patient patient = getById(id);
        patient.getUser().setIsActive(true);
        save(patient);
    }

    public Patient find(Patient patient){
        return patientRepository.getOne(patient.getId());
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

    public List<Patient> getNonActive(){
        return patientRepository.findPatientsByUserIsActive(false);
    }

    public List<Patient> sortedList(String field, String direction) {
        Sort sort = direction.equals(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending():
                Sort.by(field).descending();
        return patientRepository.findAll(sort);
    }

    public List<Patient> getSortedPatientsOfOneDoctor(Long docId, String field, String direction){
        List<Patient> patients = new ArrayList<>();
        if(field.equals("name")){
            patients = direction.equals(Sort.Direction.ASC.name())?
                    patientRepository.getAllByDoctor_IdEqualsOrderByNameAsc(docId):
                    patientRepository.getAllByDoctor_IdEqualsOrderByNameDesc(docId);
        }else if(field.equals("year")){
            patients = direction.equals(Sort.Direction.ASC.name())?
                    patientRepository.getAllByDoctor_IdEqualsOrderByYearAsc(docId):
                    patientRepository.getAllByDoctor_IdEqualsOrderByYearDesc(docId);
        }
        return  patients;
    }
}
