package com.escrim;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import org.h2.jdbcx.JdbcDataSource;

import com.escrim.dao.AutreMaterielDAO;
import com.escrim.dao.ColisDAO;
import com.escrim.dao.MaterielDAO;
import com.escrim.dao.MaterielMedicalDAO;
import com.escrim.dao.MedicamentDAO;
import com.escrim.model.AutreMateriel;
import com.escrim.model.Colis;
import com.escrim.model.Materiel;
import com.escrim.model.MaterielMedical;
import com.escrim.model.Medicament;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class colisController {


    @FXML
    private TextField searchField;
    private FilteredList<Colis> filteredColisData;

    @FXML
    private void handleAddMaterialButtonAction() {
        try {
            // Chargez le fichier FXML pour la popup d'ajout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addColisDialog.fxml"));
            VBox page = loader.load();

            // Créez la boîte de dialogue Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter un Colis");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Définissez le matériel dans le contrôleur.
            AddColisDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Affichez la boîte de dialogue et attendez que l'utilisateur la ferme
            dialogStage.showAndWait();

            // Après fermeture de la popup, rafraîchissez la liste du matériel
            loadMaterielData();

        } catch (IOException e) {
            e.printStackTrace();
            // Gérez l'exception ici
        }
    }

    @FXML
    private TableView<Colis> colisTableView;

    @FXML
    private void initialize() {
        TableColumn<Colis, String> nature = new TableColumn<>("Nature");
        nature.setCellValueFactory(new PropertyValueFactory<>("nature"));

        TableColumn<Colis, Float> volume = new TableColumn<>("Volume");
        volume.setCellValueFactory(new PropertyValueFactory<>("volume"));

        TableColumn<Colis, String> designation = new TableColumn<>("Désignation");
        designation.setCellValueFactory(new PropertyValueFactory<>("designation"));

        TableColumn<Colis, String> precision = new TableColumn<>("Précision");
        precision.setCellValueFactory(new PropertyValueFactory<>("precision"));

        TableColumn<Colis, Boolean> checkBoxCol1 = new TableColumn<>("Sélectionner");
        checkBoxCol1.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());

        checkBoxCol1.setCellFactory(tc -> new CheckBoxTableCell<>());

        colisTableView.getColumns().add(checkBoxCol1);
        colisTableView.getColumns().add(nature);
        colisTableView.getColumns().add(designation);
        colisTableView.getColumns().add(precision);
        colisTableView.getColumns().add(volume);

        colisTableView.setRowFactory(tv -> {
            TableRow<Colis> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Colis rowData = row.getItem();
                    handleDoubleClickAction("colis", rowData);
                }
            });
            return row;
        });

        loadMaterielData(); // Méthode pour charger les données dans la TableView
    }

    private void handleDoubleClickAction(String type, Colis colis) {
        // Ici, vous pouvez ouvrir une fenêtre de détails ou effectuer une autre action
        // avec l'objet Materiel qui a été double-cliqué.
        System.out.println("Double click on: " + colis.getDesignation());
    }

    ColisDAO colisDAO;

    private void loadMaterielData() {

        ObservableList<Colis> colisList = FXCollections.observableArrayList();

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:tcp://localhost/~/test");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        // Remplacez cette partie par la récupération des données depuis votre base de
        // données.

        try {
            colisDAO = new ColisDAO(dataSource.getConnection());

            colisList.addAll(colisDAO.findAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        filteredColisData = new FilteredList<>(colisList, p -> true);

        // Ajoutez un listener au TextField de recherche
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredColisData.setPredicate(materiel -> {
                // Si le champ de recherche est vide, affichez tous les matériaux.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Comparez le nom du produit de chaque matériel avec le texte de recherche.
                String lowerCaseFilter = newValue.toLowerCase();

                if (materiel.getDesignation().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Le filtre correspond au nom du produit.
                }
                return false; // Aucune correspondance.
            });
        });

        colisTableView.setItems(filteredColisData);

    }

    public void delete() throws SQLException {

        deleteColisItems();
        loadMaterielData();

    }

    public void deleteColisItems() throws SQLException {
        for (Colis colis : colisTableView.getItems()) {
            if (colis.isSelected()) {
                colisDAO.delete(colis.getIdColis());
            }
        }
    }

    @FXML
    private void handleExportAction() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            saveDataToCsv(file);
        }
    }

    private void saveDataToCsv(File file) {

        try (PrintWriter writer = new PrintWriter(file)) {
            // Écrivez l'en-tête du fichier CSV

            writer.println("id, id Moyen de Transport, Désignation,Côtes, Nature, Volume");

            // Écrivez les données des matériaux
            for (Colis colis : colisTableView.getItems()) {
                String data = String.format("%d,%d,%s,%d,%s,%f",
                        colis.getIdColis(),
                        colis.getIdMoyenTransport(),
                        colis.getDesignation(),
                        colis.getCotes(),
                        colis.getNature(),
                        colis.getVolume());
                writer.println(data);
            }

        } catch (

        Exception e) {
            // Gestion des exceptions
            e.printStackTrace();
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
