package ua.sazonova.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sazonova.hospital.entity.CardRecord;

public interface CardRecordRepository extends JpaRepository<CardRecord, Long> {
}
