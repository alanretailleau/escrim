package com.escrim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escrim.model.Materiel;

public class MaterielDAO implements GenericDAO<Materiel> {
    private Connection dbConnection;

    public MaterielDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public MaterielDAO() {
    }

    @Override
    public void insert(Materiel materiel) throws SQLException {
        String sql = "INSERT INTO Materiel (idColis, enMission, nomProduit, quantite, unite, volume) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, materiel.getIdColis());
            stmt.setBoolean(2, materiel.isEnMission());
            stmt.setString(3, materiel.getNomProduit());
            stmt.setInt(4, materiel.getQuantite());
            stmt.setString(5, materiel.getUnite());
            stmt.setFloat(6, materiel.getVolume());
            stmt.executeUpdate();
        }
    }

    /*
     * @Override
     * public Materiel findById(int id) throws SQLException {
     * String sql = "SELECT * FROM Materiel WHERE idMateriel = ?";
     * try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
     * stmt.setInt(1, id);
     * ResultSet rs = stmt.executeQuery();
     * if (rs.next()) {
     * return new Materiel(
     * rs.getInt("idMateriel"),
     * rs.getInt("idColis"),
     * rs.getBoolean("enMission"),
     * rs.getString("nomProduit"),
     * rs.getInt("quantite"),
     * rs.getString("unite"),
     * rs.getFloat("volume")
     * );
     * }
     * }
     * return null;
     * }
     * 
     * @Override
     * public List<Materiel> findAll() throws SQLException {
     * List<Materiel> materiels = new ArrayList<>();
     * String sql = "SELECT * FROM Materiel";
     * try (PreparedStatement stmt = dbConnection.prepareStatement(sql);
     * ResultSet rs = stmt.executeQuery()) {
     * while (rs.next()) {
     * Materiel materiel = new Materiel(
     * rs.getInt("idMateriel"),
     * rs.getInt("idColis"),
     * rs.getBoolean("enMission"),
     * rs.getString("nomProduit"),
     * rs.getInt("quantite"),
     * rs.getString("unite"),
     * rs.getFloat("volume")
     * );
     * materiels.add(materiel);
     * }
     * }
     * return materiels;
     * }
     */
    @Override
    public boolean update(Materiel materiel) throws SQLException {
        String sql = "UPDATE Materiel SET idColis = ?, enMission = ?, nomProduit = ?, quantite = ?, unite = ?, volume = ? WHERE idMateriel = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, materiel.getIdColis());
            stmt.setBoolean(2, materiel.isEnMission());
            stmt.setString(3, materiel.getNomProduit());
            stmt.setInt(4, materiel.getQuantite());
            stmt.setString(5, materiel.getUnite());
            stmt.setFloat(6, materiel.getVolume());
            stmt.setInt(7, materiel.getIdMateriel());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM Materiel WHERE idMateriel = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public Materiel findById(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Materiel> findAll() throws SQLException {
        List<Materiel> materiels = new ArrayList<>();
        String sql = "SELECT * FROM Materiel";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Materiel materiel = new Materiel(
                        rs.getInt("idMateriel"),
                        rs.getInt("idColis"),
                        rs.getBoolean("enMission"),
                        rs.getString("nomProduit"),
                        rs.getInt("quantite"),
                        rs.getString("unite"),
                        rs.getFloat("volume"));
                materiels.add(materiel);
            }
        }
        return materiels;
    }
}
