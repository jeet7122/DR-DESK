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
        test.TestGetAll();
    }
}
