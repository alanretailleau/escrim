package com.escrim.model;

import java.util.Objects;

public class MoyenTransport {
	
	private int idTransport;
	private String nom;
	private float chargeMax;
	private float volumeUtilisable;
	private int longueurPisteMin;
	private int porteeCharge;
	private int rayonAction;
	private int vitesseDeCroisiere;
	private float consommationCarburant;
	private String positionPalettes;
	
	public MoyenTransport(int idTransport, String nom, float chargeMax, float volumeUtilisable, int longueurPiste, int porteeCharge, int rayonAction, int vitesseDeCroisiere, float consommationCarburant, String positionPalettes){
		this.nom = nom;
		this.idTransport = idTransport;
		this.chargeMax = chargeMax;
		this.volumeUtilisable = volumeUtilisable;
		this.longueurPisteMin = longueurPiste;
		this.porteeCharge = porteeCharge;
		this.rayonAction = rayonAction;
		this.vitesseDeCroisiere = vitesseDeCroisiere;
		this.consommationCarburant = consommationCarburant;
		this.positionPalettes = positionPalettes;
	}
	
	public MoyenTransport(int idTransport, String nom, float chargeMax, float volumeUtilisable, int longueurPiste,  int porteeCharge, int rayonAction, int vitesseDeCroisiere, float consommationCarburant){
		this.nom = nom;
		this.idTransport = idTransport;
		this.chargeMax = chargeMax;
		this.volumeUtilisable = volumeUtilisable;
		this.longueurPisteMin = longueurPiste;
		this.porteeCharge = porteeCharge;
		this.rayonAction = rayonAction;
		this.vitesseDeCroisiere = vitesseDeCroisiere;
		this.consommationCarburant = consommationCarburant;
		this.positionPalettes = null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoyenTransport other = (MoyenTransport) obj;
		return Float.floatToIntBits(chargeMax) == Float.floatToIntBits(other.chargeMax)
				&& Float.floatToIntBits(consommationCarburant) == Float.floatToIntBits(other.consommationCarburant)
				&& longueurPisteMin == other.longueurPisteMin && Objects.equals(nom, other.nom)
				&& porteeCharge == other.porteeCharge && Objects.equals(positionPalettes, other.positionPalettes)
				&& rayonAction == other.rayonAction && vitesseDeCroisiere == other.vitesseDeCroisiere
				&& Float.floatToIntBits(volumeUtilisable) == Float.floatToIntBits(other.volumeUtilisable);
	}
	
	public String getNom() {
		return nom;
	}
	public float getChargeMax() {
		return chargeMax;
	}
	public int getId() {
		return idTransport;
	}
	public float getVolumeUtilisable() {
		return volumeUtilisable;
	}
	public int getLongueurPisteMin() {
		return longueurPisteMin;
	}
	public int getPorteeCharge() {
		return porteeCharge;
	}
	public int getRayonAction() {
		return rayonAction;
	}
	public int getVitesseDeCroisiere() {
		return vitesseDeCroisiere;
	}
	public float getConsommationCarburant() {
		return consommationCarburant;
	}
	public String getPositionPalettes() {
		return positionPalettes;
	}

}
