package appointment;

import java.util.Date;

public class Appointment {
    private final String appointmentId;
    private Date appointmentDate;
    private String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Invalid appointment ID: cannot be null and must be 10 characters or less.");
        }
        
        validateAppointmentDate(appointmentDate);
        validateDescription(description);

        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        validateAppointmentDate(appointmentDate);
        this.appointmentDate = appointmentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    private void validateAppointmentDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Appointment date cannot be null.");
        }
        if (date.before(new Date())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
        }
    }

    private void validateDescription(String desc) {
        if (desc == null) {
            throw new IllegalArgumentException("Appointment description cannot be null.");
        }
        if (desc.length() > 50) {
            throw new IllegalArgumentException("Appointment description cannot exceed 50 characters.");
        }
    }
}
