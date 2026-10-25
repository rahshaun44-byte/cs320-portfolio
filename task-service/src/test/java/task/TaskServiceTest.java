package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    public void setUp() {
        service = new TaskService();
    }

    @Test
    public void testAddTaskSuccessful() {
        Task task = new Task("T001", "Module 5", "Complete Appointment Service milestone");
        service.addTask(task);
        assertEquals(task, service.getTask("T001"));
    }

    @Test
    public void testAddDuplicateTaskIdThrowsException() {
        Task task1 = new Task("T001", "Task One", "First task description");
        Task task2 = new Task("T001", "Task Two", "Second task description");
        service.addTask(task1);

        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(task2);
        });
    }

    @Test
    public void testAddNullTaskThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(null);
        });
    }

    @Test
    public void testDeleteTaskSuccessful() {
        Task task = new Task("T002", "Study", "Review JUnit test strategies");
        service.addTask(task);
        assertNotNull(service.getTask("T002"));

        service.deleteTask("T002");
        assertNull(service.getTask("T002"));
    }

    @Test
    public void testDeleteNonExistentTaskThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteTask("NONEXISTENT");
        });
    }

    @Test
    public void testDeleteNullTaskIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteTask(null);
        });
    }

    @Test
    public void testUpdateNameSuccessful() {
        Task task = new Task("T003", "Initial", "Valid description");
        service.addTask(task);
        service.updateName("T003", "Updated Name");
        assertEquals("Updated Name", service.getTask("T003").getName());
    }

    @Test
    public void testUpdateDescriptionSuccessful() {
        Task task = new Task("T003", "Initial", "Valid description");
        service.addTask(task);
        service.updateDescription("T003", "Updated description content");
        assertEquals("Updated description content", service.getTask("T003").getDescription());
    }

    @Test
    public void testUpdateNonExistentTaskThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateName("NONEXISTENT", "New Name");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateDescription("NONEXISTENT", "New Description");
        });
    }

    @Test
    public void testUpdateWithNullIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateName(null, "New Name");
        });
    }
}
