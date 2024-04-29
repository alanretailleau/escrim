package com.escrim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escrim.model.Colis;

public class ColisDAO implements GenericDAO<Colis> {
    private Connection dbConnection;

    public ColisDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void insert(Colis colis) throws SQLException {
        String sql = "INSERT INTO Colis (nature, volume, cotes, designation, precisions, idMoyenTransport) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setString(1, colis.getNature());
            stmt.setFloat(2, colis.getVolume());
            stmt.setFloat(3, colis.getCotes());
            stmt.setString(4, colis.getDesignation());
            stmt.setString(5, colis.getPrecisions());
            stmt.setInt(6, colis.getIdMoyenTransport());
            stmt.executeUpdate();
        }
    }

    @Override
    public Colis findById(int id) throws SQLException {
        String sql = "SELECT * FROM Colis WHERE idColis = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Colis(
                        rs.getString("nature"),
                        rs.getInt("idColis"),
                        rs.getFloat("volume"),
                        rs.getFloat("cotes"),
                        rs.getString("designation"),
                        rs.getString("precisions"),
                        rs.getInt("idMoyenTransport"));
            }
        }
        return null;
    }

    @Override
    public List<Colis> findAll() throws SQLException {
        List<Colis> colisList = new ArrayList<>();
        String sql = "SELECT * FROM Colis";
        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Colis colis = new Colis(
                    rs.getString("nature"),
                    rs.getInt("idColis"),
                    rs.getFloat("volume"),
                    rs.getFloat("cotes"),
                    rs.getString("designation"),
                    rs.getString("precisions"), rs.getInt("idMoyenTransport"));
            colisList.add(colis);
        }

        return colisList;
    }

    @Override
    public boolean update(Colis colis) throws SQLException {
        String sql = "UPDATE Colis SET nature = ?, volume = ?, cotes = ?, designation = ?, precisions = ?, idMoyenTransport = ? WHERE idColis = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setString(1, colis.getNature());
            stmt.setFloat(2, colis.getVolume());
            stmt.setFloat(3, colis.getCotes());
            stmt.setString(4, colis.getDesignation());
            stmt.setString(5, colis.getPrecisions());
            stmt.setInt(6, colis.getIdMoyenTransport());
            stmt.setInt(7, colis.getIdColis());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM Colis WHERE idColis = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
