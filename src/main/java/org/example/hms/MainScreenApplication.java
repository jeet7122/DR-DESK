package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.models.Billing;
import org.example.hms.tests.BillingTest;


import java.io.IOException;


public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BillingTest test = new BillingTest();
        Billing billing = new Billing();
        billing.setAppointmentId(4);
        billing.setPatientId(8);
        billing.setConsultationFee(29.99);
        billing.setMedicineFee(9.99);
        billing.setServiceFee(1.99);
        billing.setPaid(false);

        test.TestInsert(billing);


    }
}
