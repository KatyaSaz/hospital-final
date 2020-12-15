package ua.sazonova.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor find(Doctor doctor) {
        return doctorRepository.getOne(doctor.getId());
    }

    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void create(Doctor doctor){
        save(doctor);
        find(doctor).getUser().setIdMoreInfo(doctor.getId());
    }

    public void delete(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getById(Long id) {
        if (doctorRepository.findById(id).isPresent()) {
            return doctorRepository.findById(id).get();
        }
        return null;
    }

    public List<Doctor> sortedList(String field, String direction) {
        Sort sort = direction.equals(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending():
                Sort.by(field).descending();
        return doctorRepository.findAll(sort);
    }

    public List<Doctor> getByOneType(DoctorType type){
        return  doctorRepository.findByType(type);
    }

//    public List<Doctor> sortByAmountOfPatients(){
//        List<Doctor> doctors = doctorRepository.findAll();
//        List<Doctor> sortedDoctors = Collections.sort(
//                doctors,
//                ((Patient) d1,(Patient)d2)->d1.getPatients().size()-d2.getPatients().size()
//        );
//        Sort sort = Sort.by(patients.size()).ascending();
//        return  doctorRepository.findAll(sort);
//    }
//
//    public int getAmountOfPatients(Doctor doctor){
//        return doctor.getPatients().size();
//    }

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
