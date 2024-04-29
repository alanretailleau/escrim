package com.escrim;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private void handleConnectButton() throws IOException {
        String usernameT = username.getText();
        String passwordT = password.getText();

        // Connexion à la base de données
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", usernameT, passwordT);
            // Connexion réussie
            System.out.println("Connexion réussie !");
            App.setRoot("materiel");
            // Vous pouvez maintenant utiliser la connexion pour exécuter des requêtes SQL
            // N'oubliez pas de fermer la connexion lorsque vous avez terminé : connection.close();
        } catch (SQLException e) {
            // Erreur de connexion
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }
}
