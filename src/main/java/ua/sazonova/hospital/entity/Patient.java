package ua.sazonova.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ua.sazonova.hospital.entity.enam.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patients")
public class Patient{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name="year")
    private int year;
    private String phone;
    @ManyToOne
    @JoinColumn(name="doc_id", nullable=false)
    private Doctor doctor;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    private List<CardRecord> records;


}
