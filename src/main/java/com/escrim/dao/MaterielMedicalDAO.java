package com.escrim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escrim.model.MaterielMedical;

public class MaterielMedicalDAO implements GenericDAO<MaterielMedical> {
    private Connection dbConnection;

    public MaterielMedicalDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void insert(MaterielMedical materielMedical) throws SQLException {
        String sql = "INSERT INTO MaterielMedical (idColis, nomProduit, quantite, unite, volume, dlu, numeroLot) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, materielMedical.getIdMateriel());
            stmt.setString(2, materielMedical.getNomProduit());
            stmt.setInt(3, materielMedical.getQuantite());
            stmt.setString(4, materielMedical.getUnite());
            stmt.setFloat(5, materielMedical.getVolume());
            stmt.setDate(6, java.sql.Date.valueOf(materielMedical.getDlu()));
            stmt.setInt(7, materielMedical.getNumeroLot());
            stmt.executeUpdate();
        }
    }

    @Override
    public MaterielMedical findById(int id) throws SQLException {
        String sql = "SELECT * FROM MaterielMedical WHERE idMaterielMedical = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MaterielMedical(
                    rs.getInt("idMaterielMedical"),
                    rs.getString("nomProduit"),
                    rs.getInt("idColis"),
                    rs.getInt("quantite"),
                    rs.getString("unite"),
                    rs.getFloat("volume"),
                    rs.getDate("dlu").toLocalDate(),
                    rs.getInt("numeroLot"), false);
                
            }
        }
        return null;
    }

    @Override
    public List<MaterielMedical> findAll() throws SQLException {
        List<MaterielMedical> listeMaterielMedical = new ArrayList<>();
        String sql = "SELECT * FROM MaterielMedical";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MaterielMedical materielMedical = new MaterielMedical(
                    rs.getInt("idMaterielMedical"),
                    rs.getString("nomProduit"),
                    rs.getInt("idColis"),
                    rs.getInt("quantite"),
                    rs.getString("unite"),
                    rs.getFloat("volume"),
                    rs.getDate("dlu").toLocalDate(),
                    rs.getInt("numeroLot"), false);
                
                listeMaterielMedical.add(materielMedical);
            }
        }
        return listeMaterielMedical;
    }

    @Override
    public boolean update(MaterielMedical materielMedical) throws SQLException {
        String sql = "UPDATE MaterielMedical SET idColis = ?, nomProduit = ?, quantite = ?, unite = ?, volume = ?, dlu = ?, numeroLot = ? WHERE idMaterielMedical = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, materielMedical.getIdMateriel());
            stmt.setString(2, materielMedical.getNomProduit());
            stmt.setInt(3, materielMedical.getQuantite());
            stmt.setString(4, materielMedical.getUnite());
            stmt.setFloat(5, materielMedical.getVolume());
            stmt.setDate(6, java.sql.Date.valueOf(materielMedical.getDlu()));
            stmt.setInt(7, materielMedical.getNumeroLot());
            stmt.setInt(8, materielMedical.getIdMateriel());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM MaterielMedical WHERE idMaterielMedical = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
