package ua.sazonova.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sazonova.hospital.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

//    @Override
//    Optional<User> findById(Long aLong);
}
