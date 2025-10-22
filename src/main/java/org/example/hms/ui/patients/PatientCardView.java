package org.example.hms.ui.patients;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.example.hms.models.Patient;

public class PatientCardView {
    private final Patient patient;
    private final VBox root;
    public PatientCardView(Patient patient) {
        this.patient = patient;
        root = new VBox();
    }
    public Node getView() {
        return root;
    }
}
