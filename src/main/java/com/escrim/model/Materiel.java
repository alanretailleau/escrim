package com.escrim.model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;

public class Materiel {

	private int idMateriel;
	private String nomProduit;
	private int idColis;
	protected boolean enMission;
	private int quantite;
	private String unite;
	private float volume;

	public Materiel(int idMateriel, int idColis, boolean enMission, String nomProduit, int quantite, String unite,
			float volume) {
		this.idMateriel = idMateriel;
		this.idColis = idColis;
		this.enMission = enMission;
		this.nomProduit = nomProduit;
		this.quantite = quantite;
		this.unite = unite;
		this.volume = volume;
	}

	private final BooleanProperty selected = new SimpleBooleanProperty(false);

	// Méthodes d'accès pour la propriété selected
	public boolean isSelected() {
		return selected.get();
	}

	public void setSelected(boolean selected) {
		this.selected.set(selected);
	}

	public BooleanProperty selectedProperty() {
		return selected;
	}

	public Materiel() {
		// TODO Auto-generated constructor stub
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public boolean isEnMission() {
		return enMission;
	}

	public int getIdMateriel() {
		return idMateriel;
	}

	public int getIdColis() {
		return idColis;
	}

	public int getQuantite() {
		return quantite;
	}

	public String getUnite() {
		return unite;
	}

	public float getVolume() {
		return volume;
	}

	// Setters
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public void setIdColis(int idColis) {
		this.idColis = idColis;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}
}
