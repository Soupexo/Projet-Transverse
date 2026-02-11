package Controleur;

import Modele.*;
import Vue.*;
import java.awt.event.*;
import java.awt.Component;
import javax.swing.*;

/**
 * Contrôleur chargé de gérer les interactions liées aux soirées :
 * affichage, ajout, suppression, annulation et navigation.
 * <p>
 * Il relie les vues {@link VueListeSoiree} et {@link vueFormulaireSoirees}
 * en écoutant les actions des boutons, et utilise les propriétés client
 * des boutons pour associer les données (soirées, maisons) aux actions.
 * </p>
 * 
 * <p>
 * Ce contrôleur s'occupe également de la gestion de la sauvegarde automatique
 * après chaque action modifiant les données.
 * </p>
 * 
 * @author Otman Benbouziane
 * @version 1.0
 */
public class ControleurSoiree implements ActionListener {
	
	  // --------------------------
    //       ATTRIBUT
    // --------------------------

    /**
     * Vue principale affichant les soirées et les boutons associés.
     */
    private VueListeSoiree _vueListesSoirees;

    /**
     * Vue du formulaire d’ajout/modification d’une soirée.
     */
    private vueFormulaireSoirees _vueFormulaireSoirees;

    /**
     * Commande pour afficher le formulaire d’ajout de soirée.
     */
    public static final String ACTION_AFFICHER_FORMULAIRE = "AFFICHER_FORMULAIRE";

    /**
     * Commande pour afficher les détails d'une soirée.
     */
    public static final String ACTION_AFFICHER_SOIREE = "AFFICHER_SOIREE";

    /**
     * Commande pour ajouter une nouvelle soirée.
     */
    public static final String ACTION_AJOUTER_SOIREE = "AJOUTER_SOIREE";

    /**
     * Commande pour supprimer une soirée.
     */
    public static final String ACTION_SUPPRIMER_SOIREE = "SUPPRIMER_SOIREE";

    /**
     * Commande pour annuler une action (fermer formulaire).
     */
    public static final String ACTION_ANNULER = "ANNULER";
    
    /**
     * Commande pour revenir à l'écran d'accueil.
     */
    public static final String ACTION_RETOUR_ACCUEIL = "RETOUR_ACCUEIL";

    /**
     * La fenêtre JFrame principale dans laquelle les vues sont affichées.
     */
    private JFrame frame;
    
    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------

    /**
     * Constructeur du contrôleur.
     * Il attache les écouteurs d'action aux composants nécessaires
     * dans les vues fournies.
     * 
     * @param vue Vue principale des listes de soirées
     * @param vueFormulaire Vue du formulaire de création/modification de soirée
     * @param frame Fenêtre principale JFrame parent des vues
     */
    public ControleurSoiree(VueListeSoiree vue, vueFormulaireSoirees vueFormulaire, JFrame frame) {
        this._vueListesSoirees = vue;
        this._vueFormulaireSoirees = vueFormulaire;
        this.frame = frame;

        this._vueListesSoirees.getBoutonAccueil().addActionListener(this);

        // Ajoute un ActionListener à tous les boutons du panneau gauche
        for (Component comp : this._vueListesSoirees.getPanelGauche().getComponents()) {
            if (comp instanceof JButton) {
                ((JButton) comp).addActionListener(this);
            }
        }

        // Réinitialise l'écouteur du bouton "Ajouter une soirée" pour éviter doublons
        for (ActionListener al : this._vueListesSoirees.getBoutonSoiree().getActionListeners()) {
            this._vueListesSoirees.getBoutonSoiree().removeActionListener(al);
        }
        this._vueListesSoirees.getBoutonSoiree().addActionListener(this);

        // Ajoute les écouteurs aux boutons du formulaire
        _vueFormulaireSoirees.getBoutonValider().addActionListener(this);
        _vueFormulaireSoirees.getBoutonAnnuler().addActionListener(this);
    }
    
    // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Gère les différentes actions déclenchées par les boutons de la vue
     * selon la commande d'action associée.
     * 
     * @param e L'événement ActionEvent déclenché
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bouton = (JButton) e.getSource();

        Soiree soiree = (Soiree) bouton.getClientProperty("soiree");
        MaisonEtudiante maison = (MaisonEtudiante) bouton.getClientProperty("maison");

        switch (e.getActionCommand()) {
            case ACTION_AFFICHER_SOIREE:
                if (soiree != null && maison != null) {
                    _vueListesSoirees.afficherSoiree(soiree, maison, this);
                }
                break;

            case ACTION_AFFICHER_FORMULAIRE:
                _vueFormulaireSoirees.afficher();
                break;

            case ACTION_AJOUTER_SOIREE:
                _vueFormulaireSoirees.recupererDonnees();
                _vueListesSoirees.rafraichirPanelGauche(this);
                break;

            case ACTION_ANNULER:
                _vueFormulaireSoirees.fermer();
                break;

            case ACTION_SUPPRIMER_SOIREE:
                if (soiree != null && maison != null) {
                    maison.retirerSoiree(soiree);
                    _vueListesSoirees.rafraichirPanelGauche(this);
                }
                break;

            case ACTION_RETOUR_ACCUEIL:
                frame.dispose();
                VueListeMaisons.lanceApplication();
                break;

            default:
                break;
        }

        // Sauvegarde automatique après chaque modification
        GestionSauvegarde.sauvegarder(_vueListesSoirees.get_ciup());
    }
}
