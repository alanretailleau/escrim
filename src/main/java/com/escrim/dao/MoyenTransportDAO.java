package com.escrim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escrim.model.MoyenTransport;

public class MoyenTransportDAO implements GenericDAO<MoyenTransport> {
    private Connection dbConnection;

    public MoyenTransportDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void insert(MoyenTransport moyenTransport) throws SQLException {
        String sql = "INSERT INTO MoyenTransport (nom, chargeMax, volumeUtilisable, longueurPisteMin, porteeCharge, rayonAction, vitesseCroisiere, consommationCarburant, positionPalettes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setString(1, moyenTransport.getNom());
            stmt.setFloat(2, moyenTransport.getChargeMax());
            stmt.setFloat(3, moyenTransport.getVolumeUtilisable());
            stmt.setInt(4, moyenTransport.getLongueurPisteMin());
            stmt.setInt(5, moyenTransport.getPorteeCharge());
            stmt.setInt(6, moyenTransport.getRayonAction());
            stmt.setInt(7, moyenTransport.getVitesseDeCroisiere());
            stmt.setFloat(8, moyenTransport.getConsommationCarburant());
            stmt.setString(9, moyenTransport.getPositionPalettes());
            stmt.executeUpdate();
        }
    }

    @Override
    public MoyenTransport findById(int id) throws SQLException {
        String sql = "SELECT * FROM MoyenTransport WHERE idMoyenTransport = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MoyenTransport(
                    rs.getInt("idMoyenTransport"),
                    rs.getString("nom"),
                    rs.getFloat("chargeMax"),
                    rs.getFloat("volumeUtilisable"),
                    rs.getInt("longueurPisteMin"),
                    rs.getInt("porteeCharge"),
                    rs.getInt("rayonAction"),
                    rs.getInt("vitesseCroisiere"),
                    rs.getFloat("consommationCarburant"),
                    rs.getString("positionPalettes")
                );
            }
        }
        return null;
    }

    @Override
    public List<MoyenTransport> findAll() throws SQLException {
        List<MoyenTransport> moyensTransport = new ArrayList<>();
        String sql = "SELECT * FROM MoyenTransport";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MoyenTransport moyenTransport = new MoyenTransport(
                    rs.getInt("idMoyenTransport"),
                    rs.getString("nom"),
                    rs.getFloat("chargeMax"),
                    rs.getFloat("volumeUtilisable"),
                    rs.getInt("longueurPisteMin"),
                    rs.getInt("porteeCharge"),
                    rs.getInt("rayonAction"),
                    rs.getInt("vitesseCroisiere"),
                    rs.getFloat("consommationCarburant"),
                    rs.getString("positionPalettes")
                );
                moyensTransport.add(moyenTransport);
            }
        }
        return moyensTransport;
    }

    @Override
    public boolean update(MoyenTransport moyenTransport) throws SQLException {
        String sql = "UPDATE MoyenTransport SET nom = ?, chargeMax = ?, volumeUtilisable = ?, longueurPisteMin = ?, porteeCharge = ?, rayonAction = ?, vitesseCroisiere = ?, consommationCarburant = ?, positionPalettes = ? WHERE idMoyenTransport = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setString(1, moyenTransport.getNom());
            stmt.setFloat(2, moyenTransport.getChargeMax());
            stmt.setFloat(3, moyenTransport.getVolumeUtilisable());
            stmt.setInt(4, moyenTransport.getLongueurPisteMin());
            stmt.setInt(5, moyenTransport.getPorteeCharge());
            stmt.setInt(6, moyenTransport.getRayonAction());
            stmt.setInt(7, moyenTransport.getVitesseDeCroisiere());
            stmt.setFloat(8, moyenTransport.getConsommationCarburant());
            stmt.setString(9, moyenTransport.getPositionPalettes());
            stmt.setInt(10, moyenTransport.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM MoyenTransport WHERE idMoyenTransport = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
