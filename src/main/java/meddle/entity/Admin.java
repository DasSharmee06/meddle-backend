package meddle.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

    // Admins don't need extra columns based on our schema, 
    // they just inherit the base User properties like name and email.

    // --- POLYMORPHISM ---
    // Admins manage the system; they do not get gamification points.
    @Override
    public int calculateRewardMultiplier() {
        return 0;
    }
}