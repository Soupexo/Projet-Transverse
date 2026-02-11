package Vue;

import java.awt.*;
import javax.swing.*;

/**
 * Classe abstraite représentant une vue générale pour la gestion
 * et l'affichage des étudiants dans une interface graphique Swing.
 * <p>
 * Cette vue est organisée en trois zones principales :
 * <ul>
 *   <li>Un panneau gauche contenant la liste des étudiants, dans un JScrollPane</li>
 *   <li>Un panneau droit affichant les détails de l'étudiant sélectionné</li>
 *   <li>Un panneau supérieur avec un bouton pour ajouter un nouvel étudiant, ainsi qu'un bouton accueil</li>
 * </ul>
 * <p>
 * Cette classe fournit les composants de base et leurs accès, mais
 * reste abstraite car elle ne gère pas la logique spécifique
 * de la liste ou des détails des étudiants.
 * 
 * @author Otman Benbouziane
 */
public abstract class VuePageEtudiant extends JPanel {
    
    // --------------------------
    //       ATTRIBUTS
    // --------------------------
    
    /** Panneau contenant la liste des étudiants */
    protected JPanel panelGauche;
    
    /** ScrollPane englobant la liste des étudiants pour permettre le défilement */
    protected JScrollPane scrollGauche;
    
    /** Panneau affichant les détails d'un étudiant sélectionné */
    protected JPanel panelDroit;
    
    /** Séparateur horizontal entre la liste et les détails */
    protected JSplitPane splitPane;
    
    /** Panneau supérieur contenant les boutons d'action */
    protected JPanel panelHaut;
    
    /** Bouton déclenchant l'affichage du formulaire d'ajout d'étudiant */
    protected JButton boutonEtudiant;
    
    /** Bouton permettant de revenir à la page d'accueil */
    protected JButton boutonAccueil;
    
    /** Constante représentant l'action d'afficher le formulaire d'ajout */
    public static final String ACTION_AFFICHER_FORMULAIRE = "AFFICHER_FORMULAIRE_ETUDIANT";
    
    /** Constante représentant l'action de retour à l'accueil */
    public static final String ACTION_RETOUR_ACCUEIL = "RETOUR_ACCUEIL";
    
    /** Référence à l'en-tête global de l'application (optionnelle) */
    protected VueHeaderGlobal header;
    
    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------
    
    /**
     * Constructeur par défaut qui initialise tous les composants graphiques
     * de la vue générale des étudiants, avec la disposition et la configuration
     * des panneaux et boutons.
     */
    public VuePageEtudiant() {
        // Initialisation panneau gauche (liste des étudiants)
        panelGauche = new JPanel();
        panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
        panelGauche.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // JScrollPane pour le défilement vertical uniquement
        scrollGauche = new JScrollPane(panelGauche,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Panneau droit pour afficher les détails
        panelDroit = new JPanel(new BorderLayout());

        // JSplitPane horizontal entre liste et détails
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollGauche, panelDroit);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setDividerLocation(300);

        // Panneau supérieur avec boutons
        panelHaut = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHaut.setBackground(new Color(64, 224, 208));
        
        boutonEtudiant = new JButton("➕ Ajouter un étudiant");
        boutonEtudiant.setActionCommand(ACTION_AFFICHER_FORMULAIRE);
        panelHaut.add(boutonEtudiant);
        
        boutonAccueil = new JButton("🏠 Accueil");
        boutonAccueil.setActionCommand(ACTION_RETOUR_ACCUEIL);
        panelHaut.add(boutonAccueil);

        // Disposition générale de la vue
        setLayout(new BorderLayout());
        add(panelHaut, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }
    
    // --------------------------
    //       ACCESSEURS
    // --------------------------
    
    /** @return Le panneau supérieur contenant les boutons */
    public JPanel getPanelHaut() { 
        return panelHaut; 
    }
    
    /** @return Le bouton d'ajout d'étudiant */
    public JButton getBoutonEtudiant() { 
        return boutonEtudiant; 
    }
    
    /** @return Le panneau contenant la liste des étudiants */
    public JPanel getPanelGauche() { 
        return panelGauche; 
    }
    
    /** @return Le JScrollPane englobant la liste des étudiants */
    public JScrollPane getScrollGauche() { 
        return scrollGauche; 
    }
    
    /** @return Le panneau affichant les détails de l'étudiant */
    public JPanel getPanelDroit() { 
        return panelDroit; 
    }
    
    /** @return Le JSplitPane séparant la liste et les détails */
    public JSplitPane getSplitPane() { 
        return splitPane; 
    }
    
    /** @return L'en-tête global de la vue */
    public VueHeaderGlobal getHeader() {
        return header;
    }

    /** @return Le bouton permettant de revenir à l'accueil */
    public JButton getBoutonAccueil() {
        return boutonAccueil;
    }

    /** Définit le bouton d'accueil (optionnel) */
    public void setBoutonAccueil(JButton boutonAccueil) {
        this.boutonAccueil = boutonAccueil;
    }
}
