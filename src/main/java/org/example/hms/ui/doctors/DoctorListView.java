package org.example.hms.ui.doctors;

import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.hms.dao.DoctorDAO;
import org.example.hms.models.Doctor;
import org.example.hms.ui.utils.AbstractListView;

import java.time.LocalDate;
import java.util.List;

public class DoctorListView extends AbstractListView<Doctor, DoctorDAO> {

    public DoctorListView() {
        super();
     }

    @Override
    protected DoctorDAO createDAO() {
        return new DoctorDAO();
    }

    // -------------------- TABLE SETUP --------------------
    @Override
    protected void setupColumns() {
        TableColumn<Doctor, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());

        TableColumn<Doctor, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFirstName()));

        TableColumn<Doctor, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLastName()));

        TableColumn<Doctor, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEmail()));

        TableColumn<Doctor, String> specCol = new TableColumn<>("Specialization");
        specCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSpecialization()));

        TableColumn<Doctor, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getContactNumber()));

        TableColumn<Doctor, LocalDate> joinDateCol = new TableColumn<>("Join Date");
        joinDateCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getJoiningDate()));

        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, emailCol, specCol, contactCol, joinDateCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No doctors found"));
        table.setRowFactory(tv -> {
            TableRow<Doctor> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    Doctor doctor = row.getItem();
                    Node view = new DoctorCardView(doctor).getView();
                    root.setCenter(view);
                    root.setTop(null);
                }
            });
            return row;
        });
    }

    @Override
    protected String getSearchPrompt() {
        return "Search for doctors";
    }

    @Override
    protected List<Doctor> getPaginatedData(String query, int limit, int offset) {
        return dao.getDoctorsPaginated(query, limit, offset);
    }

    @Override
    protected int getTotalDataCount(String query) {
        return dao.getTotalCount(query);
    }
}
