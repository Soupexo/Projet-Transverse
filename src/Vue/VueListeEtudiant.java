package Vue;

import Modele.*;
import java.util.*;
import javax.swing.*;
import Controleur.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Vue permettant d'afficher dynamiquement la liste des étudiants
 * présents dans chaque maison de la Cité Internationale Universitaire de Paris (CIUP).
 * <p>
 * La vue est divisée en deux panneaux :
 * <ul>
 *   <li>Le panneau gauche affiche des boutons représentant chaque étudiant (résidents et en liste d'attente).</li>
 *   <li>Le panneau droit affiche les détails de l'étudiant sélectionné et permet sa suppression.</li>
 * </ul>
 * Un bouton permet également d'ajouter un nouvel étudiant via un formulaire (implémenté dans {@link VueFormulaireEtudiant}).
 * 
 * Cette classe fait partie de l'architecture MVC où le contrôleur associé est {@link ControleurEtudiant}.
 * 
 * @author Otman Benbouziane
 * @see ControleurEtudiant
 * @see VueFormulaireEtudiant
 * @see Etudiant
 * @see MaisonEtudiante
 * @see Ciup
 */
public class VueListeEtudiant extends VuePageEtudiant {
    
    // ---------------------------------
    //           CONSTANTES
    // ---------------------------------

    /** Action command pour afficher les détails d'un étudiant */
    public static final String ACTION_AFFICHER_ETUDIANT = "AFFICHER_ETUDIANT";
    
    /** Action command pour supprimer un étudiant */
    public static final String ACTION_SUPPRIMER_ETUDIANT = "SUPPRIMER_ETUDIANT";

    // ---------------------------------
    //           ATTRIBUTS
    // ---------------------------------

    /** Modèle principal représentant la Cité Internationale Universitaire de Paris */
    private Ciup _ciup;
    
    // ---------------------------------
    //          CONSTRUCTEUR
    // ---------------------------------

    /**
     * Initialise la vue avec la référence au modèle {@link Ciup}.
     * 
     * @param ciup le modèle représentant la CIUP, contenant les maisons et étudiants
     */
    public VueListeEtudiant(Ciup ciup) {
        _ciup = ciup;
    }
    
    // ---------------------------------
    //           METHODES
    // ---------------------------------

    /**
     * Recharge dynamiquement le panneau gauche avec les boutons représentant
     * tous les étudiants (résidents et en liste d'attente) de chaque maison.
     * <p>
     * Chaque bouton est associé à un {@link ActionListener} pour gérer la sélection.
     * </p>
     * 
     * @param listener écouteur d'événements à associer aux boutons d'étudiant
     */
    public void rafraichirPanelGauche(ActionListener listener) {
        panelGauche.removeAll();
        panelDroit.removeAll();

        for (MaisonEtudiante maison : _ciup.getListeMaisonsEtudiantes()) {
            for (Etudiant etu : maison.getListeEtudiante()) {
                JButton bouton = new JButton(etu.getPrenom() + " " + etu.getNom());
                bouton.setActionCommand(ACTION_AFFICHER_ETUDIANT);
                bouton.putClientProperty("etudiant", etu);
                bouton.putClientProperty("maison", maison);
                bouton.addActionListener(listener);
                panelGauche.add(bouton);
            }
            for (Etudiant etu : maison.getlisteAttente()) {
                JButton bouton = new JButton(etu.getPrenom() + " " + etu.getNom());
                bouton.setActionCommand(ACTION_AFFICHER_ETUDIANT);
                bouton.putClientProperty("etudiant", etu);
                bouton.putClientProperty("maison", maison);
                bouton.addActionListener(listener);
                panelGauche.add(bouton);
            }
        }

        panelGauche.revalidate();
        panelGauche.repaint();
        panelDroit.revalidate();
        panelDroit.repaint();
    }

    /**
     * Affiche les informations détaillées d'un étudiant dans le panneau droit,
     * avec un bouton pour supprimer cet étudiant.
     * <p>
     * Le bouton de suppression est associé à un {@link ActionListener} spécifique.
     * </p>
     * 
     * @param etu l'étudiant sélectionné à afficher
     * @param maison la maison d'appartenance de l'étudiant
     * @param listener écouteur pour gérer la suppression
     */
    public void afficherEtudiant(Etudiant etu, MaisonEtudiante maison, ActionListener listener) {
        panelDroit.removeAll();
        panelDroit.setLayout(new BoxLayout(panelDroit, BoxLayout.Y_AXIS));
        panelDroit.setBackground(Color.WHITE);
        panelDroit.setBorder(BorderFactory.createLineBorder(new Color(0, 167, 121)));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.WHITE);

        JButton boutonSupprimer = new JButton("✖");
        boutonSupprimer.setActionCommand(ACTION_SUPPRIMER_ETUDIANT);
        boutonSupprimer.putClientProperty("etudiant", etu);
        boutonSupprimer.putClientProperty("maison", maison);
        boutonSupprimer.setForeground(Color.WHITE);
        boutonSupprimer.setBackground(Color.RED);
        boutonSupprimer.addActionListener(listener);
        boutonSupprimer.setFocusPainted(false);
        boutonSupprimer.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        topPanel.add(boutonSupprimer);

        JTextArea nomMaison;
        if (etu.getMaisonResidence() != null && maison.getListeEtudiante().contains(etu)) {
            nomMaison = new JTextArea("Maison : " + maison.getNom());
            nomMaison.setForeground(Color.RED);
            nomMaison.setBackground(Color.WHITE);
            nomMaison.setFont(new Font("Arial Black", Font.BOLD, 21));
            nomMaison.setEditable(false);
            nomMaison.setBorder(null);
        } else {
            nomMaison = new JTextArea("Maison : " + maison.getNom() + " (Liste d'attente)");
            nomMaison.setForeground(Color.RED);
            nomMaison.setBackground(Color.WHITE);
            nomMaison.setFont(new Font("Arial Black", Font.BOLD, 21));
            nomMaison.setEditable(false);
            nomMaison.setBorder(null);
        }

        JTextArea nomEtudiant = new JTextArea("Nom : " + etu.getPrenom() + " " + etu.getNom());
        nomEtudiant.setFont(new Font("Arial", Font.BOLD, 18));
        nomEtudiant.setEditable(false);
        nomEtudiant.setBackground(Color.WHITE);
        nomEtudiant.setBorder(null);

        JTextArea nationalite = new JTextArea("Nationalité : " + etu.getNationalite());
        nationalite.setFont(new Font("Arial", Font.PLAIN, 16));
        nationalite.setEditable(false);
        nationalite.setBackground(Color.WHITE);
        nationalite.setBorder(null);

        panelDroit.add(topPanel);
        panelDroit.add(nomMaison);
        panelDroit.add(nomEtudiant);
        panelDroit.add(nationalite);

        panelDroit.revalidate();
        panelDroit.repaint();
    }
    
    // ---------------------------------
    //          ACCESSEURS
    // ---------------------------------

    /**
     * Accesseur pour le modèle {@link Ciup}.
     * 
     * @return l'objet {@link Ciup} associé à cette vue
     */
    public Ciup get_ciup() {
        return _ciup;
    }

    /**
     * Mutateur pour le modèle {@link Ciup}.
     * 
     * @param _ciup le nouvel objet {@link Ciup} à associer
     */
    public void set_ciup(Ciup _ciup) {
        this._ciup = _ciup;
    }

    /**
     * Méthode utilitaire pour afficher cette vue dans une {@link JFrame} donnée.
     * 
     * @param frame la fenêtre Swing dans laquelle afficher la vue
     */
    public void afficherVue(JFrame frame) {
        VueListeEtudiant panel;

        Ciup ciupCharge = GestionSauvegarde.charger();
        if (ciupCharge == null) {
            ciupCharge = Factory.constructionCiup();
        }

        panel = new VueListeEtudiant(ciupCharge);
        VueFormulaireEtudiant formulaire = new VueFormulaireEtudiant(frame, panel);
        ControleurEtudiant controleur = new ControleurEtudiant(panel, formulaire, frame);
        panel.rafraichirPanelGauche(controleur);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
