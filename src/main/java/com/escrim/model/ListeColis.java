package com.escrim.model;

import java.util.ArrayList;
import java.util.List;

public class ListeColis {
	
	private List<Colis> lesColis;
	private static ListeColis instance;

	/**
	 *Constructeur (privé). 
	 */
	private ListeColis() {
		this.lesColis = new ArrayList<Colis>();
	}

	public static ListeColis getInstance() 
	{
		if(instance == null) 
		{
			instance = new ListeColis();
		}
		return instance;
	}
	
	/**
	   * Retrouve si un Colis appartient à lesColis à partir de son idColis, et renvoie l'indice dans lesColis auquel il se trouve.
	   * Si le Colis n'appartient pas à lesColis, renvoie -1. 
	   * 
	   * @param idColis		Le idColis du Colis à retrouver
	   * @return int
	   */
	public int findColis(int idColis) {
		for (int i = 0; i < lesColis.size(); i++)
		{
			if (lesColis.get(i).getIdColis() == idColis)
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	   * Ajoute un Colis à lesColis si son idColis n'apparaît pas déjà dedans. 
	   * 
	   * @param nature 				La nature du Colis à ajouter
	   * @param idColis 			Le idColis du Colis à ajouter
	   * @param volume 				Le volume du Colis à ajouter
	   * @param cotes 				Les cotes  du Colis à ajouter
	   * @param designation 		La designation du Colis à ajouter
	   * @param precisions 			Les precisions du Colis à ajouter
	   * @return void
	   */
	public void addColis(String nature, int idColis, float volume, float cotes, String designation, String precisions, int idMoyenTransport) {
		
		if (findColis(idColis) == -1)
		{
			Colis colis = new Colis(nature, idColis, volume, cotes, designation, precisions, idMoyenTransport);
			this.lesColis.add(colis);
		}
	}
	
	/**
	   * Supprime un Colis de lesColis si son idColis apparaît dedans. 
	   * 
	   * @param idColis 			Le idColis du Colis à supprimer
	   * @return void
	   */
	public void deleteColis(int idColis) {
		int indice = findColis(idColis);
		if (indice != -1) {
			lesColis.remove(indice);
			}
	}
	
	/**
	   * Met à jour la nature d'un Colis de lesColis si son idColis apparaît dedans. 
	   * 
	   * @param idColis 	Le idColis du Colis à mettre à jour
	   * @param nature		La nature du Colis à modifier
	   * @return void
	   */
	public void updateNatureColis(int idColis, String nature) {
		int indice = findColis(idColis);
		if (indice != -1)
		{
			Colis ancienColis = lesColis.get(indice);
			float volume = ancienColis.getVolume();
			float cotes = ancienColis.getCotes();
			String designation = ancienColis.getDesignation();
			String precisions = ancienColis.getPrecisions();
			int idMoyenTransport = ancienColis.getIdMoyenTransport();
			lesColis.remove(indice);
			Colis colis = new Colis(nature, idColis, volume, cotes, designation, precisions, idMoyenTransport);
			this.lesColis.add(colis);
		}
		
	}
	
	/**
	   * Met à jour le volume d'un Colis de lesColis si son idColis apparaît dedans. 
	   * 
	   * @param idColis 	Le idColis du Colis à mettre à jour
	   * @param volume		Le volume du Colis à modifier
	   * @return void
	   */
	public void updateVolumeColis(int idColis, float volume) {
		int indice = findColis(idColis);
		if (indice != -1)
		{
			Colis ancienColis = lesColis.get(indice);
			String nature = ancienColis.getNature();
			float cotes = ancienColis.getCotes();
			String designation = ancienColis.getDesignation();
			String precisions = ancienColis.getPrecisions();
			int idMoyenTransport = ancienColis.getIdMoyenTransport();
			lesColis.remove(indice);
			Colis colis = new Colis(nature, idColis, volume, cotes, designation, precisions, idMoyenTransport);
			this.lesColis.add(colis);
		}
		
	}
	
	/**
	   * Met à jour les cotes d'un Colis de lesColis si son idColis apparaît dedans. 
	   * 
	   * @param idColis 	Le idColis du Colis à mettre à jour
	   * @param cotes		Les cotes du Colis à modifier
	   * @return void
	   */
	public void updateCotesColis(int idColis, float cotes) {
		int indice = findColis(idColis);
		if (indice != -1)
		{
			Colis ancienColis = lesColis.get(indice);
			String nature = ancienColis.getNature();
			float volume = ancienColis.getVolume();
			String precisions = ancienColis.getPrecisions();
			String designation = ancienColis.getDesignation();
			int idMoyenTransport = ancienColis.getIdMoyenTransport();
			lesColis.remove(indice);
			Colis colis = new Colis(nature, idColis, volume, cotes, designation, precisions, idMoyenTransport);
			this.lesColis.add(colis);
		}
		
	}
	
	/**
	   * Met à jour les precisions d'un Colis de lesColis si son idColis apparaît dedans. 
	   * 
	   * @param idColis 		Le idColis du Colis à mettre à jour
	   * @param precisions		Les precisions du Colis à modifier
	   * @return void
	   */
	public void updatePrecisionsColis(int idColis, String precisions) {
		int indice = findColis(idColis);
		if (indice != -1)
		{
			Colis ancienColis = lesColis.get(indice);
			String nature = ancienColis.getNature();
			float volume = ancienColis.getVolume();
			float cotes = ancienColis.getCotes();
			String designation = ancienColis.getDesignation();
			int idMoyenTransport = ancienColis.getIdMoyenTransport();
			lesColis.remove(indice);
			Colis colis = new Colis(nature, idColis, volume, cotes, designation, precisions, idMoyenTransport);
			this.lesColis.add(colis);
		}
		
	}
	
	/**
	   * Met à jour la designation d'un Colis de lesColis si son idColis apparaît dedans. 
	   * 
	   * @param idColis 		Le idColis du Colis à mettre à jour
	   * @param designation		La designation du Colis à modifier
	   * @return void
	   */
	public void updateDesignationColis(int idColis, String designation) {
		int indice = findColis(idColis);
		if (indice != -1)
		{
			Colis ancienColis = lesColis.get(indice);
			String nature = ancienColis.getNature();
			float volume = ancienColis.getVolume();
			float cotes = ancienColis.getCotes();
			String precisions = ancienColis.getPrecisions();
			int idMoyenTransport = ancienColis.getIdMoyenTransport();
			lesColis.remove(indice);
			Colis colis = new Colis(nature, idColis, volume, cotes, designation, precisions, idMoyenTransport);
			this.lesColis.add(colis);
		}
		
	}

}
