package org.example.hms.tests;

import org.example.hms.dao.AppointmentDAO;
import org.example.hms.models.Appointment;

import java.util.List;

public class AppointmentTest {
    AppointmentDAO  dao = new AppointmentDAO();
    public void addAppointment(Appointment appointment)
    {
        dao.insert(appointment);
    }
    public void getAllAppointment()
    {
        List<Appointment> appointments = dao.getAll();
        for(Appointment appointment : appointments)
        {
            System.out.println(appointment);
        }
    }
    public void updateAppointment(Appointment appointment, int id)
    {
        dao.update(appointment, id);
    }
    public void deleteAppointment(int id)
    {
        dao.delete(id);
    }
}
