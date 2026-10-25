package appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {

    private AppointmentService service;
    private Date futureDate;

    @BeforeEach
    public void setUp() {
        service = new AppointmentService();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 2);
        futureDate = cal.getTime();
    }

    @Test
    public void testAddAppointmentSuccessful() {
        Appointment apt = new Appointment("APT01", futureDate, "Routine Checkup");
        service.addAppointment(apt);
        assertEquals(apt, service.getAppointment("APT01"));
    }

    @Test
    public void testAddDuplicateAppointmentIdThrowsException() {
        Appointment apt1 = new Appointment("APT01", futureDate, "First Appointment");
        Appointment apt2 = new Appointment("APT01", futureDate, "Duplicate Appointment");
        service.addAppointment(apt1);

        assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment(apt2);
        });
    }

    @Test
    public void testAddNullAppointmentThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment(null);
        });
    }

    @Test
    public void testDeleteAppointmentSuccessful() {
        Appointment apt = new Appointment("APT02", futureDate, "Eye Exam");
        service.addAppointment(apt);
        assertNotNull(service.getAppointment("APT02"));

        service.deleteAppointment("APT02");
        assertNull(service.getAppointment("APT02"));
    }

    @Test
    public void testDeleteNonExistentAppointmentThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteAppointment("NONEXISTENT");
        });
    }

    @Test
    public void testDeleteNullAppointmentIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteAppointment(null);
        });
    }
}
