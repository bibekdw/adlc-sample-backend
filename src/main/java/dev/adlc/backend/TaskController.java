package dev.adlc.backend;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    public enum Priority { LOW, MEDIUM, HIGH }
    public enum Status { PENDING, COMPLETED }
    public record Task(long id, String title, Priority priority, Status status) {}
    public record NewTask(@NotBlank @Size(max=200) String title, Priority priority) {}

    private final AtomicLong nextId = new AtomicLong(0);
    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();

    @GetMapping("/healthz")
    public Map<String,String> health() { return Map.of("status", "ok"); }

    @GetMapping("/api/tasks")
    public List<Task> all(@RequestParam(required = false) Status status) {
        return tasks.values().stream()
            .filter(t -> status == null || t.status() == status)
            .sorted((a,b) -> Long.compare(a.id(), b.id())).toList();
    }

    @PostMapping("/api/tasks")
    public ResponseEntity<Task> create(@Valid @RequestBody NewTask request) {
        long id = nextId.incrementAndGet();
        Task task = new Task(id, request.title(), request.priority() == null ? Priority.MEDIUM : request.priority(), Status.PENDING);
        tasks.put(id, task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PostMapping("/api/tasks/{id}/complete")
    public ResponseEntity<Task> complete(@PathVariable long id) {
        Task updated = tasks.computeIfPresent(id, (key, old) -> new Task(old.id(), old.title(), old.priority(), Status.COMPLETED));
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }
}
