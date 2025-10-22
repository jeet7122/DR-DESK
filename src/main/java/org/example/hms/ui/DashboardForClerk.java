package org.example.hms.ui;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import org.example.hms.ui.doctors.DoctorFormView;
import org.example.hms.ui.doctors.DoctorListView;
import org.example.hms.ui.patients.AddPatientView;
import org.example.hms.ui.patients.ListPatientsView;
import org.example.hms.ui.patients.UpdatePatientView;

public class DashboardForClerk {
    private final BorderPane root;
    public DashboardForClerk() {
        root = new BorderPane();
        MenuBar navbar = new MenuBar();
        Menu doctors = new Menu("Doctors");
        MenuItem viewDoctors = new MenuItem("View Doctors");
        MenuItem addDoctor = new MenuItem("Add Doctor");
        MenuItem updateDoctor = new MenuItem("Update Doctor");
        //Menu Actions for Doctors
        doctors.setOnAction(e -> root.setCenter(new DoctorListView().getView()));
        addDoctor.setOnAction(e -> root.setCenter(new DoctorFormView().getView()));
        updateDoctor.setOnAction(e -> root.setCenter(new UpdatePatientView().getView()));


        doctors.getItems().addAll(viewDoctors, addDoctor, updateDoctor);

        Menu Appointments = new Menu("Appointments");
        MenuItem viewAllAppointments = new MenuItem("View Appointments");
        MenuItem addAppointment = new MenuItem("Add Appointment");
        MenuItem updateAppointment = new MenuItem("Update Appointment");
        Appointments.getItems().addAll(viewAllAppointments, addAppointment, updateAppointment);

        //Menu actions of Appointments


        Menu Patients = new Menu("Patients");
        MenuItem viewPatients = new MenuItem("View Patients");
        MenuItem addPatient = new MenuItem("Add Patient");
        MenuItem updatePatient = new MenuItem("Update Patient");
        Patients.getItems().addAll(viewPatients, addPatient, updatePatient);

        //----------------Menu actions of Patients-------------------------
        viewPatients.setOnAction(e -> root.setCenter(new ListPatientsView().getView()));
        addPatient.setOnAction(e -> root.setCenter(new AddPatientView().getView()));
        updatePatient.setOnAction(e -> root.setCenter(new UpdatePatientView().getView()));

        Menu Departments = new Menu("Departments");
        MenuItem viewDepartments = new MenuItem("View Departments");
        MenuItem addDepartment = new MenuItem("Add Department");
        MenuItem updateDepartment = new MenuItem("Update Department");
        Departments.getItems().addAll(viewDepartments, addDepartment, updateDepartment);

        Menu Bills = new Menu("Bills");
        MenuItem viewBills = new MenuItem("View Bills");
        MenuItem addBill = new MenuItem("Add Bill");
        Bills.getItems().addAll(viewBills, addBill);

        navbar.getMenus().addAll(doctors, Patients, Appointments, Departments, Bills);
        root.setTop(navbar);





        root.setCenter(new DoctorListView().getView());
    }
    public Parent getView(){
        return root;
    }

}
