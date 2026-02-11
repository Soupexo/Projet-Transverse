package Modele;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * Représente une soirée organisée au sein d'une maison étudiante.
 * Contient les informations sur la soirée ainsi que la liste des étudiants inscrits.
 * 
 * Cette classe est sérialisable afin de permettre la sauvegarde des soirées dans un fichier.
 * 
 * @author Yanis Marques 
 * @version 1.0
 */
public class Soiree implements Serializable {

    /** Format de la date (année-mois-jour) pour l'affichage */
    private static transient DateTimeFormatter FORMATTER_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Format de l'heure (heure:minute:seconde) pour l'affichage */
    private static transient DateTimeFormatter FORMATTER_2 = DateTimeFormatter.ofPattern("HH:mm:ss");

    /** Nom de la soirée */
    private String nomSoiree;

    /** Description de la soirée */
    private String descriptionSoiree;

    /** Date et heure de la soirée */
    private LocalDateTime dateSoiree;

    /** Liste des étudiants inscrits à la soirée */
    private List<Etudiant> listeInscrit;

    /** Version de sérialisation */
    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------
    // Constructeur
    // ----------------------------------------------------------------

    /**
     * Construit une nouvelle instance de Soiree avec les informations données.
     *
     * @param nom le nom de la soirée
     * @param description la description de la soirée
     * @param date la date et l'heure de la soirée
     */
    public Soiree(String nom, String description, LocalDateTime date) {
        this.nomSoiree = nom;
        this.descriptionSoiree = description;
        this.dateSoiree = date;
        this.listeInscrit = new ArrayList<>();
    }

    // ----------------------------------------------------------------
    // Méthodes
    // ----------------------------------------------------------------

    /**
     * Inscrit un étudiant à la soirée.
     *@author Otman Benbouziane
     * @param etudiant l'étudiant à inscrire
     */
    public void inscrireEtudiantSoiree(Etudiant etudiant) {
        listeInscrit.add(etudiant);
    }

    // ----------------------------------------------------------------
    // Accesseurs et Modificateurs
    // ----------------------------------------------------------------

    /**
     * Retourne le nom de la soirée.
     * 
     * @return le nom de la soirée
     */
    public String getNomSoiree() {
        return nomSoiree;
    }

    /**
     * Modifie le nom de la soirée.
     * 
     * @param nom le nouveau nom
     */
    public void setNomSoiree(String nom) {
        this.nomSoiree = nom;
    }

    /**
     * Retourne la description de la soirée.
     * 
     * @return la description
     */
    public String getDescriptionSoiree() {
        return descriptionSoiree;
    }

    /**
     * Modifie la description de la soirée.
     * 
     * @param description la nouvelle description
     */
    public void setDescriptionSoiree(String description) {
        this.descriptionSoiree = description;
    }

    /**
     * Retourne la date et l'heure de la soirée.
     * 
     * @return la date de la soirée
     */
    public LocalDateTime getDateSoiree() {
        return dateSoiree;
    }

    /**
     * Modifie la date et l'heure de la soirée.
     * 
     * @param date la nouvelle date
     */
    public void setDateSoiree(LocalDateTime date) {
        this.dateSoiree = date;
    }

    /**
     * Retourne le nombre d'étudiants inscrits à la soirée sous forme de chaîne.
     * 
     * @return le nombre d'inscrits
     */
    public String getNombreMembre() {
        return " " + this.listeInscrit.size();
    }

    /**
     * Modifie la liste des étudiants inscrits à la soirée.
     * @author Otman Benbouziane
     * @param listeInscrit nouvelle liste des inscrits
     */
    public void setListeInscrit(List<Etudiant> listeInscrit) {
        this.listeInscrit = listeInscrit;
    }

    /**
     * Retourne la liste des étudiants inscrits.
     * 
     * @return liste des inscrits
     */
    public List<Etudiant> getListeInscrit() {
        return listeInscrit;
    }

    /**
     * Retourne le formateur de date utilisé pour l'affichage (yyyy-MM-dd).
     * 
     * @return le formateur de date
     */
    public DateTimeFormatter getFormatter1() {
        return FORMATTER_1;
    }

    /**
     * Retourne le formateur de temps utilisé pour l'affichage (HH:mm:ss).
     * 
     * @return le formateur d'heure
     */
    public DateTimeFormatter getFormatter2() {
        return FORMATTER_2;
    }

    // ----------------------------------------------------------------
    // Représentation textuelle
    // ----------------------------------------------------------------

    /**
     * Retourne une représentation textuelle de la soirée.
     * 
     * @return une chaîne décrivant la soirée
     */
    @Override
    public String toString() {
        return "Soiree [nomSoiree=" + nomSoiree + 
               ", descriptionSoiree=" + descriptionSoiree + 
               ", dateSoiree=" + dateSoiree.format(FORMATTER_1) + "]";
    }
}