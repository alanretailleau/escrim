package com.escrim;

import java.io.IOException;
import javafx.fxml.FXML;

public class fxController {

    @FXML
    private void switchToTableau() throws IOException {
        App.setRoot("tableau");
    }

    @FXML
    private void switchToMateriel() throws IOException {
        App.setRoot("materiel");
    }

    @FXML
    private void switchToPatient() throws IOException {
        App.setRoot("recherche");
    }

    @FXML
    private void switchToCommande() throws IOException {
        App.setRoot("colis");
    }

    @FXML
    private void logout() throws IOException {
        App.setRoot("login");
    }
}