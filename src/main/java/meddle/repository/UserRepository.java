package meddle.repository;

import meddle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    // Spring Boot reads this method name and automatically writes the SQL: 
    // SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);
    

}