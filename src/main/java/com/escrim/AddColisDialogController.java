package com.escrim;

import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;

import com.escrim.dao.ColisDAO;
import com.escrim.model.Colis;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddColisDialogController {

    @FXML
    private TextField natureField;
    @FXML
    private TextField volumeField;
    @FXML
    private TextField cotesField;
    @FXML
    private TextField designationField;
    @FXML
    private TextField precisionsField;
    @FXML
    private TextField idMoyenTransportField;

    private Stage dialogStage;
    private ColisDAO colisDAO;
    private boolean okClicked = false;

    // Initialise le contrôleur.
    @FXML
    private void initialize() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:tcp://localhost/~/test");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        colisDAO = new ColisDAO(dataSource.getConnection());
    }

    // Appelé quand l'utilisateur clique sur Ajouter.
    @FXML
    private void handleAddColis() {
        try {
            String nature = natureField.getText();
            float volume = Float.parseFloat(volumeField.getText());
            float cotes = Float.parseFloat(cotesField.getText());
            String designation = designationField.getText();
            String precisions = precisionsField.getText();
            int idMoyenTransport = Integer.parseInt(idMoyenTransportField.getText());
            
            // Créez un nouvel objet Colis avec les informations saisies.
            Colis newColis = new Colis();
            newColis.setNature(nature);
            newColis.setVolume(volume);
            newColis.setCotes(cotes);
            newColis.setDesignation(designation);
            newColis.setPrecisions(precisions);
            newColis.setIdMoyenTransport(idMoyenTransport);
            
            colisDAO.insert(newColis);
            okClicked = true;
            dialogStage.close();
        } catch (NumberFormatException e) {
            // Gérer l'exception ici, par exemple en montrant un message d'erreur.
        } catch (SQLException e) {
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
