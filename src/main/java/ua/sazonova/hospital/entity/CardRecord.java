package ua.sazonova.hospital.entity;

import lombok.*;
import org.springframework.http.HttpHeaders;
import ua.sazonova.hospital.entity.enam.RecordType;
import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public String diagnoseToString(){
        return "Patient name: "+ patient.getName()+" "+ patient.getSurname()+"\n"
                +"Doctor name: "+ patient.getDoctor().getName()+" "+ patient.getDoctor().getSurname()+"\n"
                +recordType+": "+ description;
    }

    public String getFileName(){
        return "diagnose_"+patient.getSurname()+".txt";
    }


}
