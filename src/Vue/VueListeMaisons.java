package Vue;

import Modele.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Controleur.*;

/**
 * Vue principale affichant la liste des maisons étudiantes et la maison internationale de la CIUP.
 * Utilise une grille 2x2 pour afficher jusqu'à 4 maisons étudiantes,
 * et un panneau séparé pour la maison internationale.
 * Hérite de VueHeaderGlobal pour gérer l'en-tête global de l'application.
 * @author Lucas Pausé-Chapuis
 */
public class VueListeMaisons extends VueHeaderGlobal {
	
	 // --------------------------
    //       ATTRIBUTS
    // --------------------------

    private JFrame frame;
    private JPanel panelGrilleMaisons;
    private JPanel panelMaisonInternationale;
    private JPanel panelContenuPrincipal;
    private final String[] couleursMaisons;
    private final Ciup ciup;
    private final ControleurListeMaisons controleurListeMaisons;

    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------
    /**
     * Constructeur qui initialise la fenêtre et les composants avec les données de la CIUP.
     * @param ciup l'instance modèle de la CIUP contenant maisons et étudiants
     */
    public VueListeMaisons(Ciup ciup) {
        super();
        this.ciup = ciup;

        // Couleurs associées aux maisons étudiantes affichées (4 maximum)
        couleursMaisons = new String[] { "#ba47d7", "#d74b47", "#475ad7", "#cdd747" };

        initialiserFenetre();
        controleurListeMaisons = new ControleurListeMaisons(ciup, frame);
        initialiserHeader(frame);
        initialiserContenuPrincipal();

        frame.setVisible(true);
        frame.setResizable(true);
    }
    
    // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Initialise la JFrame principale.
     */
    private void initialiserFenetre() {
        frame = new JFrame("Gestionnaire de la CIUP : liste des maisons");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    /**
     * Initialise le contenu principal : panneau maison internationale + grille des maisons étudiantes.
     */
    private void initialiserContenuPrincipal() {
        panelContenuPrincipal = new JPanel();
        panelContenuPrincipal.setLayout(new BoxLayout(panelContenuPrincipal, BoxLayout.Y_AXIS));
        panelContenuPrincipal.setBackground(Color.decode("#f2f2f2"));

        // Maison internationale en haut
        panelMaisonInternationale = creerPanelMaisonInternationale("contenuVues/images/maison_internationale.jpg", ciup.getMaisonInternationale());
        panelContenuPrincipal.add(panelMaisonInternationale);

        // Grille 2x2 pour maisons étudiantes
        panelGrilleMaisons = new JPanel(new GridLayout(2, 2));

        for (int i = 0; i < 4; i++) {
            JPanel panelMaison;
            if (ciup.getListeMaisonsEtudiantes().size() > i) {
                MaisonEtudiante maison = ciup.getListeMaisonsEtudiantes().get(i);
                panelMaison = creerPanelMaison(maison.getCheminImage(), maison, couleursMaisons[i]);
            } else {
                panelMaison = creerPanelMaison(couleursMaisons[i]);
            }
            panelGrilleMaisons.add(panelMaison);
        }

        panelContenuPrincipal.add(panelGrilleMaisons);
        frame.add(panelContenuPrincipal, BorderLayout.CENTER);
    }

    /**
     * Crée un JPanel représentant un slot "Ajouter Maison" (sans maison associée).
     * @param fond la couleur de fond à utiliser pour le bandeau supérieur
     * @return un JPanel interactif pour ajouter une maison
     */
    private JPanel creerPanelMaison(String fond) {
        JPanel panelMaison = new JPanel(new BorderLayout());
        panelMaison.setBackground(Color.decode("#f2f2f2"));
        panelMaison.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(Color.decode("#ffffff"));

        JPanel ligne = new JPanel();
        ligne.setBackground(Color.decode(fond));
        ligne.setPreferredSize(new Dimension(500, 10));
        box.add(ligne, BorderLayout.NORTH);

        JPanel panelIcone = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelIcone.setBackground(Color.decode("#ffffff"));

        JLabel icone = new JLabel();
        icone.setIcon(redimensionnerImage(50, 50, "contenuVues/images/ajouter_maison.png"));
        panelIcone.add(icone);

        JPanel panelTitre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitre.setBackground(Color.decode("#ffffff"));

        JLabel labelTitre = new JLabel("AJOUTER MAISON");
        labelTitre.setFont(new Font("Arial Black", Font.PLAIN, 20));
        panelTitre.add(labelTitre);

        JPanel infos = new JPanel(new BorderLayout());
        infos.setBackground(Color.decode("#ffffff"));
        infos.add(panelIcone, BorderLayout.CENTER);
        infos.add(panelTitre, BorderLayout.SOUTH);

        box.add(infos, BorderLayout.CENTER);
        panelMaison.add(box, BorderLayout.CENTER);

        panelMaison.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelMaison.addMouseListener(controleurListeMaisons.getMouseListenerAjoutMaison());

        return panelMaison;
    }

    /**
     * Crée un JPanel pour une maison étudiante avec ses infos.
     * @param imagePath chemin vers l'image de la maison
     * @param maison la maison étudiante à afficher
     * @param couleurFond couleur du bandeau supérieur
     * @return un JPanel représentant la maison étudiante
     */
    private JPanel creerPanelMaison(String imagePath, MaisonEtudiante maison, String couleurFond) {
        JPanel panelMaison = new JPanel(new BorderLayout());
        panelMaison.setBackground(Color.decode("#f2f2f2"));
        panelMaison.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(Color.decode("#ffffff"));

        JPanel ligne = new JPanel();
        ligne.setBackground(Color.decode(couleurFond));
        ligne.setPreferredSize(new Dimension(500, 10));
        box.add(ligne, BorderLayout.NORTH);

        JLabel icone = new JLabel();
        icone.setIcon(redimensionnerImage(100, 100, imagePath));
        box.add(icone, BorderLayout.WEST);

        JPanel infos = new JPanel(new GridLayout(0, 1));
        infos.setBackground(Color.decode("#ffffff"));

        JLabel labelTitre = new JLabel(maison.getNom());
        labelTitre.setFont(new Font("Arial Black", Font.PLAIN, 20));

        JLabel labelEtudiants = new JLabel("👨‍🎓 Étudiants : " + maison.getListeEtudiante().size());
        labelEtudiants.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));

        JLabel labelChambres = new JLabel("🛏 Chambres : " + maison.getListeChambres().size());
        labelChambres.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));

        JLabel labelNationalite = new JLabel("🏳 Nationalité : " + maison.getNationalite());
        labelNationalite.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));

        infos.add(labelTitre);
        infos.add(labelEtudiants);
        infos.add(labelChambres);
        infos.add(labelNationalite);

        box.add(infos, BorderLayout.CENTER);
        panelMaison.add(box, BorderLayout.CENTER);

        panelMaison.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelMaison.addMouseListener(controleurListeMaisons.getMouseListenerMaisonEtudiante(maison));

        return panelMaison;
    }

    /**
     * Crée un JPanel pour la maison internationale, avec ses infos globales.
     * @param imagePath chemin vers l'image de la maison internationale
     * @param maison la maison internationale
     * @return un JPanel représentant la maison internationale
     */
    private JPanel creerPanelMaisonInternationale(String imagePath, MaisonInternationale maison) {
        JPanel panelMaison = new JPanel(new BorderLayout());
        panelMaison.setBackground(Color.decode("#f2f2f2"));
        panelMaison.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(Color.decode("#ffffff"));

        JPanel ligne = new JPanel();
        ligne.setBackground(Color.decode("#47d7ac"));
        ligne.setPreferredSize(new Dimension(500, 10));
        box.add(ligne, BorderLayout.NORTH);

        JPanel boxInfos = new JPanel(new GridLayout(2, 0));
        boxInfos.setBackground(Color.decode("#ffffff"));
        boxInfos.setBorder(new EmptyBorder(0, 10, 0, 0));

        JLabel labelTitre = new JLabel(maison.getNom());
        labelTitre.setFont(new Font("Arial Black", Font.PLAIN, 20));

        JPanel infos = new JPanel(new GridLayout(0, 2));
        infos.setBackground(Color.decode("#ffffff"));

        JLabel labelEtudiants = new JLabel("👨‍🎓 " + ciup.getNbEtudiants() + " étudiants");
        labelEtudiants.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));

        JLabel labelMaisons = new JLabel("🏚 " + ciup.getListeMaisonsEtudiantes().size() + " maisons");
        labelMaisons.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));

        infos.add(labelEtudiants);
        infos.add(labelMaisons);

        boxInfos.add(labelTitre);
        boxInfos.add(infos);

        box.add(boxInfos, BorderLayout.WEST);

        JLabel icone = new JLabel(redimensionnerImage(192, 108, imagePath));
        box.add(icone, BorderLayout.EAST);

        panelMaison.add(box, BorderLayout.CENTER);

        return panelMaison;
    }

    /**
     * Redimensionne une image pour l'affichage dans un JLabel.
     * @param largeur largeur souhaitée
     * @param hauteur hauteur souhaitée
     * @param path chemin vers l'image
     * @return un ImageIcon redimensionné
     */
    private static ImageIcon redimensionnerImage(int largeur, int hauteur, String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    // --------------------------
    //       ACCESSEUR
    // --------------------------

    public JFrame getFrame() { return frame; }
    public void setFrame(JFrame frame) { this.frame = frame; }

    public JPanel getPanelGrilleMaisons() { return panelGrilleMaisons; }
    public void setPanelGrilleMaisons(JPanel panelGrilleMaisons) { this.panelGrilleMaisons = panelGrilleMaisons; }

    public JPanel getPanelMaisonInternationale() { return panelMaisonInternationale; }
    public void setPanelMaisonInternationale(JPanel panelMaisonInternationale) { this.panelMaisonInternationale = panelMaisonInternationale; }

    public JPanel getPanelContenuPrincipal() { return panelContenuPrincipal; }
    public void setPanelContenuPrincipal(JPanel panelContenuPrincipal) { this.panelContenuPrincipal = panelContenuPrincipal; }

    // --------------------------
    //       METHODE APPLICATION
    // --------------------------
    /**
     * Méthode pour lancer l'application depuis un contexte externe.
     */
    public static void lanceApplication() {
        VueListeEtudiant panelEtudiant;
        VueListeSoiree panelSoiree;

        Ciup ciupCharge = GestionSauvegarde.charger();
        if (ciupCharge == null) {
            ciupCharge = Factory.constructionCiup();
        }

        VueListeMaisons vueMaison = new VueListeMaisons(ciupCharge);

        panelEtudiant = new VueListeEtudiant(ciupCharge);
        VueFormulaireEtudiant formulaireEtudiant = new VueFormulaireEtudiant(vueMaison.frame, panelEtudiant);
        ControleurEtudiant controleurEtudiant = new ControleurEtudiant(panelEtudiant, formulaireEtudiant, vueMaison.frame);
        panelEtudiant.rafraichirPanelGauche(controleurEtudiant);

        panelSoiree = new VueListeSoiree(ciupCharge);
        vueFormulaireSoirees formulaire = new vueFormulaireSoirees(vueMaison.frame, panelSoiree);
        ControleurSoiree controleurSoiree = new ControleurSoiree(panelSoiree, formulaire, vueMaison.frame);

        VueHeaderGlobal hv = vueMaison;
        ControleurNavigation navbar = new ControleurNavigation(panelEtudiant, panelSoiree, hv, vueMaison);
    }
}
