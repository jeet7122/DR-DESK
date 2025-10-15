package org.example.hms.tests;

import org.example.hms.dao.MedicalRecordsDAO;
import org.example.hms.models.MedicalRecord;

import java.util.List;

public class MedicalRecordsTest {
    MedicalRecordsDAO dao = new MedicalRecordsDAO();

    public void AddMedicalRecordTest(MedicalRecord medicalRecord){
        dao.insert(medicalRecord);
    }

    public void getAllMedicalRecordsTest(){
        List<MedicalRecord> records = dao.getAll();
        for(MedicalRecord medicalRecord : records){
            System.out.println(medicalRecord);
        }
    }

    public void deleteMedicalRecordsTest(int id){
        dao.delete(id);
    }

    public void updateMedicalRecordsTest(MedicalRecord medicalRecord, int id){
        dao.update(medicalRecord, id);
    }
}
