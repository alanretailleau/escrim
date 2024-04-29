package com.escrim;

import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;

import com.escrim.dao.MaterielDAO;
import com.escrim.model.Materiel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMaterialDialogController {

    @FXML
    private TextField nomProduitField;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField colisField;
    @FXML
    private TextField uniteField;
    @FXML
    private TextField volumeField;

    private Stage dialogStage;
    private MaterielDAO materielDAO;
    private boolean okClicked = false;

    // Constructeur vide.
    public AddMaterialDialogController() {
    }

    // Initialise le contrôleur.
    @FXML
    private void initialize() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL("jdbc:h2:tcp://localhost/~/test");
		dataSource.setUser("sa");
		dataSource.setPassword("");
        materielDAO = new MaterielDAO(dataSource.getConnection()); // Assume MaterielDAO has a no-arg constructor
    }

    // Appelé quand l'utilisateur clique sur Ajouter.
    @FXML
    private void handleAddMaterial() {
        try {
            String nomProduit = nomProduitField.getText();
            int quantite = Integer.parseInt(quantiteField.getText());
            int idColis = Integer.parseInt(colisField.getText());
            String unite = uniteField.getText();
            float volume = Float.parseFloat(volumeField.getText());
            
            // Créez un nouvel objet Materiel avec les informations saisies.
            Materiel newMateriel = new Materiel();
            newMateriel.setNomProduit(nomProduit);
            newMateriel.setQuantite(quantite);
            newMateriel.setIdColis(idColis);
            newMateriel.setUnite(unite);
            newMateriel.setVolume(volume);
            
            materielDAO.insert(newMateriel);
            okClicked = true;
            dialogStage.close();
        } catch (NumberFormatException e) {
            // Gérer l'exception ici, par exemple en montrant un message d'erreur.
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Appelé quand l'utilisateur clique sur Annuler.
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    // Définit la scène pour cette boîte de dialogue.
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // Retourne vrai si l'utilisateur clique sur Ajouter, faux autrement.
    public boolean isOkClicked() {
        return okClicked;
    }
}
