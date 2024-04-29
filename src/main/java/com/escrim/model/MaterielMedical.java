package com.escrim.model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class MaterielMedical extends Materiel{
	
	private int idMaterielMedical;
	private String nomProduit;
	private int idColis; 
	private int quantite;
	private String unite;
	private float volume;
	private LocalDate dlu;
	private int numeroLot;
	
	public MaterielMedical(int idMaterielMedical, String nomProduit, int idColis, int quantite, String unite, float volume, LocalDate dlu, int numeroLot, boolean enMission) {
		super(numeroLot, idColis, enMission, nomProduit, quantite, unite, volume);
		this.nomProduit = nomProduit;
		this.idColis = idColis;
		this.quantite = quantite;
		this.idMaterielMedical = idMaterielMedical;
		this.unite = unite;
		this.volume = volume;
		this.dlu = dlu;
		this.numeroLot = numeroLot;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterielMedical other = (MaterielMedical) obj;
		return Objects.equals(dlu, other.dlu) && idColis == other.idColis
				&& Objects.equals(nomProduit, other.nomProduit) && numeroLot == other.numeroLot
				&& quantite == other.quantite && Objects.equals(unite, other.unite)
				&& Float.floatToIntBits(volume) == Float.floatToIntBits(other.volume);
	}

	public String getNomProduit() {
		return nomProduit;
	}
	
	public int getIdColis() {
		return idColis;
	}
	
	public int getQuantite() {
		return quantite;
	}

	public int getIdMaterielMedical() {
		return idMaterielMedical;
	}
	
	public String getUnite() {
		return unite;
	}
	
	public float getVolume() {
		return volume;
	}
	
	
	public LocalDate getDlu() {
		return dlu;
	}
	
	public int getNumeroLot() {
		return numeroLot;
	}

}
