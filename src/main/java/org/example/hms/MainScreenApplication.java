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
        medicalRecord.setPatientId(8);
        medicalRecord.setCondition("Thyroid");
        medicalRecord.setDescription("has a little to higher than normal");
        medicalRecord.setCurrentStatus("Normal");
        MedicalRecordsTest.AddMedicalRecordTest(medicalRecord);

        MedicalRecord recordToUpdate = new MedicalRecord();
        recordToUpdate.setPatientId(6);
        recordToUpdate.setCondition("Cold and Cough");
        recordToUpdate.setCurrentStatus("Severe");
        recordToUpdate.setDescription("has a little bit of cold and more soar throat");
        MedicalRecordsTest.updateMedicalRecordsTest(recordToUpdate, 1);



        MedicalRecordsTest.getAllMedicalRecordsTest();

    }
}
