package org.example.hms.ui.appointment;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.hms.dao.AppointmentDAO;
import org.example.hms.models.Appointment;
import org.example.hms.ui.utils.AbstractListView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppointmentListView extends AbstractListView<Appointment, AppointmentDAO> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AppointmentListView() {
        super();
    }

    @Override
    protected AppointmentDAO createDAO() {
        return new AppointmentDAO();
    }

    @Override
    protected void setupColumns() {
        TableColumn<Appointment, Integer> appointment_idCol = new TableColumn<>("Appointment ID");
        TableColumn<Appointment, Integer> doctor_idCol = new TableColumn<>("Doctor ID");
        TableColumn<Appointment, Integer> patient_id = new TableColumn<>("Patient ID");
        TableColumn<Appointment, String> statusCol = new TableColumn<>("Status");
        TableColumn<Appointment, String> appointment_dateCol = new TableColumn<>("Appointment date and time");
        TableColumn<Appointment, String >  notesCol = new TableColumn<>("Notes");

        appointment_idCol.setCellValueFactory(a -> new SimpleIntegerProperty(a.getValue().getAppointmentId()).asObject());
        doctor_idCol.setCellValueFactory(a -> new SimpleIntegerProperty(a.getValue().getDoctorId()).asObject());
        patient_id.setCellValueFactory(a -> new SimpleIntegerProperty(a.getValue().getPatientId()).asObject());
        statusCol.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getStatus().toString()));
        notesCol.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getNotes()));
        appointment_dateCol.setCellValueFactory(a -> {
            LocalDateTime dateTime = a.getValue().getAppointmentDate();
            if (dateTime == null) {
                return new SimpleStringProperty("null");
            }
            return new SimpleStringProperty(formatter.format(dateTime));
        });

        table.getColumns().addAll(appointment_idCol,doctor_idCol,patient_id,statusCol,appointment_dateCol, notesCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

    }

    @Override
    protected String getSearchPrompt() {
        return "Search for Appointment";
    }

    @Override
    protected List<Appointment> getPaginatedData(String query, int limit, int offset) {
        return dao.getAppointmentsPaginated(query, limit, offset);
    }

    @Override
    protected int getTotalDataCount(String query) {
        return dao.getTotalCount(query);
    }
}
