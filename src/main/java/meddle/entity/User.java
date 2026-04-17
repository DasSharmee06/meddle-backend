package meddle.entity;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;

// --- ABSTRACTION & INHERITANCE SETUP ---
// We tell Java this class maps to the "users" table.
// We use Single Table strategy so Student, Faculty, and Admin all live in one table.
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    // --- ENCAPSULATION ---
    // All variables are private. They can only be accessed via getters/setters.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "role", insertable = false, updatable = false)
    private String role; // Read-only from the database side to prevent accidental overwrites

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "reward_score")
    private int rewardScore = 0;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();

    // --- POLYMORPHISM PREPARATION ---
    // This abstract method forces all child classes (Student, Faculty) to implement 
    // their own specific way of calculating gamification points.
    public abstract int calculateRewardMultiplier();

    // --- ENCAPSULATION GETTERS & SETTERS ---
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getRole() { return role; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getRewardScore() { return rewardScore; }
    public void setRewardScore(int rewardScore) { this.rewardScore = rewardScore; }
    
    // Custom method to easily add points
    public void addPoints(int points) {
        this.rewardScore += points;
    }
}