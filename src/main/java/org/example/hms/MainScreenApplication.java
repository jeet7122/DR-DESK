package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.models.MedicalRecord;
import org.example.hms.tests.MedicalRecordsTest;


import java.io.IOException;

public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatientId(6);
        medicalRecord.setCondition("Cold and Cough");
        medicalRecord.setDescription("has a little bit of cold and soar throat");
        medicalRecord.setCurrentStatus("Normal");
        MedicalRecordsTest.AddMedicalRecordTest(medicalRecord);
        MedicalRecordsTest.getAllMedicalRecordsTest();
    }
}
