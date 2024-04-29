package com.escrim.model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;
import java.util.Objects;

public class Colis {
	
	private String nature;
	private int idColis;
	private float volume;
	private float cotes;
	private int idMoyenTransport;
	private String designation;
	private String precisions;
	
	public Colis(String nature, int idColis, float volume, float cotes, String designation, String precisions, int idMoyenTransport) {
		this.nature = nature;
		this.idColis = idColis;
		this.volume = volume;
		this.cotes = cotes;
		this.designation = designation;
		this.precisions = precisions;
		this.idMoyenTransport = idMoyenTransport;
	}
	
	public Colis(String nature, int idColis, float volume, float cotes, String designation, String precisions) {
		this.nature = nature;
		this.idColis = idColis;
		this.volume = volume;
		this.cotes = cotes;
		this.designation = designation;
		this.precisions = precisions;
	}

	public Colis() {
		//TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colis other = (Colis) obj;
		return Float.floatToIntBits(cotes) == Float.floatToIntBits(other.cotes)
				&& Objects.equals(designation, other.designation) && idColis == other.idColis
				&& Objects.equals(nature, other.nature) && Objects.equals(precisions, other.precisions)
				&& Float.floatToIntBits(volume) == Float.floatToIntBits(other.volume);
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

	public String getNature() {
		return nature;
	}
	
	public int getIdColis() {
		return idColis;
	}
	
	public float getVolume() {
		return volume;
	}
	
	public float getCotes() {
		return cotes;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public String getPrecisions() {
		return precisions;
	}

    public int getIdMoyenTransport() {
        return idMoyenTransport;
    }

	public void setNature(String nature2) {
		this.nature = nature2;
		throw new UnsupportedOperationException("Unimplemented method 'setNature'");
	}

    public void setVolume(float volume2) {
        this.volume = volume2;
        throw new UnsupportedOperationException("Unimplemented method 'setVolume'");
    }

    public void setCotes(float cotes2) {
        this.cotes = cotes2;
        throw new UnsupportedOperationException("Unimplemented method 'setCotes'");
    }

    public void setDesignation(String designation2) {
        this.designation = designation2;
        throw new UnsupportedOperationException("Unimplemented method 'setDesignation'");
    }

    public void setPrecisions(String precisions2) {
        this.precisions = precisions2;
        throw new UnsupportedOperationException("Unimplemented method 'setPrecisions'");
    }

    public void setIdMoyenTransport(int idMoyenTransport2) {
        this.idMoyenTransport = idMoyenTransport2;
        throw new UnsupportedOperationException("Unimplemented method 'setIdMoyenTransport'");
    }

}
