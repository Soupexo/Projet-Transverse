package Vue;

import javax.swing.*;
import java.awt.*;
import Modele.Maison;
import Controleur.ControleurFormulaireMaison;

/**
 * Fenêtre modale permettant d'éditer les informations d'une maison.
 * 
 * <p>Cette classe affiche un formulaire avec les champs suivants :
 * <ul>
 *   <li>Nom</li>
 *   <li>Description</li>
 *   <li>Nationalité</li>
 *   <li>Directeur</li>
 *   <li>Image</li>
 * </ul>
 * 
 * <p>Elle crée un contrôleur {@link ControleurFormulaireMaison} pour gérer les interactions
 * entre l'utilisateur et le formulaire.
 * 
 * <p>La fenêtre est bloquante (modal) et s'ouvre centrée par rapport à la fenêtre parente.
 * 
 * @author Lucas Pausé-Chapuis
 */
public class VueFormulaireMaison extends JDialog {
	
	// --------------------------
    //       ATTRIBUTS
    // --------------------------

    private JTextField champNom;
    private JTextArea champDescription;
    private JTextField champNationalite;
    private JTextField champDirecteur;
    private JLabel labelImage;
    private JButton boutonChoisirImage;
    private JButton boutonValider;
    private JButton boutonAnnuler;

    private Maison maison;
    
    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------

    /**
     * Initialise la fenêtre de formulaire pour une maison donnée.
     * 
     * @param parent la fenêtre JFrame parente, utilisée pour le positionnement et la modalité
     * @param maison la maison dont les informations seront affichées et modifiées
     */
    public VueFormulaireMaison(JFrame parent, Maison maison) {
        super(parent, "Éditer les informations", true);
        this.maison = maison;

        setSize(500, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        add(creerPanelFormulaire(), BorderLayout.CENTER);
        add(creerPanelBoutons(), BorderLayout.SOUTH);

        // Création du contrôleur qui gère les événements de la vue
        new ControleurFormulaireMaison(this, maison);

        setVisible(true);
    }
    
    // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Construit et retourne le panneau central contenant les champs de saisie.
     * 
     * <p>Utilise un GridBagLayout pour disposer les labels et champs de manière
     * alignée et espacée.
     * 
     * @return le JPanel contenant les composants du formulaire
     */
    private JPanel creerPanelFormulaire() {
        JPanel panelFormulaire = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelFormulaire.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nom
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulaire.add(new JLabel("Nom :"), gbc);
        gbc.gridx = 1;
        champNom = new JTextField(maison.getNom(), 20);
        panelFormulaire.add(champNom, gbc);

        // Description
        gbc.gridy++;
        gbc.gridx = 0;
        panelFormulaire.add(new JLabel("Description :"), gbc);
        gbc.gridx = 1;
        champDescription = new JTextArea(maison.getDescription() != null ? maison.getDescription() : "", 3, 20);
        champDescription.setLineWrap(true);
        champDescription.setWrapStyleWord(true);
        panelFormulaire.add(new JScrollPane(champDescription), gbc);

        // Nationalité
        gbc.gridy++;
        gbc.gridx = 0;
        panelFormulaire.add(new JLabel("Nationalité :"), gbc);
        gbc.gridx = 1;
        champNationalite = new JTextField(maison.getNationalite() != null ? maison.getNationalite() : "", 20);
        panelFormulaire.add(champNationalite, gbc);

        // Directeur
        gbc.gridy++;
        gbc.gridx = 0;
        panelFormulaire.add(new JLabel("Directeur :"), gbc);
        gbc.gridx = 1;
        champDirecteur = new JTextField(maison.getDirecteur() != null ? maison.getDirecteur() : "", 20);
        panelFormulaire.add(champDirecteur, gbc);
        
        // Image
        gbc.gridy++;
        gbc.gridx = 0;
        panelFormulaire.add(new JLabel("Image :"), gbc);
        gbc.gridx = 1;
        JPanel panelImage = new JPanel(new BorderLayout());
        labelImage = new JLabel();
        labelImage.setPreferredSize(new Dimension(100, 100));
        if (maison.getCheminImage() != null) {
            labelImage.setIcon(redimensionnerImage(100, 100, maison.getCheminImage()));
        }
        boutonChoisirImage = new JButton("Choisir une image");
        panelImage.add(labelImage, BorderLayout.WEST);
        panelImage.add(boutonChoisirImage, BorderLayout.CENTER);
        panelFormulaire.add(panelImage, gbc);

        return panelFormulaire;
    }

    /**
     * Construit et retourne le panneau contenant les boutons de validation et d'annulation.
     * 
     * @return le JPanel contenant les boutons Valider et Annuler
     */
    private JPanel creerPanelBoutons() {
        JPanel panelBoutons = new JPanel();
        boutonValider = new JButton("Valider");
        boutonAnnuler = new JButton("Annuler");

        panelBoutons.add(boutonValider);
        panelBoutons.add(boutonAnnuler);

        return panelBoutons;
    }
    
    // --------------------------
    //       ACCESSEURS
    // --------------------------

    /**
     * Retourne le bouton Valider.
     * 
     * @return le JButton associé à la validation du formulaire
     */
    public JButton getBoutonValider() {
        return boutonValider;
    }

    /**
     * Retourne le bouton Annuler.
     * 
     * @return le JButton associé à l'annulation du formulaire
     */
    public JButton getBoutonAnnuler() {
        return boutonAnnuler;
    }

    /**
     * Retourne le bouton permettant de choisir une image pour la maison.
     * 
     * @return le JButton permettant de sélectionner une image
     */
    public JButton getBoutonChoisirImage() {
        return boutonChoisirImage;
    }

    /**
     * Retourne le champ texte du nom de la maison.
     * 
     * @return le JTextField pour le nom
     */
    public JTextField getChampNom() {
        return champNom;
    }

    /**
     * Retourne la zone de texte pour la description de la maison.
     * 
     * @return le JTextArea pour la description
     */
    public JTextArea getChampDescription() {
        return champDescription;
    }

    /**
     * Retourne le champ texte pour la nationalité de la maison.
     * 
     * @return le JTextField pour la nationalité
     */
    public JTextField getChampNationalite() {
        return champNationalite;
    }

    /**
     * Retourne le champ texte pour le directeur de la maison.
     * 
     * @return le JTextField pour le directeur
     */
    public JTextField getChampDirecteur() {
        return champDirecteur;
    }

    /**
     * Retourne le label affichant l'image associée à la maison.
     * 
     * @return le JLabel contenant l'icône de l'image
     */
    public JLabel getLabelImage() {
        return labelImage;
    }

    /**
     * Redimensionne une image à la largeur et hauteur spécifiées.
     * 
     * <p>Cette méthode est utilisée pour adapter l'affichage de l'image
     * dans le formulaire.
     * 
     * @param largeur la largeur désirée en pixels
     * @param hauteur la hauteur désirée en pixels
     * @param cheminImage le chemin vers le fichier image à charger
     * @return une ImageIcon redimensionnée selon les dimensions données
     */
    public ImageIcon redimensionnerImage(int largeur, int hauteur, String cheminImage) {
        ImageIcon icon = new ImageIcon(cheminImage);
        Image image = icon.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
