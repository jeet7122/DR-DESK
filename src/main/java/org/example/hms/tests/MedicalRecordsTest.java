package org.example.hms.tests;

import org.example.hms.dao.MedicalRecordsDAO;
import org.example.hms.models.MedicalRecord;

import java.util.List;

public class MedicalRecordsTest {

    public static void AddMedicalRecordTest(MedicalRecord medicalRecord){
        MedicalRecordsDAO.AddMedicalRecord(medicalRecord);
    }

    public static void getAllMedicalRecordsTest(){
        List<MedicalRecord> records =  MedicalRecordsDAO.getMedicalRecords();
        for(MedicalRecord medicalRecord : records){
            System.out.println(medicalRecord);
        }
    }
}
