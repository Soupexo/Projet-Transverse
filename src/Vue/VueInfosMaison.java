package Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import Modele.*;
import Controleur.*;

/**
 * VueInfosMaison représente la fenêtre d'affichage des informations détaillées
 * d'une maison étudiante dans l'application de gestion de la CIUP.
 * <p>
 * Cette classe étend {@link VueHeaderGlobal} pour intégrer l'en-tête global de l'application.
 * Elle affiche notamment le nom de la maison, le nombre d'étudiants,
 * la nationalité, le directeur, la description, la liste des étudiants et la miniature de la maison.
 * <p>
 * La vue inclut également des boutons permettant de revenir à la liste des maisons,
 * d'éditer les informations de la maison ou de la supprimer.
 * <p>
 * L'interface utilise des composants Swing avec un agencement en BorderLayout
 * et des panneaux pour organiser les différentes sections.
 * 
 * @author Lucas Pausé-Chapuis
 * @version 1.0
 */
public class VueInfosMaison extends VueHeaderGlobal {
	
	 // --------------------------
    //       ATTRIBUTS
    // --------------------------

    /**
     * La fenêtre principale de cette vue.
     */
    private JFrame frame;

    /**
     * La fenêtre parente qui a ouvert cette vue (peut être null).
     */
    private JFrame parent;

    /**
     * Panneau contenant la grille des éléments d'information.
     */
    private JPanel panelGrilleElements;

    /**
     * Panneau principal contenant le contenu de la fenêtre.
     */
    private JPanel panelContenuPrincipal;

    /**
     * Instance du modèle principal Ciup, représentant la CIUP.
     */
    private Ciup ciup;
    
    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------

    /**
     * Constructeur principal.
     * Initialise et affiche la fenêtre avec les informations de la maison étudiante.
     *
     * @param ciup    l'instance du modèle principal CIUP
     * @param parent  la fenêtre parente (peut être null)
     * @param maison  la maison étudiante dont on affiche les informations
     */
    public VueInfosMaison(Ciup ciup, JFrame parent, MaisonEtudiante maison) {
        this.parent = parent;
        this.ciup = ciup;
        frame = new JFrame("Informations - " + maison.getNom());
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        initialiserFenetre(maison);
        initialiserContenuPrincipal(maison);
        frame.setVisible(true);
    }

    /**
     * Constructeur simplifié sans fenêtre parente.
     * 
     * @param ciup   l'instance du modèle principal CIUP
     * @param maison la maison étudiante dont on affiche les informations
     */
    public VueInfosMaison(Ciup ciup, MaisonEtudiante maison) {
        this(ciup, null, maison);
    }
    
    // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Initialise la fenêtre principale (JFrame) avec titre, taille, layout, etc.
     *
     * @param maison la maison étudiante utilisée pour afficher son nom dans le titre
     */
    private void initialiserFenetre(MaisonEtudiante maison) {
        frame = new JFrame("Gestionnaire de la CIUP : " + maison.getNom());
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    /**
     * Initialise le contenu principal de la fenêtre,
     * en configurant les différents panneaux, boutons et labels affichant les informations.
     *
     * @param maison la maison étudiante à afficher
     */
    private void initialiserContenuPrincipal(MaisonEtudiante maison) {
        panelContenuPrincipal = new JPanel(new BorderLayout());
        panelContenuPrincipal.setBackground(Color.decode("#f2f2f2"));
        panelContenuPrincipal.setBorder(new EmptyBorder(40, 20, 40, 20));

        // Ligne supérieure décorative
        JPanel ligne = new JPanel();
        ligne.setBackground(Color.decode("#47d7ac"));
        ligne.setPreferredSize(new Dimension(500, 10));
        panelContenuPrincipal.add(ligne, BorderLayout.NORTH);

        // Panneau des boutons supérieurs (Retour, Éditer, Supprimer)
        JPanel boutonsSuperieurs = new JPanel(new FlowLayout(FlowLayout.LEFT));
        boutonsSuperieurs.setBackground(Color.decode("#ffffff"));

        JButton boutonSuperieurRetour = new JButton("Retour");
        boutonSuperieurRetour.setFont(new Font("Arial Black", Font.PLAIN, 20));
        boutonSuperieurRetour.setToolTipText("<html>Revenir à la <b>liste des maisons</b>.</html>");
        boutonSuperieurRetour.setForeground(Color.decode("#0ca779"));
        boutonSuperieurRetour.addActionListener(
            ControleurInfosMaison.creerListenerRetour(frame, parent, ciup)
        );

        JButton boutonSuperieurEditInfos = new JButton("Éditer les informations");
        boutonSuperieurEditInfos.setFont(new Font("Arial Black", Font.PLAIN, 20));
        boutonSuperieurEditInfos.setToolTipText("<html>Éditer les <b>informations</b> de cette maison.</html>");
        boutonSuperieurEditInfos.setForeground(Color.decode("#0ca779"));
        boutonSuperieurEditInfos.addActionListener(
            ControleurInfosMaison.creerListenerEditer(frame, ciup, maison)
        );

        JButton boutonSuperieurSupprimer = new JButton("Supprimer");
        boutonSuperieurSupprimer.setFont(new Font("Arial Black", Font.PLAIN, 20));
        boutonSuperieurSupprimer.setToolTipText("<html><b>Supprimer</b> cette maison.</html>");
        boutonSuperieurSupprimer.setForeground(Color.decode("#ff6060"));
        boutonSuperieurSupprimer.addActionListener(
            ControleurInfosMaison.creerListenerSupprimer(frame, parent, ciup, maison)
        );

        boutonsSuperieurs.add(boutonSuperieurRetour);
        boutonsSuperieurs.add(boutonSuperieurEditInfos);
        boutonsSuperieurs.add(boutonSuperieurSupprimer);
        panelContenuPrincipal.add(boutonsSuperieurs, BorderLayout.NORTH);

        // Grille des éléments d'informations (infos, miniature, liste étudiants, description)
        panelGrilleElements = new JPanel(new GridLayout(2, 2));
        panelGrilleElements.setBorder(new EmptyBorder(40, 20, 40, 20));
        panelGrilleElements.setBackground(Color.decode("#ffffff"));

        // Panel infos maison : nom, étudiants, nationalité, directeur, chambres
        JPanel panelInfosMaisons = new JPanel(new GridLayout(2, 0));
        JPanel panelInfosMaisonsTitre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelTitre = new JLabel(maison.getNom());
        labelTitre.setFont(new Font("Arial Black", Font.PLAIN, 24));
        panelInfosMaisonsTitre.add(labelTitre);

        JPanel panelInfosMaisonsDetails = new JPanel(new GridLayout(4, 0));
        JLabel labelEtudiants = new JLabel("👨‍🎓 " + maison.getNbEtudiants() + " étudiants présents dans la maison");
        labelEtudiants.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        panelInfosMaisonsDetails.add(labelEtudiants);

        JLabel labelNationalite = new JLabel("🏳 Nationalité : " + maison.getNationalite());
        labelNationalite.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        panelInfosMaisonsDetails.add(labelNationalite);

        JLabel labelDirecteur = new JLabel("🤵 Directeur : " + maison.getDirecteur());
        labelDirecteur.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        panelInfosMaisonsDetails.add(labelDirecteur);

        JLabel labelChambres = new JLabel("🛏 " + maison.getNbChambres() + " chambres");
        labelChambres.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        panelInfosMaisonsDetails.add(labelChambres);

        panelInfosMaisons.add(panelInfosMaisonsTitre);
        panelInfosMaisons.add(panelInfosMaisonsDetails);
        panelGrilleElements.add(panelInfosMaisons);

        // Panel miniature avec possibilité de changement d'image
        JPanel panelMiniature = new JPanel(new GridLayout(0, 2));
        panelMiniature.setBorder(BorderFactory.createTitledBorder("Cliquez pour changer la miniature"));
        JLabel labelImage = new JLabel();
        labelImage.setIcon(redimensionnerImage(150, 150, maison.getCheminImage()));
        labelImage.addMouseListener(ControleurInfosMaison.creerListenerImage(labelImage, maison));
        panelMiniature.add(labelImage);
        panelGrilleElements.add(panelMiniature);

        // Panel liste des étudiants
        panelGrilleElements.add(creerPanelListeEtudiants(maison));

        // Panel description de la maison
        JPanel panelDescription = new JPanel();
        JLabel labelDescription = new JLabel("Description : " + maison.getDescription());
        labelDescription.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        panelDescription.add(labelDescription);
        panelGrilleElements.add(panelDescription);

        panelContenuPrincipal.add(panelGrilleElements, BorderLayout.CENTER);
        frame.add(panelContenuPrincipal, BorderLayout.CENTER);
    }

    /**
     * Crée un panneau contenant la liste des étudiants inscrits dans la maison.
     * Chaque étudiant est affiché dans un JLabel avec prénom et nom.
     * La liste est insérée dans un JScrollPane pour gestion du défilement.
     *
     * @param maison la maison étudiante dont on affiche les étudiants
     * @return un JPanel contenant la liste des étudiants dans un JScrollPane
     */
    private JPanel creerPanelListeEtudiants(MaisonEtudiante maison) {
        JPanel panelEtudiants = new JPanel();
        panelEtudiants.setLayout(new BoxLayout(panelEtudiants, BoxLayout.Y_AXIS));
        panelEtudiants.setBackground(Color.decode("#ffffff"));

        for (Etudiant etu : maison.getListeEtudiante()) {
            JLabel labelEtudiant = new JLabel(etu.getPrenom() + " " + etu.getNom());
            labelEtudiant.setFont(new Font("Arial", Font.PLAIN, 16));
            labelEtudiant.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            panelEtudiants.add(labelEtudiant);
        }

        JScrollPane scrollPane = new JScrollPane(panelEtudiants);
        scrollPane.setPreferredSize(new Dimension(300, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des étudiants"));

        JPanel panelContainer = new JPanel(new BorderLayout());
        panelContainer.setBackground(Color.decode("#ffffff"));
        panelContainer.add(scrollPane, BorderLayout.CENTER);

        return panelContainer;
    }

    /**
     * Redimensionne une image à la largeur et hauteur spécifiées
     * et retourne un ImageIcon redimensionné.
     *
     * @param largeur la largeur désirée de l'image
     * @param hauteur la hauteur désirée de l'image
     * @param path    le chemin vers le fichier image
     * @return un ImageIcon redimensionné à la taille souhaitée
     */
    private static ImageIcon redimensionnerImage(int largeur, int hauteur, String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
