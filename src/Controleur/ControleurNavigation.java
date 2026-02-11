package Controleur;

import Modele.*;
import Vue.*;
import Controleur.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Contrôleur gérant la navigation entre différentes vues principales de l'application.
 * 
 * Cette classe implémente {@link ActionListener} pour écouter les actions sur les boutons
 * de navigation présents dans l'en-tête global de l'application.
 * 
 * Elle permet d'afficher la vue des étudiants, la vue des soirées, ou un retour à l'accueil.
 * 
 * @author Otman Benbouziane
 * @version 1.0
 * 
 * @see VueListeEtudiant
 * @see VueListeSoiree
 * @see VueHeaderGlobal
 * @see VueListeMaisons
 */
public class ControleurNavigation implements ActionListener {
	
	 // --------------------------
    //       ATTRIBUT
    // --------------------------

    /**
     * Commande d'action pour afficher la vue des étudiants.
     */
    public static final String ACTION_AFFICHER_VUE_ETUDIANT = "AFFICHER_VUE_ETUDIANT";

    /**
     * Commande d'action pour afficher la vue des soirées.
     */
    public static final String ACTION_AFFICHER_VUE_SOIREE = "AFFICHER_VUE_SOIREE";


    /**
     * Vue listant les étudiants.
     */
    private VueListeEtudiant vueEtudiant;

    /**
     * Vue listant les soirées.
     */
    private VueListeSoiree vueSoiree;

    /**
     * En-tête global contenant les boutons de navigation.
     */
    private VueHeaderGlobal vueHeader;

    /**
     * Vue listant les maisons étudiantes.
     */
    private VueListeMaisons vueMaison;

    
    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------
    /**
     * Constructeur du contrôleur de navigation.
     * 
     * Associe les boutons de l'en-tête global à ce contrôleur comme écouteur d'actions.
     * 
     * @param vueEtudiant la vue des étudiants
     * @param vueSoiree la vue des soirées
     * @param vueHeader l'en-tête global
     * @param vueMaison la vue des maisons étudiantes (pour obtenir la fenêtre principale)
     */
    public ControleurNavigation(VueListeEtudiant vueEtudiant, VueListeSoiree vueSoiree, VueHeaderGlobal vueHeader, VueListeMaisons vueMaison) {
        this.vueEtudiant = vueEtudiant;
        this.vueSoiree = vueSoiree;
        this.vueHeader = vueHeader;
        this.vueMaison = vueMaison;

        this.vueHeader.getBoutonHeaderEtudiants().addActionListener(this);
        this.vueHeader.getBoutonHeaderSoirees().addActionListener(this);
    }
    
    // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Gère les actions déclenchées par les boutons de navigation.
     * 
     * Affiche la vue correspondante en fonction de la commande d'action reçue.
     * 
     * @param e l'événement d'action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bouton = (JButton) e.getSource();
        switch (e.getActionCommand()) {
            case ACTION_AFFICHER_VUE_ETUDIANT:
                vueEtudiant.afficherVue(this.vueMaison.getFrame());
                break;

            case ACTION_AFFICHER_VUE_SOIREE:
                vueSoiree.afficherVue(this.vueMaison.getFrame());
                break;

            default:
                break;
        }
    }
}
