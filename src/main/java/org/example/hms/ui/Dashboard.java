package org.example.hms.ui;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import org.example.hms.ui.appointment.AddAppointmentView;
import org.example.hms.ui.appointment.AppointmentListView;
import org.example.hms.ui.department.AddDepartmentForm;
import org.example.hms.ui.department.DepartmentListView;
import org.example.hms.ui.department.UpdateDepartmentForm;
import org.example.hms.ui.doctors.DeleteDoctorFormView;
import org.example.hms.ui.doctors.DoctorFormView;
import org.example.hms.ui.doctors.DoctorListView;
import org.example.hms.ui.doctors.DoctorUpdateFormView;
import org.example.hms.ui.patients.AddPatientView;
import org.example.hms.ui.patients.ListPatientsView;
import org.example.hms.ui.patients.UpdatePatientView;

public class Dashboard {
    private final BorderPane root;
    public Dashboard() {
        root = new BorderPane();
        MenuBar navbar = new MenuBar();
        Menu doctors = new Menu("Doctors");
        MenuItem viewDoctors = new MenuItem("View Doctors");
        MenuItem addDoctor = new MenuItem("Add Doctor");
        MenuItem deleteDoctor = new MenuItem("Delete Doctor");
        MenuItem updateDoctor = new MenuItem("Update Doctor");

        //Menu Actions for Doctors
        doctors.setOnAction(e -> root.setCenter(new DoctorListView().getView()));
        addDoctor.setOnAction(e -> root.setCenter(new DoctorFormView().getView()));
        updateDoctor.setOnAction(e -> root.setCenter(new DoctorUpdateFormView().getView()));
        deleteDoctor.setOnAction(e -> root.setCenter(new DeleteDoctorFormView().getView()));

        doctors.getItems().addAll(viewDoctors, addDoctor, deleteDoctor, updateDoctor);

        Menu Appointments = new Menu("Appointments");
        MenuItem viewAllAppointments = new MenuItem("View Appointments");
        MenuItem addAppointment = new MenuItem("Add Appointment");
        MenuItem deleteAppointment = new MenuItem("Delete Appointment");
        MenuItem updateAppointment = new MenuItem("Update Appointment");
        Appointments.getItems().addAll(viewAllAppointments, addAppointment, deleteAppointment, updateAppointment);

        //Menu actions of Appointments
        viewAllAppointments.setOnAction(e -> root.setCenter(new AppointmentListView().getView()));
        addAppointment.setOnAction(e -> root.setCenter(new AddAppointmentView().getView()));

        Menu Patients = new Menu("Patients");
        MenuItem viewPatients = new MenuItem("View Patients");
        MenuItem addPatient = new MenuItem("Add Patient");
        MenuItem deletePatient = new MenuItem("Delete Patient");
        MenuItem updatePatient = new MenuItem("Update Patient");
        Patients.getItems().addAll(viewPatients, addPatient, deletePatient, updatePatient);

        //----------------Menu actions of Patients-------------------------
        viewPatients.setOnAction(e -> root.setCenter(new ListPatientsView().getView()));
        addPatient.setOnAction(e -> root.setCenter(new AddPatientView().getView()));
        updatePatient.setOnAction(e -> root.setCenter(new UpdatePatientView().getView()));

        Menu Departments = new Menu("Departments");
        MenuItem viewDepartments = new MenuItem("View Departments");
        MenuItem addDepartment = new MenuItem("Add Department");
        MenuItem deleteDepartment = new MenuItem("Delete Department");
        MenuItem updateDepartment = new MenuItem("Update Department");
        Departments.getItems().addAll(viewDepartments, addDepartment, deleteDepartment, updateDepartment);

        addDepartment.setOnAction(e -> root.setCenter(new AddDepartmentForm().getView()));
        updateDepartment.setOnAction(e -> root.setCenter(new UpdateDepartmentForm().getView()));
        viewDepartments.setOnAction(e -> root.setCenter(new DepartmentListView().getView()));

        Menu Bills = new Menu("Bills");
        MenuItem viewBills = new MenuItem("View Bills");
        MenuItem addBill = new MenuItem("Add Bill");
        MenuItem deleteBill = new MenuItem("Delete Bill");
        MenuItem updateBill = new MenuItem("Update Bill");
        Bills.getItems().addAll(viewBills, addBill, deleteBill, updateBill);

        navbar.getMenus().addAll(doctors, Patients, Appointments, Departments, Bills);
        root.setTop(navbar);





        root.setCenter(new DoctorListView().getView());
    }
    public Parent getView(){
        return root;
    }

}
