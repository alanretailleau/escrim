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
import com.escrim.dao.MaterielDAO;
import com.escrim.dao.MaterielMedicalDAO;
import com.escrim.dao.MedicamentDAO;
import com.escrim.model.AutreMateriel;
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

public class materielController {

    @FXML
    private TabPane materialPane;

    @FXML
    private TextField searchField;
    private FilteredList<Materiel> filteredMaterialData;
    private FilteredList<Medicament> filteredMedicamentData;
    private FilteredList<AutreMateriel> filteredAutreMaterialData;
    private FilteredList<MaterielMedical> filteredMedicalMaterialData;

    @FXML
    private void handleAddMaterialButtonAction() {
        try {
            // Chargez le fichier FXML pour la popup d'ajout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addMaterialDialog.fxml"));
            VBox page = loader.load();

            // Créez la boîte de dialogue Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter un Matériel");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Définissez le matériel dans le contrôleur.
            AddMaterialDialogController controller = loader.getController();
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
    private TableView<Materiel> materielTableView;

    @FXML
    private TableView<MaterielMedical> materielMedicalTableView;

    @FXML
    private TableView<AutreMateriel> autreTableView;

    @FXML
    private TableView<Medicament> medicamentTableView;

    @FXML
    private void initialize() {
        TableColumn<Materiel, String> nomProduitCol1 = new TableColumn<>("Nom du produit");
        nomProduitCol1.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        TableColumn<MaterielMedical, String> nomProduitCol2 = new TableColumn<>("Nom du produit");
        nomProduitCol2.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        TableColumn<Medicament, String> nomProduitCol3 = new TableColumn<>("Nom du produit");
        nomProduitCol3.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        TableColumn<AutreMateriel, String> nomProduitCol4 = new TableColumn<>("Nom du produit");
        nomProduitCol4.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));

        TableColumn<Medicament, Integer> numLotCol1 = new TableColumn<>("Numéro Lot");
        numLotCol1.setCellValueFactory(new PropertyValueFactory<>("numeroLot"));
        TableColumn<MaterielMedical, Integer> numLotCol2 = new TableColumn<>("Numéro Lot");
        numLotCol2.setCellValueFactory(new PropertyValueFactory<>("numeroLot"));

        TableColumn<Materiel, Integer> quantiteCol1 = new TableColumn<>("Quantité");
        quantiteCol1.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        TableColumn<MaterielMedical, Integer> quantiteCol2 = new TableColumn<>("Quantité");
        quantiteCol2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        TableColumn<Medicament, Integer> quantiteCol3 = new TableColumn<>("Quantité");
        quantiteCol3.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        TableColumn<AutreMateriel, Integer> quantiteCol4 = new TableColumn<>("Quantité");
        quantiteCol4.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        TableColumn<Materiel, String> uniteCol1 = new TableColumn<>("Unité");
        uniteCol1.setCellValueFactory(new PropertyValueFactory<>("unite"));
        TableColumn<MaterielMedical, String> uniteCol2 = new TableColumn<>("Unité");
        uniteCol2.setCellValueFactory(new PropertyValueFactory<>("unite"));
        TableColumn<Medicament, String> uniteCol3 = new TableColumn<>("Unité");
        uniteCol3.setCellValueFactory(new PropertyValueFactory<>("unite"));
        TableColumn<AutreMateriel, String> uniteCol4 = new TableColumn<>("Unité");
        uniteCol4.setCellValueFactory(new PropertyValueFactory<>("unite"));

        TableColumn<Medicament, String> dosageCol = new TableColumn<>("Forme dosage");
        dosageCol.setCellValueFactory(new PropertyValueFactory<>("formeDosage"));

        TableColumn<Medicament, String> theraCol = new TableColumn<>("Classe Therapeutique");
        theraCol.setCellValueFactory(new PropertyValueFactory<>("classeTherapeutique"));

        TableColumn<Medicament, String> dciCol = new TableColumn<>("DCI");
        dciCol.setCellValueFactory(new PropertyValueFactory<>("dci"));

        TableColumn<Medicament, LocalDate> dluCol = new TableColumn<>("DLU");
        dluCol.setCellValueFactory(new PropertyValueFactory<>("dlu"));
        TableColumn<MaterielMedical, LocalDate> dluCol1 = new TableColumn<>("DLU");
        dluCol1.setCellValueFactory(new PropertyValueFactory<>("dlu"));

        TableColumn<Materiel, Boolean> checkBoxCol1 = new TableColumn<>("Sélectionner");
        checkBoxCol1.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        TableColumn<MaterielMedical, Boolean> checkBoxCol2 = new TableColumn<>("Sélectionner");
        checkBoxCol2.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        TableColumn<Medicament, Boolean> checkBoxCol3 = new TableColumn<>("Sélectionner");
        checkBoxCol3.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        TableColumn<AutreMateriel, Boolean> checkBoxCol4 = new TableColumn<>("Sélectionner");
        checkBoxCol4.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());

        checkBoxCol1.setCellFactory(tc -> new CheckBoxTableCell<>());
        checkBoxCol2.setCellFactory(tc -> new CheckBoxTableCell<>());
        checkBoxCol3.setCellFactory(tc -> new CheckBoxTableCell<>());
        checkBoxCol4.setCellFactory(tc -> new CheckBoxTableCell<>());

        materielTableView.getColumns().add(checkBoxCol1);
        materielTableView.getColumns().add(nomProduitCol1);
        materielTableView.getColumns().add(quantiteCol1);
        materielTableView.getColumns().add(uniteCol1);

        materielMedicalTableView.getColumns().add(checkBoxCol2);
        materielMedicalTableView.getColumns().add(nomProduitCol2);
        materielMedicalTableView.getColumns().add(quantiteCol2);
        materielMedicalTableView.getColumns().add(uniteCol2);
        materielMedicalTableView.getColumns().add(dluCol1);
        materielMedicalTableView.getColumns().add(numLotCol2);

        medicamentTableView.getColumns().add(checkBoxCol3);
        medicamentTableView.getColumns().add(nomProduitCol3);
        medicamentTableView.getColumns().add(quantiteCol3);
        medicamentTableView.getColumns().add(uniteCol3);
        medicamentTableView.getColumns().add(dluCol);
        medicamentTableView.getColumns().add(numLotCol1);
        medicamentTableView.getColumns().add(dosageCol);
        medicamentTableView.getColumns().add(theraCol);
        medicamentTableView.getColumns().add(dciCol);

        autreTableView.getColumns().add(checkBoxCol4);
        autreTableView.getColumns().add(nomProduitCol4);
        autreTableView.getColumns().add(quantiteCol4);
        autreTableView.getColumns().add(uniteCol4);
        
        materielTableView.setRowFactory(tv -> {
            TableRow<Materiel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Materiel rowData = row.getItem();
                    handleDoubleClickAction("materiel", rowData);
                }
            });
            return row;
        });
        materielMedicalTableView.setRowFactory(tv -> {
            TableRow<MaterielMedical> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    MaterielMedical rowData = row.getItem();
                    handleDoubleClickAction("materiel", rowData);
                }
            });
            return row;
        });
        medicamentTableView.setRowFactory(tv -> {
            TableRow<Medicament> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Medicament rowData = row.getItem();
                    handleDoubleClickAction("materiel", rowData);
                }
            });
            return row;
        });
        autreTableView.setRowFactory(tv -> {
            TableRow<AutreMateriel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    AutreMateriel rowData = row.getItem();
                    handleDoubleClickAction("materiel", rowData);
                }
            });
            return row;
        });

        loadMaterielData(); // Méthode pour charger les données dans la TableView
    }

    private void handleDoubleClickAction(String type, Materiel materiel) {
        // Ici, vous pouvez ouvrir une fenêtre de détails ou effectuer une autre action
        // avec l'objet Materiel qui a été double-cliqué.
        System.out.println("Double click on: " + materiel.getNomProduit());
    }

    MaterielDAO materielDAO;
    MaterielMedicalDAO materielMedicalDAO;
    MedicamentDAO medicamentDAO;
    AutreMaterielDAO autreMaterielDAO;

    private void loadMaterielData() {

        ObservableList<Materiel> materielList = FXCollections.observableArrayList();
        ObservableList<MaterielMedical> materielMedicalList = FXCollections.observableArrayList();
        ObservableList<Medicament> medicamentList = FXCollections.observableArrayList();
        ObservableList<AutreMateriel> autreList = FXCollections.observableArrayList();

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:tcp://localhost/~/test");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        // Remplacez cette partie par la récupération des données depuis votre base de
        // données.

        try {
            materielDAO = new MaterielDAO(dataSource.getConnection());
            materielMedicalDAO = new MaterielMedicalDAO(dataSource.getConnection());
            medicamentDAO = new MedicamentDAO(dataSource.getConnection());
            autreMaterielDAO = new AutreMaterielDAO(dataSource.getConnection());
            materielList.addAll(materielDAO.findAll());
            materielMedicalList.addAll(materielMedicalDAO.findAll());
            medicamentList.addAll(medicamentDAO.findAll());
            autreList.addAll(autreMaterielDAO.findAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        filteredMaterialData = new FilteredList<>(materielList, p -> true);
        filteredMedicalMaterialData = new FilteredList<>(materielMedicalList, p -> true);
        filteredMedicamentData = new FilteredList<>(medicamentList, p -> true);
        filteredAutreMaterialData = new FilteredList<>(autreList, p -> true);

        // Ajoutez un listener au TextField de recherche
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredMaterialData.setPredicate(materiel -> {
                // Si le champ de recherche est vide, affichez tous les matériaux.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Comparez le nom du produit de chaque matériel avec le texte de recherche.
                String lowerCaseFilter = newValue.toLowerCase();

                if (materiel.getNomProduit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Le filtre correspond au nom du produit.
                }
                return false; // Aucune correspondance.
            });
            filteredMedicalMaterialData.setPredicate(materiel -> {
                // Si le champ de recherche est vide, affichez tous les matériaux.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Comparez le nom du produit de chaque matériel avec le texte de recherche.
                String lowerCaseFilter = newValue.toLowerCase();

                if (materiel.getNomProduit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Le filtre correspond au nom du produit.
                }
                return false; // Aucune correspondance.
            });
            filteredMedicamentData.setPredicate(materiel -> {
                // Si le champ de recherche est vide, affichez tous les matériaux.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Comparez le nom du produit de chaque matériel avec le texte de recherche.
                String lowerCaseFilter = newValue.toLowerCase();

                if (materiel.getNomProduit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Le filtre correspond au nom du produit.
                }
                return false; // Aucune correspondance.
            });
            filteredAutreMaterialData.setPredicate(materiel -> {
                // Si le champ de recherche est vide, affichez tous les matériaux.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Comparez le nom du produit de chaque matériel avec le texte de recherche.
                String lowerCaseFilter = newValue.toLowerCase();

                if (materiel.getNomProduit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Le filtre correspond au nom du produit.
                }
                return false; // Aucune correspondance.
            });
        });

        materielTableView.setItems(filteredMaterialData);
        materielMedicalTableView.setItems(filteredMedicalMaterialData);
        medicamentTableView.setItems(filteredMedicamentData);
        autreTableView.setItems(filteredAutreMaterialData);
    }

    public void delete() throws SQLException {
        switch (materialPane.getSelectionModel().getSelectedItem().getId()) {
            case "materiel":
                deleteMaterielItems();
                break;
            case "materielMedical":
                deleteMaterielMedicalItems();
                break;
            case "medicament":
                deleteMedicamentItems();
                break;
            case "autre":
                deleteAutreItems();
                break;

            default:
                break;
        }
        loadMaterielData();
    }

    public void deleteMaterielItems() throws SQLException {
        for (Materiel materiel : materielTableView.getItems()) {
            if (materiel.isSelected()) {
                materielDAO.delete(materiel.getIdMateriel());
            }
        }
    }

    public void deleteMaterielMedicalItems() throws SQLException {
        for (MaterielMedical materiel : materielMedicalTableView.getItems()) {
            System.err.println(materiel.getNomProduit());
            System.err.println(materiel.isSelected());
            if (materiel.isSelected()) {
                materielMedicalDAO.delete(materiel.getIdMaterielMedical());
            }
        }
    }

    public void deleteMedicamentItems() throws SQLException {
        for (Medicament materiel : medicamentTableView.getItems()) {
            if (materiel.isSelected()) {
                medicamentDAO.delete(materiel.getIdMedicament());
            }
        }
    }

    public void deleteAutreItems() throws SQLException {
        for (AutreMateriel materiel : autreTableView.getItems()) {
            if (materiel.isSelected()) {
                autreMaterielDAO.delete(materiel.getIdAutreMateriel());
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
            switch (materialPane.getSelectionModel().getSelectedItem().getId()) {
                case "materiel":
                    writer.println("id, idColis, Nom du produit,Quantité, Unité, Volume");

                    // Écrivez les données des matériaux
                    for (Materiel materiel : materielTableView.getItems()) {
                        String data = String.format("%d,%d,%s,%d,%s,%f",
                                materiel.getIdMateriel(),
                                materiel.getIdColis(),
                                materiel.getNomProduit(),
                                materiel.getQuantite(),
                                materiel.getUnite(),
                                materiel.getVolume());
                        writer.println(data);
                    }
                    break;

                case "materielMedical":
                    writer.println("id, idColis, Nom du produit,Quantité, Unité, Volume");

                    // Écrivez les données des matériaux
                    for (MaterielMedical materiel : materielMedicalTableView.getItems()) {
                        String data = String.format("%d,%d,%s,%d,%s,%f",
                                materiel.getIdMateriel(),
                                materiel.getIdColis(),
                                materiel.getNomProduit(),
                                materiel.getQuantite(),
                                materiel.getUnite(),
                                materiel.getVolume());
                        writer.println(data);
                    }
                    break;
                case "medicament":
                    writer.println("id, idColis, Nom du produit,Quantité, Unité, Volume");

                    // Écrivez les données des matériaux
                    for (Medicament materiel : medicamentTableView.getItems()) {
                        String data = String.format("%d,%d,%s,%d,%s,%f",
                                materiel.getIdMateriel(),
                                materiel.getIdColis(),
                                materiel.getNomProduit(),
                                materiel.getQuantite(),
                                materiel.getUnite(),
                                materiel.getVolume());
                        writer.println(data);
                    }
                    break;

                case "autre":
                    writer.println("id, idColis, Nom du produit,Quantité, Unité, Volume");

                    // Écrivez les données des matériaux
                    for (AutreMateriel materiel : autreTableView.getItems()) {
                        String data = String.format("%d,%d,%s,%d,%s,%f",
                                materiel.getIdMateriel(),
                                materiel.getIdColis(),
                                materiel.getNomProduit(),
                                materiel.getQuantite(),
                                materiel.getUnite(),
                                materiel.getVolume());
                        writer.println(data);
                    }
                    break;

                default:
                    break;

            }
        } catch (Exception e) {
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
