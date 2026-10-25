package appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    private Date futureDate;
    private Date pastDate;

    @BeforeEach
    public void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        futureDate = cal.getTime();

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        pastDate = cal.getTime();
    }

    @Test
    public void testValidAppointmentCreation() {
        Appointment apt = new Appointment("1234567890", futureDate, "Annual Dental Checkup");
        assertEquals("1234567890", apt.getAppointmentId());
        assertEquals(futureDate, apt.getAppointmentDate());
        assertEquals("Annual Dental Checkup", apt.getDescription());
    }

    @Test
    public void testAppointmentIdNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, futureDate, "Valid Description");
        });
    }

    @Test
    public void testAppointmentIdTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", futureDate, "Valid Description");
        });
    }

    @Test
    public void testAppointmentDateNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", null, "Valid Description");
        });
    }

    @Test
    public void testAppointmentDateInPastThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", pastDate, "Valid Description");
        });
    }

    @Test
    public void testDescriptionNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", futureDate, null);
        });
    }

    @Test
    public void testDescriptionTooLongThrowsException() {
        String longDesc = "This description is definitely longer than fifty characters in length!";
        assertTrue(longDesc.length() > 50);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", futureDate, longDesc);
        });
    }

    @Test
    public void testSetAppointmentDateValid() {
        Appointment apt = new Appointment("12345", futureDate, "Valid Description");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 10);
        Date newFutureDate = cal.getTime();

        apt.setAppointmentDate(newFutureDate);
        assertEquals(newFutureDate, apt.getAppointmentDate());
    }

    @Test
    public void testSetAppointmentDateNullThrowsException() {
        Appointment apt = new Appointment("12345", futureDate, "Valid Description");
        assertThrows(IllegalArgumentException.class, () -> {
            apt.setAppointmentDate(null);
        });
    }

    @Test
    public void testSetAppointmentDateInPastThrowsException() {
        Appointment apt = new Appointment("12345", futureDate, "Valid Description");
        assertThrows(IllegalArgumentException.class, () -> {
            apt.setAppointmentDate(pastDate);
        });
    }

    @Test
    public void testSetDescriptionValid() {
        Appointment apt = new Appointment("12345", futureDate, "Initial Description");
        apt.setDescription("Updated Description");
        assertEquals("Updated Description", apt.getDescription());
    }

    @Test
    public void testSetDescriptionNullThrowsException() {
        Appointment apt = new Appointment("12345", futureDate, "Initial Description");
        assertThrows(IllegalArgumentException.class, () -> {
            apt.setDescription(null);
        });
    }

    @Test
    public void testSetDescriptionTooLongThrowsException() {
        Appointment apt = new Appointment("12345", futureDate, "Initial Description");
        String longDesc = "This is a very long description that exceeds fifty characters limit.";
        assertTrue(longDesc.length() > 50);
        assertThrows(IllegalArgumentException.class, () -> {
            apt.setDescription(longDesc);
        });
    }
}
