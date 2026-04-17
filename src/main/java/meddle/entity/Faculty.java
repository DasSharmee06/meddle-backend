package meddle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("faculty")
public class Faculty extends User {

    // Faculty have a department, but no PRN number
    @Column(name = "department")
    private String department;

    // --- POLYMORPHISM ---
    // Faculty get a smaller point multiplier (maybe just for leaderboard fun)
    @Override
    public int calculateRewardMultiplier() {
        return 2;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}