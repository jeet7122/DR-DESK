package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.dto.AppointmentDTO;
import org.example.hms.models.Appointment;
import org.example.hms.models.Billing;
import org.example.hms.models.Status;
import org.example.hms.tests.AppointmentTest;
import org.example.hms.tests.BillingTest;


import java.io.IOException;
import java.time.LocalDateTime;


public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        AppointmentTest test = new AppointmentTest();
        Mappers mappers = new Mappers();
        appointmentDTO.setAppointmentDate(LocalDateTime.now().plusDays(2) );
        appointmentDTO.setNotes("Update Test");
        appointmentDTO.setDoctorId(1);
        appointmentDTO.setPatientId(8);
        appointmentDTO.setStatus(String.valueOf(Status.SCHEDULED));

        Appointment appointment = mappers.mapFromDTO(appointmentDTO);
        test.updateAppointment(appointment, 4);

    }
}
