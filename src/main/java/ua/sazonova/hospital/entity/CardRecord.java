package ua.sazonova.hospital.entity;

import lombok.*;
import ua.sazonova.hospital.entity.enam.RecordType;


import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "card_records")
public class CardRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name="record_type")
    private RecordType recordType;
    @Column(name="description")
    private String description;
    @ManyToOne
    @JoinColumn(name= "pat_id", nullable=false)
    private Patient patient;
}
