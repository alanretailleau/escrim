package com.escrim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.escrim.model.Medicament;

public class MedicamentDAO implements GenericDAO<Medicament> {
    private Connection dbConnection;

    public MedicamentDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void insert(Medicament medicament) throws SQLException {
        String sql = "INSERT INTO Medicament (idColis, nomProduit, quantite, unite, volume, dlu, numeroLot, formeDosage, classeTherapeutique, dci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, medicament.getIdMateriel());
            stmt.setString(2, medicament.getNomProduit());
            stmt.setInt(3, medicament.getQuantite());
            stmt.setString(4, medicament.getUnite());
            stmt.setFloat(5, medicament.getVolume());
            stmt.setDate(6, java.sql.Date.valueOf(medicament.getDlu()));
            stmt.setInt(7, medicament.getNumeroLot());
            stmt.setString(8, medicament.getFormeDosage());
            stmt.setString(9, medicament.getClasseTherapeutique());
            stmt.setString(10, medicament.getDci());
            stmt.executeUpdate();
        }
    }

    @Override
    public Medicament findById(int id) throws SQLException {
        String sql = "SELECT * FROM Medicament WHERE idMedicament = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Medicament(
                        rs.getInt("idMedicament"),
                        rs.getString("nomProduit"),
                        rs.getInt("idColis"),
                        rs.getInt("quantite"),
                        rs.getString("unite"),
                        rs.getFloat("volume"),
                        rs.getDate("dlu").toLocalDate(),
                        rs.getString("formeDosage"),
                        rs.getString("classeTherapeutique"),
                        rs.getString("dci"),
                        rs.getInt("numeroLot"), false);
            }
        }
        return null;
    }

    @Override
    public List<Medicament> findAll() throws SQLException {
        List<Medicament> medicaments = new ArrayList<>();
        String sql = "SELECT * FROM Medicament";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Medicament medicament = new Medicament(
                    rs.getInt("idMedicament"),
                    rs.getString("nomProduit"),
                    rs.getInt("idColis"),
                    rs.getInt("quantite"),
                    rs.getString("unite"),
                    rs.getFloat("volume"),
                    rs.getDate("dlu").toLocalDate(),
                    rs.getString("formeDosage"),
                    rs.getString("classeTherapeutique"),
                    rs.getString("dci"),
                    rs.getInt("numeroLot"), false);
                medicaments.add(medicament);
            }
        }
        return medicaments;
    }

    @Override
    public boolean update(Medicament medicament) throws SQLException {
        String sql = "UPDATE Medicament SET idColis = ?, nomProduit = ?, quantite = ?, unite = ?, volume = ?, dlu = ?, numeroLot = ?, formeDosage = ?, classeTherapeutique = ?, dci = ? WHERE idMedicament = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, medicament.getIdMateriel());
            stmt.setString(2, medicament.getNomProduit());
            stmt.setInt(3, medicament.getQuantite());
            stmt.setString(4, medicament.getUnite());
            stmt.setFloat(5, medicament.getVolume());
            stmt.setDate(6, java.sql.Date.valueOf(medicament.getDlu()));
            stmt.setInt(7, medicament.getNumeroLot());
            stmt.setString(8, medicament.getFormeDosage());
            stmt.setString(9, medicament.getClasseTherapeutique());
            stmt.setString(10, medicament.getDci());
            stmt.setInt(11, medicament.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM Medicament WHERE idMedicament = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
