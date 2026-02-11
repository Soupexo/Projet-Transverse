package Modele;

import java.io.*;

/**
 * Classe abstraite représentant une maison à la Cité Internationale Universitaire de Paris (CIUP).
 * Une maison possède un nom, une nationalité associée, un directeur et une localisation.
 * <p>
 * Cette classe sert de base pour des sous-classes comme {@link MaisonEtudiante}.
 * </p>
 * 
 * @author Lucas Pausé-Chapuis
 * @version 1.0
 */
public abstract class Maison implements Serializable {
	
	/** Référence vers la CIUP */
    protected Ciup ciup;

    /** Nom de la maison (ex : "Maison du Japon") */
    protected String nom;

    /** Nationalité associée à la maison (ex : "Japonais") */
    protected String nationalite;

    /** Nom du directeur ou de la directrice de la maison */
    protected String directeur;

    /** Localisation géographique de la maison (ex : "Boulevard Jourdan") */
    protected String localisation;
    
    /** Chemin d'accès à l'image de miniature pour cette maison */
    protected String cheminImage;
    
    /** Texte de description pour cette maison */
    protected String description;

    /** Numéro de version utilisé pour la sérialisation */
    private static final long serialVersionUID = 1L;
    
    
    //==========================================================================
  	//                           GETTERS
  	//==========================================================================

    
    /**
	 * Retourne le nom de la maison étudiante.
	 * 
	 * @return Le nom de la maison
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne la nationalité représentée par la maison.
	 * 
	 * @return La nationalité de la maison
	 */
	public String getNationalite() {
		return nationalite;
	}

	/**
	 * Retourne le nom du directeur de la maison.
	 * 
	 * @return Le nom du directeur de la maison
	 */
	public String getDirecteur() {
		return directeur;
	}

	/**
	 * Retourne la localisation renseignée pour la maison.
	 * 
	 * @return La localisation de la maison
	 */
	public String getLocalisation() {
		return localisation;
	}
	
	/**
	 * Retourne le nombre d'étudiants pour la maison.
	 * 
	 * @return Le nombre d'étudiants pour la maison
	 */
	public abstract int getNbEtudiants();
	
	/**
	 * Retourne le nombre de chambres pour la maison.
	 * 
	 * @return Le nombre de chambres pour la maison
	 */
	public abstract int getNbChambres();

	/**
	 * Retourne la référence vers la CIUP.
	 * 
	 * @return La CIUP à laquelle appartient cette maison
	 */
	public Ciup getCiup() {
		return this.ciup;
	}
	
	/**
	 * Retourne le chemin d'accès à l'image de miniature pour la maison.
	 * 
	 * @return Le chemin d'accès à l'image de miniature pour la maison
	 */
	public String getCheminImage() {
	    return cheminImage;
	}
	
	public String getDescription() {
	    return description;
	}
	
	
	//==========================================================================
	//                           SETTERS
	//==========================================================================
	
	
	/**
	 * Définit le nom de la maison étudiante.
	 * 
	 * @param nom Le nouveau nom de la maison
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Définit la nationalité représentée par la maison étudiante.
	 * 
	 * @param nationalite La nouvelle nationalité de la maison
	 */
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	/**
	 * Définit le nom du directeur de la maison étudiante.
	 * 
	 * @param directeur Le nouveau nom du directeur de la maison
	 */
	public void setDirecteur(String directeur) {
		this.directeur = directeur;
	}

	/**
	 * Définit la localisation de la maison étudiante.
	 * 
	 * @param localisation La nouvelle localisation de la maison
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	
	/**
	 * Définit la CIUP de la maison.
	 * 
	 * @param ciup La nouvelle CIUP de la maison
	 */
	public void setCiup(Ciup ciup) {
		this.ciup = ciup;
	}
	
	/**
	 * Définit le chemin d'accès à l'image de miniature pour la maison.
	 * 
	 * @param cheminImage Le chemin d'accès à l'image de miniature de la maison
	 */
	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}
	
	/**
	 * Définit le texte de descritpion pour la maison.
	 * 
	 * @param description Le texte de descriptipn de la maison
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
