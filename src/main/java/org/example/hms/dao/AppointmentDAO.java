package org.example.hms.dao;

import org.example.hms.models.Appointment;

import java.util.List;

public class AppointmentDAO implements GenericDAO<Appointment> {

    @Override
    public void insert(Appointment obj) {

    }

    @Override
    public void update(Appointment obj, int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Appointment> getAll() {
        return List.of();
    }
}
