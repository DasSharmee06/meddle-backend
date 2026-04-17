package meddle.repository;

import meddle.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    
    // Magically writes: SELECT * FROM items WHERE status = ?
    // This allows Flutter to instantly ask for "just the lost items" or "just the found items"
    List<Item> findByStatus(String status);
    
    // Magically writes: SELECT * FROM items WHERE reporter_id = ?
    // This lets a student see a history of all the items they personally reported
    List<Item> findByReporterId(UUID reporterId);
}