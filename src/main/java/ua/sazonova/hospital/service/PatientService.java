package ua.sazonova.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.repository.DoctorRepository;
import ua.sazonova.hospital.repository.PatientRepository;
import ua.sazonova.hospital.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    private UserService userService;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Autowired
    public PatientService(PatientRepository patientRepository, UserService userService) {
        this.patientRepository = patientRepository;
        this.userService = userService;
    }

    public Patient find(Patient patient){
        return patientRepository.getOne(patient.getId());
    }

    public void save(Patient patient){
        patientRepository.save(patient);
    }

    @Transactional
    public void create(Patient patient){
        userService.save(user);
        patient.setUser(user);
        save(patient);
        find(patient).getUser().setIdMoreInfo(patient.getId());
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

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public List<Patient> sortedList(String field, String direction){
        Sort sort = direction.equals(Sort.Direction.ASC.name())?
                Sort.by(field).ascending():
                Sort.by(field).descending();
        return patientRepository.findAll(sort);
    }

//    public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField) :
//                Sort.by(sortField).descending();
//        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
//        return this.bookRepository.findAll(pageable);
//    }

    public List<Patient> getSortedPatientsOfOneDoctor(Long docId, String field, String direction){

        List<Patient> patients = patientRepository.getPatientsByDoctorEquals(docId);
//        Sort sort = direction.equals(Sort.Direction.ASC.name())?
//                Sort.by(field).ascending():
//                Sort.by(field).descending();
//        Collections.sort(patients, sort);

//        List<Patient> allPat = getAllPatients();
//        List<Patient> res = new ArrayList<>();
//        for(Patient p:res){
//            if(p.getDoctor().getId().equals(id)){
//                res.add(p);
//            }
//        }
        return patients;
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
