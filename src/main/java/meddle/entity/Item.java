package meddle.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty; // Added this import

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id", nullable = false)
    @JsonIgnore // Keeps the full User object out of the JSON
    private User reporter;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(nullable = false)
    private String location;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "hide_contact", nullable = false)
    private boolean hideContact = false;

    @Column(name = "event_time", nullable = false)
    private Instant eventTime;

    @Column(nullable = false)
    private String status = "lost"; 

    @Column(name = "image_path")
    private String imagePath; 

    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();

    // --- NEW: VIRTUAL PROPERTY FOR FLUTTER ---
    // This allows Flutter to see "reporterId" while "reporter" remains hidden
    @JsonProperty("reporterId")
    public UUID getReporterId() {
        return reporter != null ? reporter.getId() : null;
    }

    // --- GETTERS AND SETTERS ---
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public User getReporter() { return reporter; }
    public void setReporter(User reporter) { this.reporter = reporter; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public boolean isHideContact() { return hideContact; }
    public void setHideContact(boolean hideContact) { this.hideContact = hideContact; }

    public Instant getEventTime() { return eventTime; }
    public void setEventTime(Instant eventTime) { this.eventTime = eventTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public Instant getCreatedAt() { return createdAt; }
}