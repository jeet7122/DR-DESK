package org.example.hms.tests;

import org.example.hms.dao.PatientDAO;


public class Patients {
    public static void updatePatient (String firstName, String lastName, String email, int patient_id)
    {
        boolean updated = PatientDAO.updatePatient(firstName, lastName, email, patient_id);
        if (updated)
        {
            System.out.println("All Test Passed");
        }
        else
        {
            System.out.println("Update test failed " + patient_id);
        }
    }
}
