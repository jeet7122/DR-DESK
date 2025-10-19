package org.example.hms;

import org.example.hms.dto.AppointmentDTO;
import org.example.hms.models.Appointment;
import org.example.hms.models.Status;

public class Mappers {

    public Appointment mapFromDTO(AppointmentDTO obj) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(obj.getAppointmentId());
        appointment.setPatientId(obj.getPatientId());
        appointment.setDoctorId(obj.getDoctorId());
        appointment.setNotes(obj.getNotes());
        appointment.setStatus(Status.valueOf(obj.getStatus()));
        return appointment;
    }
}
