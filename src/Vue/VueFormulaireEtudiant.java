package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import Modele.*;

/**
 * Représente un formulaire Swing permettant d'ajouter un étudiant à la CIUP.
 * L'utilisateur peut renseigner le nom, le prénom, la nationalité (obligatoires)
 * ainsi que la maison souhaitée (facultative).
 * 
 * @author Otman Benbouziane
 */
public class VueFormulaireEtudiant {
	
	// --------------------------
    //       ATTRIBUTS
    // --------------------------

    /**
     * Boîte de dialogue contenant le formulaire.
     */
    private JDialog dialog;

    /**
     * Champ de saisie du nom.
     */
    private JTextField fieldNom;

    /**
     * Champ de saisie du prénom.
     */
    private JTextField fieldPrenom;

    /**
     * Champ de saisie de la nationalité.
     */
    private JTextField fieldNationalite;

    /**
     * Champ de saisie du nom de la maison souhaitée.
     */
    private JTextField fieldSouhait;

    /**
     * Bouton de validation.
     */
    private JButton boutonValider;

    /**
     * Bouton d'annulation.
     */
    private JButton boutonAnnuler;

    /**
     * Constante d'action pour l'ajout d'un étudiant.
     */
    static public final String ACTION_AJOUTER_ETUDIANT = "AJOUTER_ETUDIANT";

    /**
     * Constante d'action pour l'annulation.
     */
    static public final String ACTION_ANNULER = "ANNULER";

    /**
     * Référence à la vue principale des étudiants pour l'accès aux données CIUP.
     */
    private VueListeEtudiant _vues;
    
    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------

    /**
     * Construit le formulaire de création d'un étudiant.
     *
     * @param parent la fenêtre parente
     * @param _vues la vue principale des soirées
     */
    public VueFormulaireEtudiant(JFrame parent, VueListeEtudiant _vues) {
        this._vues = _vues;
        dialog = new JDialog(parent, "Ajouter un étudiant", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        fieldNom = new JTextField(20);
        fieldPrenom = new JTextField(20);
        fieldNationalite = new JTextField(20);
        fieldSouhait = new JTextField(20);

        boutonValider = new JButton("Valider");
        boutonValider.setActionCommand(ACTION_AJOUTER_ETUDIANT);
        boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setActionCommand(ACTION_ANNULER);

        JPanel centre = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        int y = 0;

        gbc.gridx = 0; gbc.gridy = y;
        centre.add(new JLabel("Nom :"), gbc);
        gbc.gridx = 1;
        centre.add(fieldNom, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y;
        centre.add(new JLabel("Prénom :"), gbc);
        gbc.gridx = 1;
        centre.add(fieldPrenom, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y;
        centre.add(new JLabel("Nationalité :"), gbc);
        gbc.gridx = 1;
        centre.add(fieldNationalite, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y;
        centre.add(new JLabel("Maison souhaitée (optionnel) :"), gbc);
        gbc.gridx = 1;
        centre.add(fieldSouhait, gbc);

        dialog.add(centre, BorderLayout.CENTER);

        JPanel bas = new JPanel();
        bas.add(boutonValider);
        bas.add(boutonAnnuler);
        dialog.add(bas, BorderLayout.SOUTH);
    }
    
    // --------------------------
    //       METHODES
    // --------------------------

    /**
     * Affiche la fenetre
     */
    public void afficher() {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * Ferme la fenetre
     */
    public void fermer() {
        dialog.dispose();
    }

    /**
     * Récupère les données saisies et crée un étudiant si les champs sont valides.
     *
     * @return l'étudiant créé ou {@code null} en cas d'erreur de validation
     */
    public Etudiant recupererDonnees() {
    	Etudiant etu;
        String nom = fieldNom.getText().trim();
        String prenom = fieldPrenom.getText().trim();
        String nationalite = fieldNationalite.getText().trim();
        String souhait = fieldSouhait.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || nationalite.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Les champs nom, prénom et nationalité sont obligatoires.");
            return null;
        }

        MaisonEtudiante maison = null;
        if (!souhait.isEmpty()) {
            maison = _vues.get_ciup().getMaisonParNom(souhait);
            if (maison == null) {
                JOptionPane.showMessageDialog(null, "La maison souhaitée n'existe pas.");
                return null;
            }
        }
        if (maison == null) {
        	etu = new Etudiant(_vues.get_ciup(), nom, prenom, nationalite);
        }
        else {
        	etu = new Etudiant(_vues.get_ciup(), nom, prenom, nationalite, maison);
        }
        this.fermer();
        return etu;
    }
    
    // --------------------------
    //       ACCESSEURS
    // --------------------------
    
    /** @return le bouton de validation */
    public JButton getBoutonValider() { return boutonValider; }

    /** @return le bouton d'annulation */
    public JButton getBoutonAnnuler() { return boutonAnnuler; }

    /** @return le champ du nom */
    public JTextField getFieldNom() { return fieldNom; }

    /** @return le champ du prénom */
    public JTextField getFieldPrenom() { return fieldPrenom; }

    /** @return le champ de la nationalité */
    public JTextField getFieldNationalite() { return fieldNationalite; }

    /** @return le champ du souhait de maison */
    public JTextField getFieldSouhait() { return fieldSouhait; }
}