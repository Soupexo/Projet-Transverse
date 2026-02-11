package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 * Classe abstraite représentant une vue générale pour l'affichage et la gestion des soirées.
 * Elle contient deux panneaux principaux : à gauche la liste des soirées, à droite les détails.
 * Un panneau supérieur contient un bouton pour ajouter une nouvelle soirée.
 */
public abstract class VuePageSoiree extends JPanel {
	
	 // --------------------------
    //       ATTRIBUT
    // --------------------------

    /**
     * Panneau contenant la liste des soirées (gauche).
     */
    protected JPanel panelGauche;

    /**
     * ScrollPane enveloppant le panneau gauche.
     */
    protected JScrollPane scrollGauche;

    /**
     * Panneau affichant les détails de la soirée sélectionnée (droite).
     */
    protected JPanel panelDroit;

    /**
     * Composant divisant l'espace entre le panneau gauche et le panneau droit.
     */
    protected JSplitPane splitPane;

    /**
     * Panneau en haut contenant le bouton d'ajout de soirée.
     */
    protected JPanel panelHaut;

    /**
     * Bouton permettant d'ouvrir le formulaire de création de soirée.
     */
    protected JButton boutonSoiree;

    /**
     * Constante définissant l'action associée à l'ouverture du formulaire.
     */
    public static final String ACTION_AFFICHER_FORMULAIRE = "AFFICHER_FORMULAIRE";
    
    public static final String ACTION_RETOUR_ACCUEIL = "RETOUR_ACCUEIL";
    
    protected JButton boutonAccueil;
    
    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------

    /**
     * Constructeur par défaut initialisant tous les composants de la vue.
     */
    public VuePageSoiree() {

        // === PANEL GAUCHE ===
        panelGauche = new JPanel();
        panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
        panelGauche.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        scrollGauche = new JScrollPane(panelGauche,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // === PANEL DROIT ===
        panelDroit = new JPanel(new BorderLayout());

        // === SPLITPANE ===
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollGauche, panelDroit);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setDividerLocation(300);

        // === PANEL HAUT ===
        panelHaut = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHaut.setBackground(new Color(64, 224, 208)); // turquoise
        boutonSoiree = new JButton("➕ Ajouter une soirée");
        boutonSoiree.setActionCommand(ACTION_AFFICHER_FORMULAIRE);
        panelHaut.add(boutonSoiree);
        
        boutonAccueil = new JButton("🏠 Accueil");
        boutonAccueil.setActionCommand("RETOUR_ACCUEIL");
        panelHaut.add(boutonAccueil);

        // === AJOUT À LA VUE ===
        setLayout(new BorderLayout());
        add(panelHaut, BorderLayout.NORTH);    // tout en haut
        add(splitPane, BorderLayout.CENTER);   // contenu principal
    }
    
    // --------------------------
    //       ACCESSEUR
    // --------------------------

    /**
     * @return le panneau du haut contenant le bouton d'ajout
     */
    public JPanel getPanelHaut() {
        return panelHaut;
    }

    /**
     * Définit le panneau du haut.
     * @param panelHaut le panneau supérieur
     */
    public void setPanelHaut(JPanel panelHaut) {
        this.panelHaut = panelHaut;
    }

    /**
     * @return le bouton d'ajout de soirée
     */
    public JButton getBoutonSoiree() {
        return boutonSoiree;
    }

    /**
     * Définit le bouton d'ajout de soirée.
     * @param boutonSoiree le bouton à définir
     */
    public void setBoutonSoiree(JButton boutonSoiree) {
        this.boutonSoiree = boutonSoiree;
    }

    /**
     * @return le panneau gauche contenant les soirées
     */
    public JPanel getPanelGauche() {
        return panelGauche;
    }

    /**
     * Définit le panneau gauche.
     * @param panelGauche le panneau gauche
     */
    public void setPanelGauche(JPanel panelGauche) {
        this.panelGauche = panelGauche;
    }

    /**
     * @return la scrollPane enveloppant le panneau gauche
     */
    public JScrollPane getScrollGauche() {
        return scrollGauche;
    }

    /**
     * Définit la scrollPane du panneau gauche.
     * @param scrollGauche la scrollPane à définir
     */
    public void setScrollGauche(JScrollPane scrollGauche) {
        this.scrollGauche = scrollGauche;
    }

    /**
     * @return le panneau droit contenant les détails
     */
    public JPanel getPanelDroit() {
        return panelDroit;
    }

    /**
     * Définit le panneau droit.
     * @param panelDroit le panneau droit
     */
    public void setPanelDroit(JPanel panelDroit) {
        this.panelDroit = panelDroit;
    }

    /**
     * @return le composant de division entre les deux panneaux
     */
    public JSplitPane getSplitPane() {
        return splitPane;
    }

    /**
     * Définit le JSplitPane de la vue.
     * @param splitPane le séparateur à définir
     */
    public void setSplitPane(JSplitPane splitPane) {
        this.splitPane = splitPane;
    }

	public JButton getBoutonAccueil() {
		return boutonAccueil;
	}

	public void setBoutonAccueil(JButton boutonAccueil) {
		this.boutonAccueil = boutonAccueil;
	}
}
