package ua.sazonova.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.repository.CardRecordRepository;
import ua.sazonova.hospital.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardRecordService {

    private CardRecordRepository cardRecordRepository;

    @Autowired
    public CardRecordService(CardRecordRepository cardRecordRepository) {
        this.cardRecordRepository = cardRecordRepository;
    }

    public void save(CardRecord cardRecord){
        cardRecordRepository.save(cardRecord);
    }

    public void delete(CardRecord cardRecord){
        cardRecordRepository.delete(cardRecord);
    }

    public List<CardRecord> getAllRecords(){
        return cardRecordRepository.findAll();
    }

    public List<CardRecord> getRecordsOfOnePatient(Long id){
        List<CardRecord> allRec = getAllRecords();
        List<CardRecord> result = new ArrayList<>();
        for(CardRecord cr:allRec){
            if(cr.getPatient().getId().equals(id)){
                result.add(cr);
            }
        }
        return result;
    }




//    public CardRecord createRecord(CardRecord cardRecord, Long patientId) {
//        cardRecord
//        Doctor newDoc = new Doctor();
//        newDoc.setName(doctor.getName());
//        newDoc.setSurname(doctor.getSurname());
//        newDoc.setType(doctor.getType());
//        newDoc.setExperience(doctor.getExperience());
//        newDoc.setPatients(patients);
//        return doctorRepository.save(newDoc);
//    }



}
