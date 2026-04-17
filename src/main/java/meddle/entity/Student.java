package meddle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("student") // This tells Supabase to put 'student' in the role column
public class Student extends User {

    // Students have specific data that Faculty and Admins don't
    @Column(name = "prn_number", unique = true)
    private String prnNumber;

    @Column(name = "department")
    private String department;

    // --- POLYMORPHISM ---
    // We override the abstract method from the User class.
    // Students get a 10x point multiplier when they find an item.
    @Override
    public int calculateRewardMultiplier() {
        return 10;
    }

    // Getters and Setters
    public String getPrnNumber() { return prnNumber; }
    public void setPrnNumber(String prnNumber) { this.prnNumber = prnNumber; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}