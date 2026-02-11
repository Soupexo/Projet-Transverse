package Controleur;

import Vue.*;
import Modele.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Contrôleur gérant les actions utilisateur liées aux étudiants (affichage, inscription, désinscription).
 * 
 * Il permet notamment :
 * <ul>
 *   <li>l'affichage des détails d'un étudiant,</li>
 *   <li>la suppression d'un étudiant,</li>
 *   <li>l'ouverture du formulaire d'ajout d'étudiant,</li>
 *   <li>l'ajout effectif d'un étudiant,</li>
 *   <li>l'annulation d'une action en cours.</li>
 * </ul>
 *
 * Toutes les actions entraînent une sauvegarde automatique de l'état de la CIUP.
 *
 * @author Otman Benbouziane
 * @version 1.0
 * @see VueListeEtudiant
 * @see VueFormulaireEtudiant
 * @see Etudiant
 * @see MaisonEtudiante
 * @see GestionSauvegarde
 */
public class ControleurEtudiant implements ActionListener {

    // --------------------------
    //       ATTRIBUTS
    // --------------------------

    /** Vue principale listant les étudiants. */
    private VueListeEtudiant _vue;

    /** Formulaire de création ou modification d'un étudiant. */
    private VueFormulaireEtudiant _formulaire;

    /** Fenêtre principale utilisée pour certaines opérations (comme le retour à l'accueil). */
    private JFrame frame;

    /** Commande d'affichage du formulaire de création d'étudiant. */
    public static final String ACTION_AFFICHER_FORMULAIRE = "AFFICHER_FORMULAIRE_ETUDIANT";

    /** Commande d'ajout d'un nouvel étudiant à une maison. */
    public static final String ACTION_AJOUTER_ETUDIANT = "AJOUTER_ETUDIANT";

    /** Commande d'annulation de l'ajout ou de la modification d'un étudiant. */
    public static final String ACTION_ANNULER = "ANNULER";

    /** Commande d'affichage des détails d'un étudiant. */
    public static final String ACTION_AFFICHER_ETUDIANT = "AFFICHER_ETUDIANT";

    /** Commande de suppression d'un étudiant d'une maison. */
    public static final String ACTION_SUPPRIMER_ETUDIANT = "SUPPRIMER_ETUDIANT";

    /** Commande permettant de revenir à l'accueil principal. */
    public static final String ACTION_RETOUR_ACCUEIL = "RETOUR_ACCUEIL";

    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------

    /**
     * Construit un contrôleur pour la gestion des étudiants.
     * Associe les actions des boutons de la vue et du formulaire à ce contrôleur.
     *
     * @param vue la vue listant les étudiants
     * @param formulaire la fenêtre de formulaire pour saisir un nouvel étudiant
     * @param frame la fenêtre principale utilisée pour certaines actions comme le retour à l'accueil
     */
    public ControleurEtudiant(VueListeEtudiant vue, VueFormulaireEtudiant formulaire, JFrame frame) {
        _vue = vue;
        _formulaire = formulaire;
        this.frame = frame;

        // Attache les écouteurs aux différents boutons
        _vue.getBoutonEtudiant().addActionListener(this);
        _formulaire.getBoutonValider().addActionListener(this);
        _formulaire.getBoutonAnnuler().addActionListener(this);
        _vue.getBoutonAccueil().addActionListener(this);

        // Rafraîchit l'affichage des étudiants
        _vue.rafraichirPanelGauche(this);
    }

    // --------------------------
    //       METHODES
    // --------------------------

    /**
     * Gère les différentes actions utilisateur selon la commande reçue :
     * <ul>
     *   <li>{@link #ACTION_AFFICHER_ETUDIANT} : affiche les détails d’un étudiant</li>
     *   <li>{@link #ACTION_SUPPRIMER_ETUDIANT} : supprime l’étudiant de sa maison</li>
     *   <li>{@link #ACTION_AFFICHER_FORMULAIRE} : affiche le formulaire d’ajout</li>
     *   <li>{@link #ACTION_AJOUTER_ETUDIANT} : ajoute l’étudiant à la maison correspondante</li>
     *   <li>{@link #ACTION_ANNULER} : ferme le formulaire sans enregistrer</li>
     *   <li>{@link #ACTION_RETOUR_ACCUEIL} : revient à la page principale de l'application</li>
     * </ul>
     *
     * Une sauvegarde est automatiquement effectuée après chaque action.
     *
     * @param e l'événement déclenché par l'utilisateur
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bouton = (JButton) e.getSource();
        Etudiant etudiant = (Etudiant) bouton.getClientProperty("etudiant");
        MaisonEtudiante maison = (MaisonEtudiante) bouton.getClientProperty("maison");

        switch (e.getActionCommand()) {
            case ACTION_AFFICHER_ETUDIANT:
                if (etudiant != null && maison != null) {
                    _vue.afficherEtudiant(etudiant, maison, this);
                }
                break;

            case ACTION_AJOUTER_ETUDIANT:
                _formulaire.recupererDonnees();
                _vue.rafraichirPanelGauche(this);
                break;

            case ACTION_ANNULER:
                _formulaire.fermer();
                break;

            case ACTION_SUPPRIMER_ETUDIANT:
                if (etudiant != null && maison != null) {
                    maison.retirerEtudiant(etudiant);
                    _vue.rafraichirPanelGauche(this);
                }
                break;

            case ACTION_AFFICHER_FORMULAIRE:
                _formulaire.afficher();
                break;

            case ACTION_RETOUR_ACCUEIL:
                frame.dispose();
                VueListeMaisons.lanceApplication();
                break;

            default:
                break;
        }

        // Sauvegarde l’état actuel de la CIUP après chaque action
        GestionSauvegarde.sauvegarder(_vue.get_ciup());
    }
}
