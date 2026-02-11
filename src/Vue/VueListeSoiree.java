package Vue;

import Modele.*;
import java.util.*;
import javax.swing.*;
import Controleur.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe {@code VueListeSoiree} représente une interface graphique Swing
 * affichant la liste des soirées organisées dans les maisons étudiantes de la CIUP.
 * Cette vue hérite de {@code VuePageSoiree} et gère :
 * <ul>
 *   <li>L'affichage dynamique des boutons correspondant aux soirées dans le panneau gauche</li>
 *   <li>L'affichage des détails d'une soirée sélectionnée dans le panneau droit</li>
 *   <li>La possibilité d'ajouter et supprimer des soirées via des boutons</li>
 * </ul>
 * Elle permet aussi de rafraîchir dynamiquement l'affichage après modification des données.
 * 
 * @author Otman Benbouziane
 */
public class VueListeSoiree extends VuePageSoiree {
	
	  // --------------------------
    //       ATTRIBUT
    // --------------------------

    /** Commande d'action pour afficher une soirée */
    public static final String ACTION_AFFICHER_SOIREE = "AFFICHER_SOIREE";

    /** Commande d'action pour supprimer une soirée */
    public static final String ACTION_SUPPRIMER_SOIREE = "SUPPRIMER_SOIREE";

    /** Référence à l'objet Ciup contenant les données de toutes les maisons étudiantes */
    private Ciup _ciup;
    
    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------

    /**
     * Constructeur initialisant la vue avec la liste des soirées provenant de la CIUP.
     * 
     * Il crée des boutons pour chaque soirée trouvée dans les agendas des maisons étudiantes
     * et les ajoute dans le panneau gauche.
     * 
     * @param ciup L'objet {@code Ciup} contenant les maisons étudiantes et leurs soirées.
     */
    public VueListeSoiree(Ciup ciup) {
        _ciup = ciup;
        // Pour chaque maison, on crée un bouton pour chaque soirée de son agenda
        for (MaisonEtudiante maison : _ciup.getListeMaisonsEtudiantes()) {
            for (Soiree soiree : maison.getAgenda()) {
                JButton bouton = new JButton(soiree.getNomSoiree());
                bouton.setActionCommand(ACTION_AFFICHER_SOIREE);
                bouton.putClientProperty("soiree", soiree);
                bouton.putClientProperty("maison", maison);
                panelGauche.add(bouton);
            }
        }
    }
    
    // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Rafraîchit le panneau gauche contenant la liste des soirées.
     * <p>
     * Cette méthode supprime tous les boutons existants et en recrée
     * un pour chaque soirée actuellement présente dans la CIUP.
     * Chaque bouton est associé à l'écouteur fourni.
     * 
     * @param listener L'écouteur à associer aux boutons de soirées.
     */
    public void rafraichirPanelGauche(ActionListener listener) {
        panelGauche.removeAll();
        panelDroit.removeAll();

        for (MaisonEtudiante maison : _ciup.getListeMaisonsEtudiantes()) {
            for (Soiree soiree : maison.getAgenda()) {
                JButton bouton = new JButton(soiree.getNomSoiree());
                bouton.setActionCommand(ACTION_AFFICHER_SOIREE);
                bouton.putClientProperty("soiree", soiree);
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
     * Affiche les détails d'une soirée sélectionnée dans le panneau droit.
     * <p>
     * Les informations affichées incluent le nom de la maison, la description,
     * le nombre d'inscrits, la date et l'heure de la soirée.
     * Un bouton pour supprimer la soirée est également ajouté.
     * 
     * @param soiree  La soirée à afficher.
     * @param maison  La maison organisatrice de la soirée.
     * @param listener L'écouteur associé au bouton de suppression.
     */
    public void afficherSoiree(Soiree soiree, MaisonEtudiante maison, ActionListener listener) {
        panelDroit.removeAll();
        panelDroit.setLayout(new BoxLayout(panelDroit, BoxLayout.Y_AXIS));
        panelDroit.setBackground(Color.WHITE);
        panelDroit.setBorder(BorderFactory.createLineBorder(Color.decode("#0ca779")));

        // Panneau contenant le bouton supprimer
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.WHITE);

        JButton boutonFermer = new JButton("✖"); // Croix Unicode
        boutonFermer.setActionCommand(ACTION_SUPPRIMER_SOIREE);
        boutonFermer.putClientProperty("soiree", soiree);
        boutonFermer.putClientProperty("maison", maison);
        boutonFermer.setForeground(Color.WHITE);
        boutonFermer.setBackground(Color.RED);
        boutonFermer.addActionListener(listener);
        boutonFermer.setFocusPainted(false);
        boutonFermer.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        topPanel.add(boutonFermer);
        panelDroit.add(topPanel);

        // Affichage du nom de la maison
        JTextArea areaMaison = new JTextArea("Maison : " + maison.getNom());
        areaMaison.setForeground(Color.RED);
        areaMaison.setBackground(Color.WHITE);
        areaMaison.setFont(new Font("Arial Black", Font.BOLD, 21));
        areaMaison.setEditable(false);
        areaMaison.setBorder(null);
        panelDroit.add(areaMaison);

        // Affichage de la description
        JTextArea areaDescription = new JTextArea("Description : " + soiree.getDescriptionSoiree());
        areaDescription.setForeground(new Color(0, 102, 204));
        areaDescription.setBackground(Color.WHITE);
        areaDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        areaDescription.setLineWrap(true);
        areaDescription.setWrapStyleWord(true);
        areaDescription.setEditable(false);
        areaDescription.setBorder(null);
        panelDroit.add(areaDescription);

        // Nombre de membres inscrits
        JTextArea areaMembres = new JTextArea(soiree.getNombreMembre() + " membres inscrits.");
        areaMembres.setForeground(new Color(0, 153, 153));
        areaMembres.setBackground(Color.WHITE);
        areaMembres.setFont(new Font("Arial", Font.ITALIC, 12));
        areaMembres.setEditable(false);
        areaMembres.setBorder(null);
        panelDroit.add(areaMembres);

        // Date de la soirée
        JTextArea areaDate = new JTextArea("Date : 📅 " + soiree.getDateSoiree().format(soiree.getFormatter1()));
        areaDate.setEditable(false);
        areaDate.setBackground(Color.WHITE);
        areaDate.setBorder(null);
        panelDroit.add(areaDate);

        // Heure de la soirée
        JTextArea areaHeure = new JTextArea("Heure : 🕒 " + soiree.getDateSoiree().format(soiree.getFormatter2()));
        areaHeure.setEditable(false);
        areaHeure.setBackground(Color.WHITE);
        areaHeure.setBorder(null);
        panelDroit.add(areaHeure);

        panelDroit.revalidate();
        panelDroit.repaint();
    }
    
    // --------------------------
    //       ACCESSEUR
    // --------------------------

    /**
     * Retourne l'objet {@code Ciup} associé à cette vue.
     * 
     * @return L'instance de {@code Ciup}.
     */
    public Ciup get_ciup() {
        return _ciup;
    }

    /**
     * Modifie l'objet {@code Ciup} associé à cette vue.
     * 
     * @param _ciup La nouvelle instance de {@code Ciup}.
     */
    public void set_ciup(Ciup _ciup) {
        this._ciup = _ciup;
    }
    
    // --------------------------
    //       METHODE AFFICHAGE
    // --------------------------

    /**
     * Méthode statique pour afficher cette vue dans une JFrame donnée.
     * <p>
     * Elle charge la CIUP via {@code GestionSauvegarde} ou la crée via {@code Factory}
     * si aucun fichier de sauvegarde n'est trouvé. Elle initialise ensuite
     * le formulaire d'ajout et le contrôleur associés.
     * 
     * @param frame La fenêtre JFrame dans laquelle afficher la vue.
     */
    public static void afficherVue(JFrame frame) {
        VueListeSoiree panel;

        if (GestionSauvegarde.charger() == null) {
            Factory fac = new Factory();
            panel = new VueListeSoiree(fac.constructionCiup());
        } else {
            Ciup ciup = GestionSauvegarde.charger();
            panel = new VueListeSoiree(ciup);
        }

        vueFormulaireSoirees formulaire = new vueFormulaireSoirees(frame, panel);
        ControleurSoiree controleurSoiree = new ControleurSoiree(panel, formulaire, frame);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
