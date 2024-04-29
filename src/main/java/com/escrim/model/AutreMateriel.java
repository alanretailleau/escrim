package com.escrim.model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;
import java.util.Objects;

public class AutreMateriel extends Materiel{
	
	private String nomProduit;
	private int idAutreMateriel;
	private int idColis; 
	private int quantite;
	private String unite;
	private float volume;
	
	public AutreMateriel(int idAutreMateriel, String nomProduit, int idColis, int quantite, String unite, float volume, boolean enMission) {
		super(idAutreMateriel, idColis, enMission, nomProduit, quantite, unite, volume);
		this.idAutreMateriel = idAutreMateriel;
		this.nomProduit = nomProduit;
		this.idColis = idColis;
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutreMateriel other = (AutreMateriel) obj;
		return idColis == other.idColis && Objects.equals(nomProduit, other.nomProduit) && quantite == other.quantite
				&& Objects.equals(unite, other.unite)
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
	
	public String getUnite() {
		return unite;
	}
	
	public float getVolume() {
		return volume;
	}

	public int getIdAutreMateriel() {
		return idAutreMateriel;
	}

}
