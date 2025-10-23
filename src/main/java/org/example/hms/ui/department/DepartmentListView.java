package org.example.hms.ui.department;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import org.example.hms.dao.DepartmentDAO;
import org.example.hms.models.Department;
import org.example.hms.ui.utils.AbstractListView;


import java.util.List;

public class DepartmentListView extends AbstractListView<Department, DepartmentDAO> {

    public DepartmentListView(){
        super();
    }

    @Override
    protected DepartmentDAO createDAO() {
        return new DepartmentDAO();
    }

    @Override
    protected void setupColumns() {
        TableColumn<Department, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(depart -> new SimpleIntegerProperty(depart.getValue().getDepartmentId()).asObject());

        TableColumn<Department, String> nameCol = new TableColumn<>("Department Name");
        nameCol.setCellValueFactory(depart -> new SimpleStringProperty(depart.getValue().getDepartmentName()));

        TableColumn<Department, String> descriptionCol = new TableColumn<>("Department Description");
        descriptionCol.setCellValueFactory(depart -> new SimpleStringProperty(depart.getValue().getDepartmentDescription()));

        TableColumn<Department, String> location = new TableColumn<>("Department Location");
        location.setCellValueFactory(depart -> new SimpleStringProperty(depart.getValue().getLocation()));

        table.getColumns().addAll(idCol, nameCol, descriptionCol, location);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No Departments found"));

    }

    @Override
    protected String getSearchPrompt() {
        return "Search Departments";
    }

    @Override
    protected List<Department> getPaginatedData(String query, int limit, int offset) {
        return dao.getDepartmentsPaginated(query, limit, offset);
    }

    @Override
    protected int getTotalDataCount(String query) {
        return dao.getTotalCount(query);
    }

}
