package Modele;

import java.util.*;
import java.io.*;

/**
 * Représente une chambre dans une maison étudiante de la CIUP.
 * <p>
 * Chaque chambre possède un identifiant unique, une capacité maximale,
 * un statut indiquant si elle est libre ou saturée, ainsi qu'une liste
 * d'étudiants qui y résident. La chambre est associée à une maison étudiante.
 * </p>
 * @author Otman Benbouziane
 */
public class Chambre implements Serializable {

    // --------------------------
    //       ATTRIBUTS
    // --------------------------

    /** Identifiant unique de la chambre */
    private int identifiant;

    /** Maison étudiante à laquelle appartient la chambre */
    private MaisonEtudiante maison;

    /** Liste des étudiants actuellement logés dans la chambre */
    private List<Etudiant> listeEtudiante;

    /** Capacité maximale de la chambre */
    private int capacite;

    /** Statut de la chambre : "libre" ou "saturé" */
    private String statut;

    /** UID de version pour la sérialisation */
    private static final long serialVersionUID = 1L;

    // --------------------------
    //      CONSTRUCTEUR
    // --------------------------

    /**
     * Construit une chambre avec la capacité, la maison et l'identifiant spécifiés.
     * Le statut initial est "libre".
     *
     * @param capacite la capacité maximale de la chambre
     * @param maison   la maison étudiante à laquelle appartient la chambre
     * @param id       l'identifiant unique de la chambre
     */
    public Chambre(int capacite, MaisonEtudiante maison, int id) {
        this.maison = maison;
        this.capacite = capacite;
        this.listeEtudiante = new ArrayList<>();
        this.identifiant = id;
        this.statut = "libre";
    }

    // --------------------------
    //      ACCESSEURS
    // --------------------------

    /** 
     * Retourne l'identifiant unique de la chambre.
     * 
     * @return identifiant de la chambre
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit un nouvel identifiant pour la chambre.
     * 
     * @param identifiant nouvel identifiant
     */
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Retourne la maison étudiante associée à la chambre.
     * 
     * @return maison étudiante
     */
    public MaisonEtudiante getMaison() {
        return maison;
    }

    /**
     * Définit une nouvelle maison étudiante pour la chambre.
     * 
     * @param maison nouvelle maison
     */
    public void setMaison(MaisonEtudiante maison) {
        this.maison = maison;
    }

    /**
     * Retourne la liste des étudiants résidant dans la chambre.
     * 
     * @return liste des étudiants
     */
    public List<Etudiant> getListeEtudiante() {
        return listeEtudiante;
    }

    /**
     * Définit la liste des étudiants de la chambre.
     * 
     * @param listeEtudiante nouvelle liste d'étudiants
     */
    public void setListeEtudiante(List<Etudiant> listeEtudiante) {
        this.listeEtudiante = listeEtudiante;
    }

    /**
     * Retourne la capacité maximale de la chambre.
     * 
     * @return capacité maximale
     */
    public int getCapacite() {
        return capacite;
    }

    /**
     * Modifie la capacité maximale de la chambre.
     * 
     * @param capacite nouvelle capacité
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    /**
     * Retourne le statut actuel de la chambre : "libre" ou "saturé".
     * 
     * @return statut de la chambre
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Définit le statut de la chambre.
     * 
     * @param statut nouveau statut ("libre" ou "saturé")
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    // --------------------------
    //        METHODES
    // --------------------------

    /**
     * Vérifie l'égalité entre deux chambres selon leur identifiant, capacité
     * et la liste des étudiants.
     *
     * @param obj objet à comparer
     * @return true si les chambres sont égales, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Chambre other = (Chambre) obj;

        return this.identifiant == other.identifiant &&
               this.capacite == other.capacite &&
               Objects.equals(this.listeEtudiante, other.listeEtudiante);
    }

    /**
     * Calcule le hash code de la chambre à partir de la capacité, la maison
     * et l'identifiant.
     *
     * @return hash code de la chambre
     */
    @Override
    public int hashCode() {
        return Objects.hash(capacite, maison, identifiant);
    }

    /**
     * Ajoute un étudiant à la chambre si la capacité n'est pas atteinte et si
     * l'étudiant n'y est pas déjà.
     * <p>
     * Met à jour la maison et la chambre de l'étudiant, puis actualise le statut.
     * </p>
     *
     * @param etudiant étudiant à ajouter
     * @throws IllegalStateException si la chambre est pleine ou si l'étudiant est déjà présent
     */
    public void ajouteEtudiant(Etudiant etudiant) {
        if (listeEtudiante.contains(etudiant)) {
            throw new IllegalStateException("Étudiant déjà présent dans cette chambre.");
        }
        if (capacite <= listeEtudiante.size()) {
            throw new IllegalStateException("Capacité de la chambre insuffisante");
        }
        listeEtudiante.add(etudiant);
        etudiant.setMaisonResidence(this.maison);
        etudiant.setChambre(this);
        actualiseStatut();
    }

    /**
     * Retire un étudiant de la chambre s'il y est présent, puis met à jour le statut.
     *
     * @param etudiant étudiant à retirer
     */
    public void retireEtudiant(Etudiant etudiant) {
        if (listeEtudiante.contains(etudiant)) {
            listeEtudiante.remove(etudiant);
            actualiseStatut();
        }
    }

    /**
     * Actualise le statut de la chambre en fonction du nombre d'étudiants :
     * "saturé" si la capacité est atteinte, sinon "libre".
     */
    public void actualiseStatut() {
        this.statut = (listeEtudiante.size() == capacite) ? "saturé" : "libre";
    }
}
