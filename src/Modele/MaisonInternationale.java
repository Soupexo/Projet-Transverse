package Modele;

/**
 * Représente une maison internationale au sein de la Cité Internationale Universitaire de Paris (CIUP).
 * Cette classe hérite de la classe abstraite Maison.
 * Elle définit une maison avec un nom, une nationalité, un directeur, une localisation
 * ainsi qu'une image par défaut.
 * @author Lucas Pausé-Chapuis
 */
public class MaisonInternationale extends Maison {
	
	//--------------------------
	//      CONSTRUCTEURS
	//--------------------------
	
	/**
	 * Constructeur de MaisonInternationale.
	 * Initialise une maison internationale avec son nom, sa nationalité,
	 * son directeur et sa localisation.
	 * L'image est définie par défaut.
	 * 
	 * @param nom Le nom de la maison internationale.
	 * @param nationalite La nationalité représentée par la maison.
	 * @param directeur Le nom du directeur de la maison.
	 * @param localisation L'adresse ou la localisation de la maison.
	 * 
	 */
	public MaisonInternationale(String nom, String nationalite, String directeur, String localisation) {
		this.nom = nom;
		this.nationalite = nationalite;
		this.directeur = directeur;
		this.localisation = localisation;
		this.cheminImage = "contenuVues/images/default_image.png";
	}
	
	//--------------------------
	//      GETTERS ET SETTERS
	//--------------------------
	
	/**
	 * Définit la CIUP à laquelle appartient cette maison internationale.
	 * 
	 * @param ciup L'objet Ciup associé.
	 */
	public void setCiup(Ciup ciup) {
		this.ciup = ciup;
	}
	
	/**
	 * Renvoie le nombre total d'étudiants inscrits dans la CIUP.
	 * 
	 * @return Le nombre d'étudiants dans la CIUP.
	 */
	public int getNbEtudiants() {
		return this.getCiup().getNbEtudiants();
	}
	
	/**
	 * Renvoie le nombre total de chambres disponibles dans la CIUP.
	 * 
	 * @return Le nombre de chambres dans la CIUP.
	 */
	public int getNbChambres() {
		return this.getCiup().getNbChambres();
	}
	
}
