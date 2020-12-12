package ua.sazonova.hospital.entity;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import ua.sazonova.hospital.entity.enam.Role;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name="id_more_info")
    private int idMoreInfo;

}
