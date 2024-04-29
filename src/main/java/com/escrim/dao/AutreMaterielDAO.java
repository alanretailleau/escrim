package com.escrim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escrim.model.AutreMateriel;

public class AutreMaterielDAO implements GenericDAO<AutreMateriel> {
    private Connection dbConnection;

    public AutreMaterielDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void insert(AutreMateriel autreMateriel) throws SQLException {
        String sql = "INSERT INTO AutreMateriel (nomProduit, idColis, quantite, unite, volume) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setString(1, autreMateriel.getNomProduit());
            stmt.setInt(2, autreMateriel.getIdColis());
            stmt.setInt(3, autreMateriel.getQuantite());
            stmt.setString(4, autreMateriel.getUnite());
            stmt.setFloat(5, autreMateriel.getVolume());
            stmt.executeUpdate();
        }
    }

    @Override
    public AutreMateriel findById(int id) throws SQLException {
        String sql = "SELECT * FROM AutreMateriel WHERE idAutreMateriel = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new AutreMateriel(
                        rs.getInt("idAutreMateriel"),
                        rs.getString("nomProduit"),
                        rs.getInt("idColis"),
                        rs.getInt("quantite"),
                        rs.getString("unite"),
                        rs.getFloat("volume"),false);
            }
        }
        return null;
    }

    @Override
    public List<AutreMateriel> findAll() throws SQLException {
        List<AutreMateriel> listeMateriel = new ArrayList<>();
        String sql = "SELECT * FROM AutreMateriel";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AutreMateriel autreMateriel = new AutreMateriel(
                        rs.getInt("idAutreMateriel"),
                        rs.getString("nomProduit"),
                        rs.getInt("idColis"),
                        rs.getInt("quantite"),
                        rs.getString("unite"),
                        rs.getFloat("volume"),false);
                listeMateriel.add(autreMateriel);
            }
        }
        return listeMateriel;
    }

    @Override
    public boolean update(AutreMateriel autreMateriel) throws SQLException {
        String sql = "UPDATE AutreMateriel SET nomProduit = ?, idColis = ?, quantite = ?, unite = ?, volume = ? WHERE idAutreMateriel = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setString(1, autreMateriel.getNomProduit());
            stmt.setInt(2, autreMateriel.getIdColis());
            stmt.setInt(3, autreMateriel.getQuantite());
            stmt.setString(4, autreMateriel.getUnite());
            stmt.setFloat(5, autreMateriel.getVolume());
            stmt.setInt(6, autreMateriel.getIdAutreMateriel()); // Assuming getId() is implemented to return
                                                                // idAutreMateriel
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM AutreMateriel WHERE idAutreMateriel = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
