package ua.sazonova.hospital.entity;

import lombok.*;
import ua.sazonova.hospital.entity.enam.DoctorType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private DoctorType type;
    private int experience;
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patients;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


}
