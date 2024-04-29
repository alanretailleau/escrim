package com.escrim.model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Medicament extends Materiel{
	
	private  int idMedicament;
	private String nomProduit;
	private int idColis; 
	private int quantite;
	private String unite;
	private float volume;
	private LocalDate dlu;
	private String formeDosage;
	private String classeTherapeutique;
	private String dci;
	private int numeroLot;
	
	public Medicament(int idMedicament, String nomProduit, int idColis, int quantite, String unite, float volume, LocalDate dlu, String formeDosage, String classeTherapeutique, String dci, int numeroLot, boolean enMission) {
		super(numeroLot, idColis, enMission, nomProduit, quantite, unite, volume);

		this.nomProduit = nomProduit;
		this.idColis = idColis;
		this.idMedicament = idMedicament;
		this.quantite = quantite;
		this.unite = unite;
		this.volume = volume;
		this.dlu = dlu;
		this.formeDosage = formeDosage;
		this.classeTherapeutique = classeTherapeutique;
		this.dci = dci;
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
		Medicament other = (Medicament) obj;
		return Objects.equals(classeTherapeutique, other.classeTherapeutique) && Objects.equals(dci, other.dci)
				&& Objects.equals(dlu, other.dlu) && Objects.equals(formeDosage, other.formeDosage)
				&& idColis == other.idColis && Objects.equals(nomProduit, other.nomProduit)
				&& numeroLot == other.numeroLot && quantite == other.quantite && Objects.equals(unite, other.unite)
				&& Float.floatToIntBits(volume) == Float.floatToIntBits(other.volume);
	}

	public String getNomProduit() {
		return nomProduit;
	}
	public int getId() {
		return idMedicament;
	}

	public int getIdColis() {
		return idColis;
	}
	
	public int getIdMedicament() {
		return idMedicament;
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

	public LocalDate getDlu() {
		return dlu;
	}

	public String getFormeDosage() {
		return formeDosage;
	}

	public String getClasseTherapeutique() {
		return classeTherapeutique;
	}

	public String getDci() {
		return dci;
	}

	public int getNumeroLot() {
		return numeroLot;
	}
	

}
