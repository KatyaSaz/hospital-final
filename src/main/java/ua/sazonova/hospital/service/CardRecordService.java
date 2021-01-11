package ua.sazonova.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.repository.CardRecordRepository;

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

    public CardRecord getByID(Long id) {
        return cardRecordRepository.getOne(id);
    }
}
