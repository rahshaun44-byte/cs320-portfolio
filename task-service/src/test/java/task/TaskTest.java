package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testValidTaskCreation() {
        Task task = new Task("T12345", "Complete Project", "Finish Module 5 and Project One deliverables");
        assertEquals("T12345", task.getTaskId());
        assertEquals("Complete Project", task.getName());
        assertEquals("Finish Module 5 and Project One deliverables", task.getDescription());
    }

    @Test
    public void testTaskIdNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Valid Name", "Valid Description");
        });
    }

    @Test
    public void testTaskIdTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", "Valid Name", "Valid Description");
        });
    }

    @Test
    public void testNameNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T12345", null, "Valid Description");
        });
    }

    @Test
    public void testNameTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T12345", "This Task Name Is Far Too Long For Limit", "Valid Description");
        });
    }

    @Test
    public void testDescriptionNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T12345", "Valid Name", null);
        });
    }

    @Test
    public void testDescriptionTooLongThrowsException() {
        String longDesc = "This description exceeds fifty characters in total length for test!";
        assertTrue(longDesc.length() > 50);
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("T12345", "Valid Name", longDesc);
        });
    }

    @Test
    public void testSetNameValid() {
        Task task = new Task("T12345", "Initial Name", "Initial Description");
        task.setName("Updated Name");
        assertEquals("Updated Name", task.getName());
    }

    @Test
    public void testSetNameNullThrowsException() {
        Task task = new Task("T12345", "Initial Name", "Initial Description");
        assertThrows(IllegalArgumentException.class, () -> {
            task.setName(null);
        });
    }

    @Test
    public void testSetNameTooLongThrowsException() {
        Task task = new Task("T12345", "Initial Name", "Initial Description");
        assertThrows(IllegalArgumentException.class, () -> {
            task.setName("This Task Name Exceeds The Twenty Char Limit");
        });
    }

    @Test
    public void testSetDescriptionValid() {
        Task task = new Task("T12345", "Initial Name", "Initial Description");
        task.setDescription("Updated valid description under fifty chars");
        assertEquals("Updated valid description under fifty chars", task.getDescription());
    }

    @Test
    public void testSetDescriptionNullThrowsException() {
        Task task = new Task("T12345", "Initial Name", "Initial Description");
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(null);
        });
    }

    @Test
    public void testSetDescriptionTooLongThrowsException() {
        Task task = new Task("T12345", "Initial Name", "Initial Description");
        String longDesc = "This description exceeds fifty characters in total length for test!";
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(longDesc);
        });
    }
}
