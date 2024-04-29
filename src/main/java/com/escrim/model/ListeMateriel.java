package com.escrim.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListeMateriel {

	private List<Materiel> lesMateriels;
	private static ListeMateriel instance;

	/**
	 * Constructeur (privé).
	 */
	private ListeMateriel() {
		this.lesMateriels = new ArrayList<Materiel>();
	}

	public static ListeMateriel getInstance() {
		if (instance == null) {
			instance = new ListeMateriel();
		}
		return instance;
	}

	/**
	 * Retrouve si un Materiel appartient à lesMateriels à partir de son nomProduit,
	 * et renvoie l'indice dans lesMateriels auquel il se trouve.
	 * Si le materiel n'appartient pas à lesMateriels, renvoie -1.
	 * 
	 * @param nomProduit Le nomProduit du Materiel à retrouver
	 * @return int
	 */
	public int findMateriel(String nomProduit) {
		for (int i = 0; i < lesMateriels.size(); i++) {
			if (lesMateriels.get(i).getNomProduit().equals(nomProduit)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Ajoute un Materiel à lesMateriels si son nomProduit n'apparaît pas déjà
	 * dedans, et qu'il s'agit d'un AutreMateriel.
	 * 
	 * @param nomProduit Le nomProduit du Materiel à ajouter
	 * @param idColis    Le idColis du Materiel à ajouter
	 * @param quantite   La quantite du Materiel à ajouter
	 * @param unite      La unite du Materiel à ajouter
	 * @param volume     Le volume du Materiel à ajouter
	 * @return
	 */
	public void addMateriel(int idMateriel, String nomProduit, int idColis, int quantite, String unite, float volume,
			boolean enMission) {
		if (findMateriel(nomProduit) == -1) {
			Materiel materiel = new AutreMateriel(idMateriel, nomProduit, idColis, quantite, unite, volume, enMission);
			this.lesMateriels.add(materiel);
		}
	}

	/**
	 * Ajoute un Materiel à lesMateriels si son nomProduit n'apparaît pas déjà
	 * dedans, et qu'il s'agit d'un MaterielMedical.
	 * 
	 * @param nomProduit Le nomProduit du Materiel à ajouter
	 * @param idColis    Le idColis du Materiel à ajouter
	 * @param quantite   La quantite du Materiel à ajouter
	 * @param unite      La unite du Materiel à ajouter
	 * @param volume     Le volume du Materiel à ajouter
	 * @param dlu        La dlu du Materiel à ajouter
	 * @param numeroLot  Le numeroLot du Materiel à ajouter
	 * @return
	 */
	public void addMateriel(int idMaterielMedical, String nomProduit, int idColis, int quantite, String unite, float volume, LocalDate dlu,
			int numeroLot, boolean enMission) {
		if (findMateriel(nomProduit) == -1) {
			Materiel materiel = new MaterielMedical(idMaterielMedical, nomProduit, idColis, quantite, unite, volume, dlu, numeroLot,
					enMission);
			this.lesMateriels.add(materiel);
		}
	}

	/**
	 * Ajoute un Materiel à lesMateriels si son nomProduit n'apparaît pas déjà
	 * dedans, et qu'il s'agit d'un Medicament.
	 * 
	 * @param nomProduit          Le nomProduit du Materiel à ajouter
	 * @param idColis             Le idColis du Materiel à ajouter
	 * @param quantite            La quantite du Materiel à ajouter
	 * @param unite               La unite du Materiel à ajouter
	 * @param volume              Le volume du Materiel à ajouter
	 * @param dlu                 La dlu du Materiel à ajouter
	 * @param numeroLot           Le numeroLot du Materiel à ajouter
	 * @param formeDosage         La formeDosage du Materiel à ajouter
	 * @param classeTherapeutique La classeTherapeutique du Materiel à ajouter
	 * @param dci                 Le dci du Materiel à ajouter
	 * @return
	 */
	public void addMateriel(int idMedicament, String nomProduit, int idColis, int quantite, String unite, float volume, LocalDate dlu,
			int numeroLot, String dci, String classeTherapeutique, String formeDosage, boolean enMission) {
		if (findMateriel(nomProduit) == -1) {
			Materiel materiel = new Medicament(idMedicament, nomProduit, idColis, quantite, unite, volume, dlu, formeDosage,
					classeTherapeutique, dci, numeroLot, enMission);
			this.lesMateriels.add(materiel);
		}
	}

	/**
	 * Supprime un Materiel de lesMateriels si son nomProduit apparaît dedans.
	 * 
	 * @param nomProduit Le nomProduit du Colis à supprimer
	 * @return void
	 */
	public void deleteMateriel(String nomProduit) {
		int indice = findMateriel(nomProduit);
		if (indice != -1) {
			lesMateriels.remove(indice);
		}
	}

	/**
	 * Met à jour l' idColis d'un Materiel de lesMateriels si son nomProduit
	 * apparaît dedans.
	 * 
	 * @param nomProduit Le nomProduit du Materiel à mettre à jour
	 * @param idColis    L' idColis du Materiel à modifier
	 * @return void
	 */
	public void updateIdColisMateriel(String nomProduit, int idColis) {
		int indice = findMateriel(nomProduit);
		if (indice != -1) {
			Materiel ancienMateriel = lesMateriels.get(indice);

			int quantite = ancienMateriel.getQuantite();
			String unite = ancienMateriel.getUnite();
			float volume = ancienMateriel.getVolume();
			int id = ancienMateriel.getIdMateriel();
			boolean enMission = ancienMateriel.isEnMission();

			if (ancienMateriel instanceof AutreMateriel) {
				Materiel materiel = new AutreMateriel(id, nomProduit, idColis, quantite, unite, volume, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}

			else if (ancienMateriel instanceof MaterielMedical) {
				MaterielMedical materielMedical = (MaterielMedical) ancienMateriel;
				LocalDate dlu = materielMedical.getDlu();
				int idMaterielMedical = materielMedical.getIdMateriel();
				int numeroLot = materielMedical.getNumeroLot();
				Materiel materiel = new MaterielMedical(idMaterielMedical, nomProduit, idColis, quantite, unite, volume, dlu, numeroLot,
						enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}

			else if (ancienMateriel instanceof Medicament) {
				Medicament medicament = (Medicament) ancienMateriel;
				LocalDate dlu = medicament.getDlu();
				int idMedicament = medicament.getId();
				int numeroLot = medicament.getNumeroLot();
				String formeDosage = medicament.getFormeDosage();
				String classeTherapeutique = medicament.getClasseTherapeutique();
				String dci = medicament.getDci();
				Materiel materiel = new Medicament(idMedicament, nomProduit, idColis, quantite, unite, volume, dlu, formeDosage,
						classeTherapeutique, dci, numeroLot, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}
		}
	}

	/**
	 * Met à jour la quantite d'un Materiel de lesMateriels si son nomProduit
	 * apparaît dedans.
	 * 
	 * @param nomProduit Le nomProduit du Materiel à mettre à jour
	 * @param quantite   La quantite du Materiel à modifier
	 * @return void
	 */
	public void updateQuantiteMateriel(String nomProduit, int quantite) {
		int indice = findMateriel(nomProduit);
		if (indice != -1) {
			Materiel ancienMateriel = lesMateriels.get(indice);

			int idColis = ancienMateriel.getIdColis();
			String unite = ancienMateriel.getUnite();
			float volume = ancienMateriel.getVolume();
			int id = ancienMateriel.getIdMateriel();
			boolean enMission = ancienMateriel.isEnMission();

			if (ancienMateriel instanceof AutreMateriel) {
				Materiel materiel = new AutreMateriel(id, nomProduit, idColis, quantite, unite, volume, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}

			else if (ancienMateriel instanceof MaterielMedical) {
				MaterielMedical materielMedical = (MaterielMedical) ancienMateriel;
				LocalDate dlu = materielMedical.getDlu();
				int numeroLot = materielMedical.getNumeroLot();
				int idMaterielMedical = materielMedical.getIdMateriel();
				Materiel materiel = new MaterielMedical(idMaterielMedical, nomProduit, idColis, quantite, unite, volume, dlu, numeroLot,
						enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}

			else if (ancienMateriel instanceof Medicament) {
				Medicament medicament = (Medicament) ancienMateriel;
				LocalDate dlu = medicament.getDlu();
				int idMedicament = medicament.getId();
				int numeroLot = medicament.getNumeroLot();
				String formeDosage = medicament.getFormeDosage();
				String classeTherapeutique = medicament.getClasseTherapeutique();
				String dci = medicament.getDci();
				Materiel materiel = new Medicament(idMedicament, nomProduit, idColis, quantite, unite, volume, dlu, formeDosage,
						classeTherapeutique, dci, numeroLot, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}
		}
	}

	/**
	 * Met à jour l'unite d'un Materiel de lesMateriels si son nomProduit apparaît
	 * dedans.
	 * 
	 * @param nomProduit Le nomProduit du Materiel à mettre à jour
	 * @param unite      L'unite du Materiel à modifier
	 * @return void
	 */
	public void updateUniteMateriel(String nomProduit, String unite) {
		int indice = findMateriel(nomProduit);
		if (indice != -1) {
			Materiel ancienMateriel = lesMateriels.get(indice);

			int idColis = ancienMateriel.getIdColis();
			int quantite = ancienMateriel.getQuantite();
			float volume = ancienMateriel.getVolume();
			boolean enMission = ancienMateriel.isEnMission();
			int id = ancienMateriel.getIdMateriel();

			if (ancienMateriel instanceof AutreMateriel) {
				Materiel materiel = new AutreMateriel(id, nomProduit, idColis, quantite, unite, volume, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}

			else if (ancienMateriel instanceof MaterielMedical) {
				MaterielMedical materielMedical = (MaterielMedical) ancienMateriel;
				LocalDate dlu = materielMedical.getDlu();
				int numeroLot = materielMedical.getNumeroLot();
				int idMaterielMedical = materielMedical.getIdMateriel();
				Materiel materiel = new MaterielMedical(idMaterielMedical, nomProduit, idColis, quantite, unite, volume, dlu, numeroLot,
						enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}

			else if (ancienMateriel instanceof Medicament) {
				Medicament medicament = (Medicament) ancienMateriel;
				LocalDate dlu = medicament.getDlu();
				int numeroLot = medicament.getNumeroLot();
				int idMedicament = medicament.getId();
				String formeDosage = medicament.getFormeDosage();
				String classeTherapeutique = medicament.getClasseTherapeutique();
				String dci = medicament.getDci();
				Materiel materiel = new Medicament(idMedicament, nomProduit, idColis, quantite, unite, volume, dlu, formeDosage,
						classeTherapeutique, dci, numeroLot, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}
		}
	}

	/**
	 * Met à jour le volume d'un Materiel de lesMateriels si son nomProduit apparaît
	 * dedans.
	 * 
	 * @param nomProduit Le nomProduit du Materiel à mettre à jour
	 * @param volume     Le volume du Materiel à modifier
	 * @return void
	 */
	public void updateVolumeMateriel(String nomProduit, float volume) {
		int indice = findMateriel(nomProduit);
		if (indice != -1) {
			Materiel ancienMateriel = lesMateriels.get(indice);

			int idColis = ancienMateriel.getIdColis();
			int quantite = ancienMateriel.getQuantite();
			String unite = ancienMateriel.getUnite();
			int id = ancienMateriel.getIdMateriel();
			boolean enMission = ancienMateriel.isEnMission();

			if (ancienMateriel instanceof AutreMateriel) {
				Materiel materiel = new AutreMateriel(id, nomProduit, idColis, quantite, unite, volume, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}

			else if (ancienMateriel instanceof MaterielMedical) {
				MaterielMedical materielMedical = (MaterielMedical) ancienMateriel;
				LocalDate dlu = materielMedical.getDlu();
				int idMaterielMedical = materielMedical.getIdMateriel();
				int numeroLot = materielMedical.getNumeroLot();
				Materiel materiel = new MaterielMedical(idMaterielMedical, nomProduit, idColis, quantite, unite, volume, dlu, numeroLot,
						enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}

			else if (ancienMateriel instanceof Medicament) {
				Medicament medicament = (Medicament) ancienMateriel;
				LocalDate dlu = medicament.getDlu();
				int idMedicament = medicament.getId();
				int numeroLot = medicament.getNumeroLot();
				String formeDosage = medicament.getFormeDosage();
				String classeTherapeutique = medicament.getClasseTherapeutique();
				String dci = medicament.getDci();
				Materiel materiel = new Medicament(idMedicament, nomProduit, idColis, quantite, unite, volume, dlu, formeDosage,
						classeTherapeutique, dci, numeroLot, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}
		}
	}

	/**
	 * Met à jour la dlu d'un Materiel de lesMateriels si son nomProduit apparaît
	 * dedans.
	 * 
	 * @param nomProduit Le nomProduit du Materiel à mettre à jour
	 * @param dlu        La dlu du Materiel à modifier
	 * @return void
	 */
	public void updateDluMateriel(String nomProduit, LocalDate dlu) {
		int indice = findMateriel(nomProduit);
		if (indice != -1) {
			Materiel ancienMateriel = lesMateriels.get(indice);

			int idColis = ancienMateriel.getIdColis();
			int quantite = ancienMateriel.getQuantite();
			float volume = ancienMateriel.getVolume();
			String unite = ancienMateriel.getUnite();
			boolean enMission = ancienMateriel.isEnMission();
			if (ancienMateriel instanceof AutreMateriel) {
			}

			else if (ancienMateriel instanceof MaterielMedical) {
				MaterielMedical materielMedical = (MaterielMedical) ancienMateriel;
				int numeroLot = materielMedical.getNumeroLot();
				int idMaterielMedical = materielMedical.getIdMateriel();
				Materiel materiel = new MaterielMedical(idMaterielMedical, nomProduit, idColis, quantite, unite, volume, dlu, numeroLot,
						enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}

			else if (ancienMateriel instanceof Medicament) {
				Medicament medicament = (Medicament) ancienMateriel;
				int numeroLot = medicament.getNumeroLot();
				int idMedicament = medicament.getId();
				String formeDosage = medicament.getFormeDosage();
				String classeTherapeutique = medicament.getClasseTherapeutique();
				String dci = medicament.getDci();
				Materiel materiel = new Medicament(idMedicament, nomProduit, idColis, quantite, unite, volume, dlu, formeDosage,
						classeTherapeutique, dci, numeroLot, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}
		}
	}

	/**
	 * Met à jour le numeroLot d'un Materiel de lesMateriels si son nomProduit
	 * apparaît dedans.
	 * 
	 * @param nomProduit Le nomProduit du Materiel à mettre à jour
	 * @param numeroLot  Le numeroLot du Materiel à modifier
	 * @return void
	 */
	public void updateNumeroLotMateriel(String nomProduit, int numeroLot) {
		int indice = findMateriel(nomProduit);
		if (indice != -1) {
			Materiel ancienMateriel = lesMateriels.get(indice);

			int idColis = ancienMateriel.getIdColis();
			int quantite = ancienMateriel.getQuantite();
			float volume = ancienMateriel.getVolume();
			String unite = ancienMateriel.getUnite();
			boolean enMission = ancienMateriel.isEnMission();

			if (ancienMateriel instanceof AutreMateriel) {
			}

			else if (ancienMateriel instanceof MaterielMedical) {
				MaterielMedical materielMedical = (MaterielMedical) ancienMateriel;
				LocalDate dlu = materielMedical.getDlu();
				int idMaterielMedical = materielMedical.getIdMateriel();
				Materiel materiel = new MaterielMedical(idMaterielMedical, nomProduit, idColis, quantite, unite, volume, dlu, numeroLot,
						enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}

			else if (ancienMateriel instanceof Medicament) {
				Medicament medicament = (Medicament) ancienMateriel;
				LocalDate dlu = medicament.getDlu();
				String formeDosage = medicament.getFormeDosage();
				int idMedicament = medicament.getId();
				String classeTherapeutique = medicament.getClasseTherapeutique();
				String dci = medicament.getDci();
				Materiel materiel = new Medicament(idMedicament, nomProduit, idColis, quantite, unite, volume, dlu, formeDosage,
						classeTherapeutique, dci, numeroLot, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}
		}
	}

	/**
	 * Met à jour le dci d'un Materiel de lesMateriels si son nomProduit apparaît
	 * dedans.
	 * 
	 * @param nomProduit Le nomProduit du Materiel à mettre à jour
	 * @param dci        Le dci du Materiel à modifier
	 * @return void
	 */
	public void updateDciMateriel(String nomProduit, String dci) {
		int indice = findMateriel(nomProduit);
		if (indice != -1) {
			Materiel ancienMateriel = lesMateriels.get(indice);

			int idColis = ancienMateriel.getIdColis();
			int quantite = ancienMateriel.getQuantite();
			float volume = ancienMateriel.getVolume();
			String unite = ancienMateriel.getUnite();
			boolean enMission = ancienMateriel.isEnMission();

			if (ancienMateriel instanceof AutreMateriel) {
			}

			else if (ancienMateriel instanceof MaterielMedical) {
			}

			else if (ancienMateriel instanceof Medicament) {
				Medicament medicament = (Medicament) ancienMateriel;
				int numeroLot = medicament.getNumeroLot();
				LocalDate dlu = medicament.getDlu();
				int idMedicament = medicament.getId();
				String formeDosage = medicament.getFormeDosage();
				String classeTherapeutique = medicament.getClasseTherapeutique();
				Materiel materiel = new Medicament(idMedicament, nomProduit, idColis, quantite, unite, volume, dlu, formeDosage,
						classeTherapeutique, dci, numeroLot, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}
		}
	}

	/**
	 * Met à jour la formeDosage d'un Materiel de lesMateriels si son nomProduit
	 * apparaît dedans.
	 * 
	 * @param nomProduit  Le nomProduit du Materiel à mettre à jour
	 * @param formeDosage La formeDosage du Materiel à modifier
	 * @return void
	 */
	public void updateFormeDosageMateriel(String nomProduit, String formeDosage) {
		int indice = findMateriel(nomProduit);
		if (indice != -1) {
			Materiel ancienMateriel = lesMateriels.get(indice);

			int idColis = ancienMateriel.getIdColis();
			int quantite = ancienMateriel.getQuantite();
			float volume = ancienMateriel.getVolume();
			String unite = ancienMateriel.getUnite();
			boolean enMission = ancienMateriel.isEnMission();

			if (ancienMateriel instanceof AutreMateriel) {
			}

			else if (ancienMateriel instanceof MaterielMedical) {
			}

			else if (ancienMateriel instanceof Medicament) {
				Medicament medicament = (Medicament) ancienMateriel;
				int numeroLot = medicament.getNumeroLot();
				LocalDate dlu = medicament.getDlu();
				int idMedicament = medicament.getId();
				String classeTherapeutique = medicament.getClasseTherapeutique();
				String dci = medicament.getDci();
				Materiel materiel = new Medicament(idMedicament, nomProduit, idColis, quantite, unite, volume, dlu, formeDosage,
						classeTherapeutique, dci, numeroLot, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}
		}
	}

	/**
	 * Met à jour la classeTherapeutique d'un Materiel de lesMateriels si son
	 * nomProduit apparaît dedans.
	 * 
	 * @param nomProduit          Le nomProduit du Materiel à mettre à jour
	 * @param classeTherapeutique La classeTherapeutique du Materiel à modifier
	 * @return void
	 */
	public void updateClasseTherapeutiqueMateriel(String nomProduit, String classeTherapeutique) {
		int indice = findMateriel(nomProduit);
		if (indice != -1) {
			Materiel ancienMateriel = lesMateriels.get(indice);

			int idColis = ancienMateriel.getIdColis();
			int quantite = ancienMateriel.getQuantite();
			float volume = ancienMateriel.getVolume();
			boolean enMission = ancienMateriel.isEnMission();
			String unite = ancienMateriel.getUnite();

			if (ancienMateriel instanceof AutreMateriel) {
			}

			else if (ancienMateriel instanceof MaterielMedical) {
			}

			else if (ancienMateriel instanceof Medicament) {
				Medicament medicament = (Medicament) ancienMateriel;
				int numeroLot = medicament.getNumeroLot();
				LocalDate dlu = medicament.getDlu();
				int idMedicament = medicament.getId();
				String formeDosage = medicament.getFormeDosage();
				String dci = medicament.getDci();
				Materiel materiel = new Medicament(idMedicament, nomProduit, idColis, quantite, unite, volume, dlu, formeDosage,
						classeTherapeutique, dci, numeroLot, enMission);
				lesMateriels.remove(indice);
				lesMateriels.add(materiel);
			}
		}
	}

}
