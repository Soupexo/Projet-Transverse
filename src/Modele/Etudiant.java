package Modele;

import java.util.*;
import java.io.*;

/**
 * Représente un étudiant de la CIUP avec ses informations personnelles,
 * sa maison de résidence, sa chambre, et éventuellement une maison souhaitée.
 * @author Otman Benbouziane
 */
public class Etudiant implements Serializable {

    //--------------------------
    //         ATTRIBUTS
    //--------------------------

    /** Maison actuelle de résidence de l'étudiant */
    private MaisonEtudiante maisonResidence;

    /** Chambre attribuée à l'étudiant */
    private Chambre chambre;

    /** Nom de l'étudiant */
    private String nom;

    /** Prénom de l'étudiant */
    private String prenom;

    /** Nationalité de l'étudiant */
    private String nationalite;

    /** Maison souhaitée par l'étudiant (peut être null) */
    private MaisonEtudiante souhait;

    /** Référence à la CIUP pour l'affectation automatique */
    private Ciup ciup;

    /** UID de version pour la sérialisation */
    private static final long serialVersionUID = 1L;

    //--------------------------
    //       CONSTRUCTEURS
    //--------------------------

    /**
     * Constructeur pour un étudiant ayant exprimé un souhait de maison.
     * L'étudiant est automatiquement inscrit à la maison souhaitée.
     *
     * @param ciup        Référence à la CIUP pour accès aux maisons
     * @param nom         Nom de l'étudiant
     * @param prenom      Prénom de l'étudiant
     * @param nationalite Nationalité de l'étudiant
     * @param souhait     Maison souhaitée (ne peut être null)
     */
    public Etudiant(Ciup ciup, String nom, String prenom, String nationalite, MaisonEtudiante souhait) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
        this.souhait = souhait;
        this.ciup = ciup;
        if (this.souhait != null) {
            this.souhait.inscrireEtudiant(this);
        }
    }

    /**
     * Constructeur pour un étudiant sans souhait de maison.
     * L'affectation à une maison nationale est automatique.
     *
     * @param ciup        Référence à la CIUP pour accès aux maisons
     * @param nom         Nom de l'étudiant
     * @param prenom      Prénom de l'étudiant
     * @param nationalite Nationalité de l'étudiant
     */
    public Etudiant(Ciup ciup, String nom, String prenom, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
        this.ciup = ciup;
        this.ciup.affecterMaisonNationale(this);
    }

    //--------------------------
    //      MÉTHODES OVERRIDES
    //--------------------------

    /**
     * Vérifie l'égalité entre deux étudiants sur la base du nom et du prénom.
     *
     * @param o Objet à comparer
     * @return true si nom et prénom sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Etudiant)) return false;
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(nom, etudiant.nom) &&
               Objects.equals(prenom, etudiant.prenom);
    }

    /**
     * Génère un hashCode basé sur le nom et le prénom de l'étudiant.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom);
    }

    /**
     * Représentation textuelle complète de l'étudiant.
     *
     * @return chaîne de caractères descriptive
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Etudiant[nom=").append(nom)
          .append(", prenom=").append(prenom)
          .append(", nationalite=").append(nationalite);

        if (maisonResidence != null)
            sb.append(", maisonResidence=").append(maisonResidence.getNom());
        else
            sb.append(", maisonResidence=null");

        if (chambre != null)
            sb.append(", chambre=").append(chambre.getIdentifiant());
        else
            sb.append(", chambre=null");

        if (souhait != null)
            sb.append(", souhait=").append(souhait.getNom());
        else
            sb.append(", souhait=null");

        sb.append("]");
        return sb.toString();
    }

    //--------------------------
    //         ACCESSEURS
    //--------------------------

    /**
     * Obtient la maison de résidence actuelle de l'étudiant.
     * 
     * @return la maison de résidence
     */
    public MaisonEtudiante getMaisonResidence() {
        return maisonResidence;
    }

    /**
     * Définit la maison de résidence de l'étudiant.
     * 
     * @param maisonResidence la maison de résidence à définir
     */
    public void setMaisonResidence(MaisonEtudiante maisonResidence) {
        this.maisonResidence = maisonResidence;
    }

    /**
     * Obtient la chambre attribuée à l'étudiant.
     * 
     * @return la chambre de l'étudiant
     */
    public Chambre getChambre() {
        return chambre;
    }

    /**
     * Définit la chambre attribuée à l'étudiant.
     * 
     * @param chambre la chambre à définir
     */
    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    /**
     * Obtient le nom de l'étudiant.
     * 
     * @return le nom de l'étudiant
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'étudiant.
     * 
     * @param nom le nom à définir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le prénom de l'étudiant.
     * 
     * @return le prénom de l'étudiant
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom de l'étudiant.
     * 
     * @param prenom le prénom à définir
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Obtient la nationalité de l'étudiant.
     * 
     * @return la nationalité de l'étudiant
     */
    public String getNationalite() {
        return nationalite;
    }

    /**
     * Définit la nationalité de l'étudiant.
     * 
     * @param nationalite la nationalité à définir
     */
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    /**
     * Obtient la maison souhaitée par l'étudiant.
     * 
     * @return la maison souhaitée (peut être null)
     */
    public MaisonEtudiante getSouhait() {
        return souhait;
    }

    /**
     * Définit la maison souhaitée par l'étudiant.
     * 
     * @param souhait la maison souhaitée à définir (peut être null)
     */
    public void setSouhait(MaisonEtudiante souhait) {
        this.souhait = souhait;
    }
}
