package com.escrim;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;

public class rechercheController {

    @FXML
    private TextField queryField;

    @FXML
    private TableView<ObservableList<String>> resultsTable;

    @FXML
    private void handleExecuteQuery() {
        resultsTable.getColumns().clear();
        String query = queryField.getText();

        // Exécutez la requête SQL et traitez les résultats

        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(
                        rs.getMetaData().getColumnName(i + 1));
                column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(j)));
                resultsTable.getColumns().add(column);
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            resultsTable.setItems(data);
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Gérer l'exception
        }
    }

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