package Modele;

import java.util.*;
import java.time.LocalDateTime;
import java.io.*;

/**
 * Classe représentant une maison étudiante au sein d'une CIUP (Cité Internationale Universitaire de Paris).
 * Une maison étudiante contient des chambres, héberge des étudiants et organise des soirées.
 * Elle gère également une liste d'attente pour les étudiants en cas de saturation.
 * 
 * @author Otman Benbouziane
 * @version 1.0
 * @since 1.0
 */
public class MaisonEtudiante extends Maison implements Serializable {
	
	//==========================================================================
	//                              ATTRIBUTS
	//==========================================================================
	
	/** Référence vers la CIUP à laquelle appartient cette maison */
	private Ciup ciup;
	
	/** Liste des étudiants actuellement logés dans cette maison */
	private List<Etudiant> listeEtudiante;
	
	/** Liste des chambres disponibles dans cette maison */
	private List<Chambre> listeChambres;
	
	/** Liste d'attente des étudiants candidats à l'hébergement */
	private List<Etudiant> listeAttente;
	
	/** Liste des nationalités représentées dans cette maison */
	private List<String> listeNationalites;
	
	/** Nombre de nationalités différentes présentes */
	private int nbListeNationalites;
	
	/** Liste du nombre d'étudiants par nationalité */
	private ArrayList<Integer> listeNbEtudiantParNationalite;
	
	/** Agenda des soirées organisées par la maison */
	private List<Soiree> agendaSoiree;
	
	/** Identifiant de version pour la sérialisation */
	private static final long serialVersionUID = 1L;
	
	//==========================================================================
	//                            CONSTRUCTEUR
	//==========================================================================
	
	/**
	 * Constructeur de la classe MaisonEtudiante.
	 * Initialise une nouvelle maison étudiante avec ses caractéristiques de base.
	 * 
	 * @param ciup La CIUP à laquelle rattacher cette maison
	 * @param nom Le nom de la maison étudiante
	 * @param directeur Le nom du directeur de la maison
	 * @param nationalite La nationalité principale de la maison
	 * @param localisation L'adresse ou localisation de la maison
	 * @param capaciteChambre Liste des capacités des chambres à créer
	 */
	public MaisonEtudiante(Ciup ciup, String nom, String directeur, String nationalite, String localisation, List<Integer> capaciteChambre) {
	    this.ciup = ciup;
		this.nom = nom;
	    this.directeur = directeur;
	    this.nationalite = nationalite;
	    this.localisation = localisation;
	    this.ciup.ajouterMaison(this);
		this.listeEtudiante = new ArrayList<Etudiant>();
		this.listeChambres = new ArrayList<Chambre>();
		this.setListeChambresParCapacite(capaciteChambre);
		this.listeAttente = new ArrayList<Etudiant>();
		this.listeNationalites = new ArrayList<String>();
		this.nbListeNationalites = 0;
		this.listeNbEtudiantParNationalite = new ArrayList<Integer>();
		this.agendaSoiree = new ArrayList<Soiree>();
		this.cheminImage = "contenuVues/images/default_image.png";
	}
	
	public MaisonEtudiante(Ciup ciup, String nom, String directeur, String nationalite, String localisation, List<Integer> capaciteChambre, String cheminImage) {
	    this.ciup = ciup;
		this.nom = nom;
	    this.directeur = directeur;
	    this.nationalite = nationalite;
	    this.localisation = localisation;
	    this.ciup.ajouterMaison(this);
		this.listeEtudiante = new ArrayList<Etudiant>();
		this.listeChambres = new ArrayList<Chambre>();
		this.setListeChambresParCapacite(capaciteChambre);
		this.listeAttente = new ArrayList<Etudiant>();
		this.listeNationalites = new ArrayList<String>();
		this.nbListeNationalites = 0;
		this.listeNbEtudiantParNationalite = new ArrayList<Integer>();
		this.agendaSoiree = new ArrayList<Soiree>();
		this.cheminImage = cheminImage;
	}
	
	//==========================================================================
	//                        GESTION DES SOIRÉES
	//==========================================================================
	
	/**
	 * Ajoute une soirée à l'agenda de la maison.
	 * Les soirées sont triées par date et il ne peut y avoir qu'une soirée par date.
	 * 
	 * @param soiree La soirée à ajouter à l'agenda
	 */
	public void ajouterSoiree(Soiree soiree) {
		int index = 0;
		while (index < this.agendaSoiree.size()) {
			LocalDateTime dateExistante = this.agendaSoiree.get(index).getDateSoiree();
			
			// Soirée déjà prévue à cette date
			if (dateExistante.equals(soiree.getDateSoiree())) {
				System.out.println(" Une soirée est déjà prévue à cette date !");
				return;
			}

			// Insertion triée
			if (dateExistante.isAfter(soiree.getDateSoiree())) {
				break;
			}

			index++;
		}

		this.agendaSoiree.add(index, soiree);
	}
	
	/**
	 * Retire une soirée de l'agenda de la maison.
	 * 
	 * @param soiree La soirée à retirer de l'agenda
	 */
	public void retirerSoiree(Soiree soiree) {
		if (this.agendaSoiree.contains(soiree)) {
			this.agendaSoiree.remove(soiree);
		}
	}
	
	//==========================================================================
	//                    GESTION DES NATIONALITÉS
	//==========================================================================
	
	/**
	 * Ajoute une nouvelle nationalité à la liste si elle n'existe pas déjà.
	 * Initialise le compteur d'étudiants pour cette nationalité à 0.
	 * 
	 * @param nationalite La nationalité à ajouter
	 */
	public void ajouterNationalite(String nationalite) {
		if (!listeNationalites.contains(nationalite)) {
			listeNationalites.add(nationalite);
			this.nbListeNationalites+=1;
			this.listeNbEtudiantParNationalite.add(0);
		}
	}
	
	/**
	 * Incrémente le nombre d'étudiants d'une nationalité donnée.
	 * 
	 * @param index L'indice de la nationalité dans la liste
	 */
	public void incrementerNombreEtudiant(int index) {
		listeNbEtudiantParNationalite.set(index,listeNbEtudiantParNationalite.get(index) + 1);
	}
	
	//==========================================================================
	//                    GESTION DES ÉTUDIANTS
	//==========================================================================
	
	/**
	 * Inscrit un étudiant dans la maison étudiante.
	 * Si une chambre est disponible, l'étudiant est directement logé.
	 * Sinon, il est ajouté à la liste d'attente.
	 * 
	 * @param etudiant L'étudiant à inscrire
	 */
	public void inscrireEtudiant(Etudiant etudiant) {
	    // Déjà logé ailleurs
	    if (etudiant.getMaisonResidence() != null) return;

	    // Déjà en liste d'attente ici : inutile de continuer
	    if (this.getlisteAttente().contains(etudiant)) return;

	    // S'il reste de la place
	    if (this.getCapaciteEtudiantsMax() > listeEtudiante.size()) {
	        Chambre chambreLibre = this.chercheChambreLibre();
	        if (chambreLibre != null) {
	            listeEtudiante.add(etudiant);
	            etudiant.setMaisonResidence(this);
	            chambreLibre.ajouteEtudiant(etudiant);
	            this.ajouterNationalite(etudiant.getNationalite());
	            incrementerNombreEtudiant(this.nbListeNationalites - 1);
	            return;
	        }
	    }

	    // Sinon, ajout en liste d'attente si pas déjà présent
	    ajouterCandidatureMaison(etudiant);
	}
	
	/**
	 * Retire un étudiant de la maison.
	 * <p>
	 * S'il est inscrit, il est retiré de sa chambre et de la liste, 
	 * puis remplacé par un étudiant de la liste d'attente si possible.
	 * Sinon, sa candidature est simplement retirée.
	 *
	 * @param etudiant l'étudiant à retirer.
	 */
	public void retirerEtudiant(Etudiant etudiant) {
	    if (listeEtudiante.contains(etudiant)) {
	        Chambre chambre = this.retrouverChambre(etudiant.getChambre());
	        if (chambre != null) {
	            chambre.retireEtudiant(etudiant);
	            chambre.actualiseStatut();
	            etudiant.setChambre(null);
	        }

	        listeEtudiante.remove(etudiant);
	        etudiant.setMaisonResidence(null);
	        if (listeAttente.size() > 0) {
	        	Etudiant etu = listeAttente.get(0);
	        	this.listeAttente.remove(etu);
	        	this.inscrireEtudiant(etu);
	        }
	    } else {
	        this.RetirerCandidature(etudiant);
	    }
	}
	
	/**
	 * Trie les étudiants (logés et en attente) par nom puis par prénom.
	 * 
	 * @param ordreCroissant true pour un tri croissant, false pour décroissant
	 */
	public void trierEtudiants(boolean ordreCroissant) {
	    Comparator<Etudiant> comparateur = Comparator.comparing(Etudiant::getNom)
	                                                  .thenComparing(Etudiant::getPrenom);
	    if (!ordreCroissant) {
	        comparateur = comparateur.reversed();
	    }

	    Collections.sort(this.getListeEtudiante(), comparateur);
	    Collections.sort(this.getlisteAttente(), comparateur);
	}
	
	//==========================================================================
	//                    GESTION DE LA LISTE D'ATTENTE
	//==========================================================================
	
	/**
	 * Ajoute un étudiant à la liste d'attente de la maison.
	 * 
	 * @param etudiant L'étudiant à ajouter en liste d'attente
	 * @throws IllegalStateException Si l'étudiant est déjà en liste d'attente
	 */
	public void ajouterCandidatureMaison(Etudiant etudiant) {
		if (!listeAttente.contains(etudiant)) {
			listeAttente.add(etudiant);	
		} else {
			throw new IllegalStateException("Étudiant déjà présent dans la liste d'attente.");
		}
	}
	
	/**
	 * Retire un étudiant de la liste d'attente.
	 * 
	 * @param etudiant L'étudiant à retirer de la liste d'attente
	 */
	public void RetirerCandidature (Etudiant etudiant) {
		if (this.listeAttente.contains(etudiant)) {
			this.listeAttente.remove(etudiant);
		}
	}
	
	//==========================================================================
	//                      GESTION DES CHAMBRES
	//==========================================================================
	
	/**
	 * Cherche et retourne la première chambre libre disponible.
	 * 
	 * @return La première chambre libre trouvée
	 * @throws IllegalStateException Si aucune chambre libre n'est disponible
	 */
	public Chambre chercheChambreLibre() {
		int numChambre = 0;
		while (numChambre < this.listeChambres.size()) {
			if (listeChambres.get(numChambre).getStatut().equals("libre")) {
				return listeChambres.get(numChambre);
			}
			numChambre++;
		}
		throw new IllegalStateException("Aucune chambre libre disponible");
	}

	/**
	 * Vérifie s'il existe au moins une chambre libre dans la maison.
	 * 
	 * @return true si une chambre libre existe, false sinon
	 */
	public boolean trouveChambreLibre() {
		int numChambre = 0;
		while (numChambre < this.listeChambres.size()) {
		if ("libre".equals(listeChambres.get(numChambre).getStatut())) {
				return true;
			}
			else {
				numChambre++;
			}
		}
		return false;
	}
	
	/**
	 * Retrouve une chambre spécifique dans la liste des chambres de la maison.
	 * 
	 * @param chmb La chambre à retrouver
	 * @return La chambre correspondante dans la liste, ou la chambre passée en paramètre si non trouvée
	 */
	public Chambre retrouverChambre (Chambre chmb) {
		Chambre chambre = chmb;
		for (int i = 0; i < this.listeChambres.size(); i++) {
			if (chmb.equals(this.listeChambres.get(i))) {
				chambre = listeChambres.get(i);
			}
		}
		return chambre;
	}
	
	/**
	 * Initialise la liste des chambres selon les capacités spécifiées.
	 * 
	 * @param listeCapacite Liste des capacités pour chaque chambre à créer
	 */
	public void setListeChambresParCapacite(List<Integer> listeCapacite) {
		for (int cpt = 0; cpt < listeCapacite.size(); cpt++) {
			Chambre chmb = new Chambre(listeCapacite.get(cpt), this, cpt);
			this.listeChambres.add(chmb);
		}
	}
	
	//==========================================================================
	//                        MÉTHODES UTILITAIRES
	//==========================================================================
	
	/**
	 * Vérifie si la maison est saturée (capacité maximale atteinte).
	 * 
	 * @return true si la maison est saturée, false sinon
	 */
	public boolean estSaturé() {
		return this.listeEtudiante.size() == this.getCapaciteEtudiantsMax();
	}
	
	/**
	 * Calcule la capacité maximale d'étudiants que peut accueillir la maison.
	 * 
	 * @return Le nombre maximum d'étudiants pouvant être logés
	 */
	public int getCapaciteEtudiantsMax() {
		int capacite = 0;
		for (int cpt = 0; cpt < this.listeChambres.size(); cpt++) {
			capacite += listeChambres.get(cpt).getCapacite();
		}
		return capacite;
	}
	
	//==========================================================================
	//                       ACCESSEURS ET GETTERS
	//==========================================================================
	
	/**
	 * Retourne le nombre d'étudiants actuellement logés dans la maison.
	 * 
	 * @return Le nombre d'étudiants logés
	 */
	public int getNbEtudiants() {
		return listeEtudiante.size();
	}
	
	/**
	 * Retourne la liste des étudiants logés dans la maison.
	 * 
	 * @return La liste des étudiants
	 */
	public List<Etudiant> getListeEtudiante() {
		return listeEtudiante;
	}

	/**
	 * Retourne la liste des chambres de la maison.
	 * 
	 * @return La liste des chambres
	 */
	public List<Chambre> getListeChambres() {
		return listeChambres;
	}

	/**
	 * Retourne la liste d'attente des étudiants candidats.
	 * 
	 * @return La liste d'attente
	 */
	public List<Etudiant> getlisteAttente() {
		return listeAttente;
	}

	/**
	 * Retourne la liste des nationalités représentées dans la maison.
	 * 
	 * @return La liste des nationalités
	 */
	public List<String> getListeNationalites() {
		return listeNationalites;
	}

	/**
	 * Retourne la liste du nombre d'étudiants par nationalité.
	 * 
	 * @return La liste des effectifs par nationalité
	 */
	public ArrayList<Integer> getListeNbEtudiantParNationalite() {
		return listeNbEtudiantParNationalite;
	}

	/**
	 * Retourne le nombre de chambres dans la maison.
	 * 
	 * @return Le nombre de chambres
	 */
	public int getNbChambres() {
		return listeChambres.size();
	}
	
	/**
	 * Retourne l'agenda des soirées de la maison.
	 * 
	 * @return La liste des soirées programmées
	 */
	public List<Soiree> getAgenda() {
		return this.agendaSoiree;
	}
	
	//==========================================================================
	//                           SETTERS
	//==========================================================================

	/**
	 * Définit la liste des étudiants logés dans la maison.
	 * 
	 * @param listeEtudiante La nouvelle liste d'étudiants
	 */
	public void setListeEtudiante(List<Etudiant> listeEtudiante) {
		this.listeEtudiante = listeEtudiante;
	}

	/**
	 * Définit la liste des chambres de la maison.
	 * 
	 * @param listeChambres La nouvelle liste de chambres
	 */
	public void setListeChambres(List<Chambre> listeChambres) {
		this.listeChambres = listeChambres;
	}

	/**
	 * Définit la liste d'attente des étudiants candidats.
	 * 
	 * @param listeAttente La nouvelle liste d'attente
	 */
	public void setlisteAttente(List<Etudiant> listeAttente) {
		this.listeAttente = listeAttente;
	}

	/**
	 * Définit la liste des nationalités représentées.
	 * 
	 * @param listeNationalites La nouvelle liste de nationalités
	 */
	public void setListeNationalites(List<String> listeNationalites) {
		this.listeNationalites = listeNationalites;
	}

	/**
	 * Définit la liste du nombre d'étudiants par nationalité.
	 * 
	 * @param listeNbEtudiantParNationalite La nouvelle liste des effectifs
	 */
	public void setListeNbEtudiantParNationalite(ArrayList<Integer> listeNbEtudiantParNationalite) {
		this.listeNbEtudiantParNationalite = listeNbEtudiantParNationalite;
	}
	
	//==========================================================================
	//                           TOSTRING
	//==========================================================================
	
	/**
	 * Retourne une représentation textuelle de la maison étudiante.
	 * Inclut les informations de base, la liste des étudiants logés et la liste d'attente.
	 * 
	 * @return Une chaîne de caractères décrivant la maison étudiante
	 */
	public String toString() {
		String s = "";
		s += "Nom : " + this.nom + ", nationalité : " + this.nationalite + ", directeur : " + this.directeur + ", localisation : " + this.localisation + "\n\nListe des étudiants :\n";
		for (Etudiant etudiant: listeEtudiante) {
			s += etudiant.getNom() + "\n";
		}
		s += "\nListe d'attente :\n";
		for (Etudiant etudiant: listeAttente) {
			s += etudiant.getNom() + "\n";
		}
		return s;
	}
	
}