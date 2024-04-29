package com.escrim.model;

import java.util.ArrayList;
import java.util.List;

public class ListeMoyenTransport {
	
	private List<MoyenTransport> lesTransports;
	private static ListeMoyenTransport instance;
	
	/**
	 *Constructeur (privé). 
	 */
	private ListeMoyenTransport() {
		this.lesTransports = new ArrayList<MoyenTransport>();
	}

	public static ListeMoyenTransport getInstance() 
	{
		if(instance == null) 
		{
			instance = new ListeMoyenTransport();
		}
		return instance;
	}
	
	/**
	   * Retrouve si un MoyenTransport appartient à lesTransports à partir de son nom, et renvoie l'indice dans lesTransports auquel il se trouve.
	   * Si le MoyenTransport n'appartient pas à lesTransports, renvoie -1. 
	   * 
	   * @param nom 		Le nom du MoyenTransport à retrouver
	   * @return int
	   */
	public int findMoyenTransport(String nom) {
		for (int i = 0; i < lesTransports.size(); i++)
		{
			if (lesTransports.get(i).getNom().equals(nom))
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	   * Ajoute un MoyenTransport à lesTransports si son nom n'apparaît pas déjà dedans. 
	   * 
	   * @param nom 						Le nom du MoyenTransport à ajouter
	   * @param chargeMax 					La chargeMax du MoyenTransport à ajouter
	   * @param volumeUtilisable 			Le volumeUtilisable du MoyenTransport à ajouter
	   * @param longueurPiste 				La longueurPiste du MoyenTransport à ajouter
	   * @param porteeCharge 				La porteeCharge du MoyenTransport à ajouter
	   * @param rayonAction 				Le rayonAction du MoyenTransport à ajouter
	   * @param vitesseDeCroisiere 			La vitesseDeCroisiere du MoyenTransport à ajouter
	   * @param consommationCarburant 		La consommationCarburant du MoyenTransport à ajouter
	   * @param positionPalettes 			La positionPalettes du MoyenTransport à ajouter
	   * @return void
	   */
	public void addMoyenTransport(int idTransport, String nom, float chargeMax, float volumeUtilisable, int longueurPiste,  int porteeCharge, int rayonAction, int vitesseDeCroisiere, float consommationCarburant, String positionPalettes) {
		if (findMoyenTransport(nom) == -1)
		{
			MoyenTransport moyenTransport = new MoyenTransport(idTransport, nom, chargeMax, volumeUtilisable, longueurPiste, porteeCharge, rayonAction, vitesseDeCroisiere, consommationCarburant, positionPalettes);
			this.lesTransports.add(moyenTransport);
		}
	}
	
	/**
	   * Ajoute un MoyenTransport à lesTransports si son nom n'apparaît pas déjà dedans. La positionPalettes est initialisée à null.
	   * 
	   * @param nom 						Le nom du MoyenTransport à ajouter
	   * @param chargeMax 					La chargeMax du MoyenTransport à ajouter
	   * @param volumeUtilisable 			Le volumeUtilisable du MoyenTransport à ajouter
	   * @param longueurPiste 				La longueurPiste du MoyenTransport à ajouter
	   * @param porteeCharge 				La porteeCharge du MoyenTransport à ajouter
	   * @param rayonAction 				Le rayonAction du MoyenTransport à ajouter
	   * @param vitesseDeCroisiere 			La vitesseDeCroisiere du MoyenTransport à ajouter
	   * @param consommationCarburant 		La consommationCarburant du MoyenTransport à ajouter
	   * @return void
	   */
	public void addMoyenTransport(int idTransport, String nom, float chargeMax, float volumeUtilisable, int longueurPiste,  int porteeCharge, int rayonAction, int vitesseDeCroisiere, float consommationCarburant) {
		if (findMoyenTransport(nom) == -1)
		{
			MoyenTransport moyenTransport = new MoyenTransport(idTransport, nom, chargeMax, volumeUtilisable, longueurPiste, porteeCharge, rayonAction, vitesseDeCroisiere, consommationCarburant);
			this.lesTransports.add(moyenTransport);
		}
	}
	
	/**
	   * Supprime un MoyenTransport de lesTransports si son nom apparaît dedans. 
	   * 
	   * @param nom 						Le nom du MoyenTransport à supprimer
	   * @return void
	   */
	public void deleteMoyenTransport(String nom) {
		int indice = findMoyenTransport(nom);
		if (indice != -1) {
			lesTransports.remove(indice);
			}
	}

	/**
	   * Met à jour la chargeMax d'un MoyenTransport de lesTransports si son nom apparaît dedans. 
	   * 
	   * @param nom 			Le nom du MoyenTransport à mettre à jour
	   * @param chargeMax		La chargeMax du MoyenTransport à modifier
	   * @return void
	   */
	public void updateChargeMaxTransport(String nom, float chargeMax) {
		int indice = findMoyenTransport(nom);
		if (indice != -1)
		{
			MoyenTransport ancienMoyenTransport = lesTransports.get(indice);
			float volumeUtilisable = ancienMoyenTransport.getVolumeUtilisable();
			int longueurPiste = ancienMoyenTransport.getLongueurPisteMin();
			int porteeCharge = ancienMoyenTransport.getPorteeCharge();
			int rayonAction = ancienMoyenTransport.getRayonAction();
			int vitesseDeCroisiere = ancienMoyenTransport.getVitesseDeCroisiere();
			int idTransport = ancienMoyenTransport.getId();
			float consommationCarburant = ancienMoyenTransport.getConsommationCarburant();
			String positionPalettes = ancienMoyenTransport.getPositionPalettes();
			lesTransports.remove(indice);
			MoyenTransport moyenTransport = new MoyenTransport(idTransport, nom, chargeMax, volumeUtilisable, longueurPiste, porteeCharge, rayonAction, vitesseDeCroisiere, consommationCarburant, positionPalettes);
			this.lesTransports.add(moyenTransport);
		}
		
	}
	
	/**
	   * Met à jour le volumeUtilisable d'un MoyenTransport de lesTransports si son nom apparaît dedans. 
	   * 
	   * @param nom 					Le nom du MoyenTransport à mettre à jour
	   * @param volumeUtilisable		Le volumeUtilisable du MoyenTransport à modifier
	   * @return void
	   */
	public void updateVolumeUtilisableTransport(String nom, float volumeUtilisable) {
		int indice = findMoyenTransport(nom);
		if (indice != -1)
		{
			MoyenTransport ancienMoyenTransport = lesTransports.get(indice);
			float chargeMax = ancienMoyenTransport.getChargeMax();
			int longueurPiste = ancienMoyenTransport.getLongueurPisteMin();
			int porteeCharge = ancienMoyenTransport.getPorteeCharge();
			int rayonAction = ancienMoyenTransport.getRayonAction();
			int vitesseDeCroisiere = ancienMoyenTransport.getVitesseDeCroisiere();
			int idTransport = ancienMoyenTransport.getId();
			float consommationCarburant = ancienMoyenTransport.getConsommationCarburant();
			String positionPalettes = ancienMoyenTransport.getPositionPalettes();
			lesTransports.remove(indice);
			MoyenTransport moyenTransport = new MoyenTransport(idTransport, nom, chargeMax, volumeUtilisable, longueurPiste, porteeCharge, rayonAction, vitesseDeCroisiere, consommationCarburant, positionPalettes);
			this.lesTransports.add(moyenTransport);
		}
		
	}
	
	/**
	   * Met à jour la longueurPiste d'un MoyenTransport de lesTransports si son nom apparaît dedans. 
	   * 
	   * @param nom 				Le nom du MoyenTransport à mettre à jour
	   * @param longueurPiste		La longueurPiste du MoyenTransport à modifier
	   * @return void
	   */
	public void updateLongueurPisteTransport(String nom, int longueurPiste) {
		int indice = findMoyenTransport(nom);
		if (indice != -1)
		{
			MoyenTransport ancienMoyenTransport = lesTransports.get(indice);
			float volumeUtilisable = ancienMoyenTransport.getVolumeUtilisable();
			float chargeMax = ancienMoyenTransport.getChargeMax();
			int porteeCharge = ancienMoyenTransport.getPorteeCharge();
			int rayonAction = ancienMoyenTransport.getRayonAction();
			int vitesseDeCroisiere = ancienMoyenTransport.getVitesseDeCroisiere();
			int idTransport = ancienMoyenTransport.getId();
			float consommationCarburant = ancienMoyenTransport.getConsommationCarburant();
			String positionPalettes = ancienMoyenTransport.getPositionPalettes();
			lesTransports.remove(indice);
			MoyenTransport moyenTransport = new MoyenTransport(idTransport, nom, chargeMax, volumeUtilisable, longueurPiste, porteeCharge, rayonAction, vitesseDeCroisiere, consommationCarburant, positionPalettes);
			this.lesTransports.add(moyenTransport);
		}
		
	}
	
	/**
	   * Met à jour la porteeCharge d'un MoyenTransport de lesTransports si son nom apparaît dedans. 
	   * 
	   * @param nom 			Le nom du MoyenTransport à mettre à jour
	   * @param porteeCharge	La porteeCharge du MoyenTransport à modifier
	   * @return void
	   */
	public void updatePorteeChargeTransport(String nom, int porteeCharge) {
		int indice = findMoyenTransport(nom);
		if (indice != -1)
		{
			MoyenTransport ancienMoyenTransport = lesTransports.get(indice);
			float volumeUtilisable = ancienMoyenTransport.getVolumeUtilisable();
			int longueurPiste = ancienMoyenTransport.getLongueurPisteMin();
			float chargeMax = ancienMoyenTransport.getChargeMax();
			int rayonAction = ancienMoyenTransport.getRayonAction();
			int vitesseDeCroisiere = ancienMoyenTransport.getVitesseDeCroisiere();
			int idTransport = ancienMoyenTransport.getId();
			float consommationCarburant = ancienMoyenTransport.getConsommationCarburant();
			String positionPalettes = ancienMoyenTransport.getPositionPalettes();
			lesTransports.remove(indice);
			MoyenTransport moyenTransport = new MoyenTransport(idTransport, nom, chargeMax, volumeUtilisable, longueurPiste, porteeCharge, rayonAction, vitesseDeCroisiere, consommationCarburant, positionPalettes);
			this.lesTransports.add(moyenTransport);
		}
		
	}
	
	/**
	   * Met à jour le rayonAction d'un MoyenTransport de lesTransports si son nom apparaît dedans. 
	   * 
	   * @param nom 			Le nom du MoyenTransport à mettre à jour
	   * @param rayonAction		Le rayonAction du MoyenTransport à modifier
	   * @return void
	   */
	public void updateRayonActionTransport(String nom, int rayonAction) {
		int indice = findMoyenTransport(nom);
		if (indice != -1)
		{
			MoyenTransport ancienMoyenTransport = lesTransports.get(indice);
			float volumeUtilisable = ancienMoyenTransport.getVolumeUtilisable();
			int longueurPiste = ancienMoyenTransport.getLongueurPisteMin();
			int porteeCharge = ancienMoyenTransport.getPorteeCharge();
			float chargeMax = ancienMoyenTransport.getChargeMax();
			int vitesseDeCroisiere = ancienMoyenTransport.getVitesseDeCroisiere();
			int idTransport = ancienMoyenTransport.getId();
			float consommationCarburant = ancienMoyenTransport.getConsommationCarburant();
			String positionPalettes = ancienMoyenTransport.getPositionPalettes();
			lesTransports.remove(indice);
			MoyenTransport moyenTransport = new MoyenTransport(idTransport, nom, chargeMax, volumeUtilisable, longueurPiste, porteeCharge, rayonAction, vitesseDeCroisiere, consommationCarburant, positionPalettes);
			this.lesTransports.add(moyenTransport);
		}
		
	}
	
	/**
	   * Met à jour la vitesseDeCroisiere d'un MoyenTransport de lesTransports si son nom apparaît dedans. 
	   * 
	   * @param nom 					Le nom du MoyenTransport à mettre à jour
	   * @param vitesseDeCroisiere		La vitesseDeCroisiere du MoyenTransport à modifier
	   * @return void
	   */
	public void updateVitesseDeCroisiereTransport(String nom, int vitesseDeCroisiere) {
		int indice = findMoyenTransport(nom);
		if (indice != -1)
		{
			MoyenTransport ancienMoyenTransport = lesTransports.get(indice);
			float volumeUtilisable = ancienMoyenTransport.getVolumeUtilisable();
			int longueurPiste = ancienMoyenTransport.getLongueurPisteMin();
			int porteeCharge = ancienMoyenTransport.getPorteeCharge();
			float chargeMax = ancienMoyenTransport.getChargeMax();
			int idTransport = ancienMoyenTransport.getId();
			int rayonAction = ancienMoyenTransport.getRayonAction();
			float consommationCarburant = ancienMoyenTransport.getConsommationCarburant();
			String positionPalettes = ancienMoyenTransport.getPositionPalettes();
			lesTransports.remove(indice);
			MoyenTransport moyenTransport = new MoyenTransport(idTransport, nom, chargeMax, volumeUtilisable, longueurPiste, porteeCharge, rayonAction, vitesseDeCroisiere, consommationCarburant, positionPalettes);
			this.lesTransports.add(moyenTransport);
		}
		
	}
	
	/**
	   * Met à jour la consommationCarburant d'un MoyenTransport de lesTransports si son nom apparaît dedans. 
	   * 
	   * @param nom 						Le nom du MoyenTransport à mettre à jour
	   * @param consommationCarburant		La consommationCarburant du MoyenTransport à modifier
	   * @return void
	   */
	public void updateConsommationCarburantTransport(String nom, float consommationCarburant) {
		int indice = findMoyenTransport(nom);
		if (indice != -1)
		{
			MoyenTransport ancienMoyenTransport = lesTransports.get(indice);
			float volumeUtilisable = ancienMoyenTransport.getVolumeUtilisable();
			int longueurPiste = ancienMoyenTransport.getLongueurPisteMin();
			int porteeCharge = ancienMoyenTransport.getPorteeCharge();
			float chargeMax = ancienMoyenTransport.getChargeMax();
			int idTransport = ancienMoyenTransport.getId();
			int vitesseDeCroisiere = ancienMoyenTransport.getVitesseDeCroisiere();
			int rayonAction = ancienMoyenTransport.getRayonAction();
			String positionPalettes = ancienMoyenTransport.getPositionPalettes();
			lesTransports.remove(indice);
			MoyenTransport moyenTransport = new MoyenTransport(idTransport, nom, chargeMax, volumeUtilisable, longueurPiste, porteeCharge, rayonAction, vitesseDeCroisiere, consommationCarburant, positionPalettes);
			this.lesTransports.add(moyenTransport);
		}
		
	}
	
	/**
	   * Met à jour la positionPalettes d'un MoyenTransport de lesTransports si son nom apparaît dedans. 
	   * 
	   * @param nom 					Le nom du MoyenTransport à mettre à jour
	   * @param positionPalettes		La positionPalettes du MoyenTransport à modifier
	   * @return void
	   */
	public void updatePositionPalettesTransport(String nom, String positionPalettes) {
		int indice = findMoyenTransport(nom);
		if (indice != -1)
		{
			MoyenTransport ancienMoyenTransport = lesTransports.get(indice);
			float volumeUtilisable = ancienMoyenTransport.getVolumeUtilisable();
			int longueurPiste = ancienMoyenTransport.getLongueurPisteMin();
			int porteeCharge = ancienMoyenTransport.getPorteeCharge();
			int idTransport = ancienMoyenTransport.getId();
			float chargeMax = ancienMoyenTransport.getChargeMax();
			int vitesseDeCroisiere = ancienMoyenTransport.getVitesseDeCroisiere();
			float consommationCarburant = ancienMoyenTransport.getConsommationCarburant();
			int rayonAction = ancienMoyenTransport.getRayonAction();
			lesTransports.remove(indice);
			MoyenTransport moyenTransport = new MoyenTransport(idTransport, nom, chargeMax, volumeUtilisable, longueurPiste, porteeCharge, rayonAction, vitesseDeCroisiere, consommationCarburant, positionPalettes);
			this.lesTransports.add(moyenTransport);
		}
		
	}
}
