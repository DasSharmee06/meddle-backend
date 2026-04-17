package meddle.controller;

import meddle.entity.Item;
import meddle.entity.User;
import meddle.repository.ItemRepository;
import meddle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/items")
// We allow Flutter (running on localhost or an emulator) to talk to this server
@CrossOrigin(origins = "*") 
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    // GET: Fetch all items
    // URL: http://localhost:8080/api/items
    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // GET: Fetch only lost items or only found items
    // URL: http://localhost:8080/api/items/status/lost
    @GetMapping("/status/{status}")
    public List<Item> getItemsByStatus(@PathVariable String status) {
        return itemRepository.findByStatus(status);
    }

    // POST: Report a new item
    // URL: http://localhost:8080/api/items/report/{reporter_id}
    @PostMapping("/report/{reporterId}")
    public ResponseEntity<?> reportItem(@PathVariable UUID reporterId, @RequestBody Item item) {
        Optional<User> reporter = userRepository.findById(reporterId);
        
        if (reporter.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: User not found!");
        }
        
        // Attach the user to the item (OOP Relationship in action)
        item.setReporter(reporter.get());
        Item savedItem = itemRepository.save(item);
        
        return ResponseEntity.ok(savedItem);
    }
}