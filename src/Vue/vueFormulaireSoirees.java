package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.time.*;
import Modele.*;
import Vue.*;

/**
 * Représente un formulaire Swing permettant à l'utilisateur d'ajouter une soirée à une maison étudiante.
 * L'utilisateur peut saisir le nom, la description, la date, l'heure, le nom de la maison et sélectionner des étudiants.
 * @author Otman Benbouziane
 */
public class vueFormulaireSoirees {
	
	// --------------------------
    //       ATTRIBUT
    // --------------------------

    /**
     * Boîte de dialogue contenant le formulaire.
     */
    private JDialog dialog;

    /**
     * Champ de saisie du nom de la soirée.
     */
    private JTextField fieldNom;

    /**
     * Champ de saisie de la description de la soirée.
     */
    private JTextArea fieldDesc;

    /**
     * Sélecteur de date.
     */
    private JSpinner spinnerDate;

    /**
     * Sélecteur d'heure.
     */
    private JSpinner spinnerHeure;

    /**
     * Bouton de validation du formulaire.
     */
    private JButton boutonValider;

    /**
     * Bouton d'annulation du formulaire.
     */
    private JButton boutonAnnuler;

    /**
     * Champ de saisie du nom de la maison.
     */
    private JTextField fieldMaison;

    /**
     * Constante d'action pour l'ajout d'une soirée.
     */
    static public final String ACTION_AJOUTER_SOIREE = "AJOUTER_SOIREE";

    /**
     * Constante d'action pour l'annulation.
     */
    static public final String ACTION_ANNULER = "ANNULER";

    /**
     * Liste des étudiants affichée dans le formulaire.
     */
    private JList<Etudiant> listeEtudiants;

    /**
     * Vue principale des listes de soirées, utilisée pour accéder aux données de la CIUP.
     */
    private VueListeSoiree _vues;
    
    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------

    /**
     * Construit le formulaire de création de soirée.
     *
     * @param parent la fenêtre parente
     * @param _vues la vue principale des soirées
     */
    public vueFormulaireSoirees(JFrame parent, VueListeSoiree _vues) {
        this._vues = _vues;
        dialog = new JDialog(parent, "Ajouter une soirée", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());

        // Champs
        fieldNom = new JTextField(20);
        fieldDesc = new JTextArea(4, 20);
        fieldDesc.setLineWrap(true);
        fieldDesc.setWrapStyleWord(true);
        JScrollPane descScrollPane = new JScrollPane(fieldDesc);

        spinnerDate = new JSpinner(new SpinnerDateModel());
        spinnerDate.setEditor(new JSpinner.DateEditor(spinnerDate, "yyyy-MM-dd"));

        spinnerHeure = new JSpinner(new SpinnerDateModel());
        spinnerHeure.setEditor(new JSpinner.DateEditor(spinnerHeure, "HH:mm"));

        fieldMaison = new JTextField(20);

        DefaultListModel<Etudiant> modelEtudiants = new DefaultListModel<>();
        for (MaisonEtudiante m : _vues.get_ciup().getListeMaisonsEtudiantes()) {
            for (Etudiant e : m.getListeEtudiante()) {
                modelEtudiants.addElement(e);
            }
        }

        listeEtudiants = new JList<>(modelEtudiants);
        listeEtudiants.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollEtudiants = new JScrollPane(listeEtudiants);
        scrollEtudiants.setPreferredSize(new Dimension(200, 100));
        listeEtudiants.setCellRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Etudiant) {
                    Etudiant e = (Etudiant) value;
                    setText(e.getNom());
                }
                return this;
            }
        });

        boutonValider = new JButton("Valider");
        boutonValider.setActionCommand(ACTION_AJOUTER_SOIREE);
        boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setActionCommand(ACTION_ANNULER);

        // Panel central avec GridBagLayout
        JPanel centre = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        int y = 0;

        // Nom
        gbc.gridx = 0; gbc.gridy = y;
        centre.add(new JLabel("Nom :"), gbc);
        gbc.gridx = 1;
        centre.add(fieldNom, gbc);
        y++;

        // Description
        gbc.gridx = 0; gbc.gridy = y;
        centre.add(new JLabel("Description :"), gbc);
        gbc.gridx = 1;
        centre.add(descScrollPane, gbc);
        y++;

        // Date
        gbc.gridx = 0; gbc.gridy = y;
        centre.add(new JLabel("Date :"), gbc);
        gbc.gridx = 1;
        centre.add(spinnerDate, gbc);
        y++;

        // Heure
        gbc.gridx = 0; gbc.gridy = y;
        centre.add(new JLabel("Heure :"), gbc);
        gbc.gridx = 1;
        centre.add(spinnerHeure, gbc);
        y++;

        // Maison
        gbc.gridx = 0; gbc.gridy = y;
        centre.add(new JLabel("Maison :"), gbc);
        gbc.gridx = 1;
        centre.add(fieldMaison, gbc);
        y++;

        // Étudiants
        gbc.gridx = 0; gbc.gridy = y;
        centre.add(new JLabel("Étudiants :"), gbc);
        gbc.gridx = 1;
        centre.add(scrollEtudiants, gbc);
        y++;

        dialog.add(centre, BorderLayout.CENTER);

        // Panel boutons
        JPanel bas = new JPanel();
        bas.add(boutonValider);
        bas.add(boutonAnnuler);
        dialog.add(bas, BorderLayout.SOUTH);
    }
    
    // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Affiche la boîte de dialogue avec une astuce pour la sélection multiple.
     */
    public void afficher() {
        dialog.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(
            dialog,
            "💡 Astuce : Maintenez la touche Ctrl pour sélectionner plusieurs étudiants.",
            "Information sélection multiple",
            JOptionPane.INFORMATION_MESSAGE
        );
        dialog.setVisible(true);
    }

    /**
     * Ferme la boîte de dialogue.
     */
    public void fermer() {
        dialog.dispose();
    }

    /**
     * Récupère les données saisies dans le formulaire et crée une instance de {@link Soiree}.
     * Effectue des validations sur les champs (non vide, non numérique, maison existante, etc.).
     *
     * @return la soirée créée ou {@code null} si une erreur de validation est survenue
     */
    public Soiree recupererDonnees() {
        String nomMaison = this.getFieldMaison().getText().trim();
        if (nomMaison.isEmpty() || nomMaison.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Le nom de la maison ne peut pas être vide ou uniquement composé de chiffres.");
            return null;
        }

        String nom = this.getFieldNom().getText().trim();
        if (nom.isEmpty() || nom.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Le nom de la soirée ne peut pas être vide ou uniquement composé de chiffres.");
            return null;
        }

        String desc = this.getFieldDesc().getText().trim();
        if (desc.isEmpty() || desc.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "La description ne peut pas être vide ou uniquement composée de chiffres.");
            return null;
        }

        Date date = (Date) this.getSpinnerDate().getValue();
        Date heure = (Date) this.getSpinnerHeure().getValue();

        ZoneId zone = ZoneId.systemDefault();
        LocalDate localDate = date.toInstant().atZone(zone).toLocalDate();
        LocalTime localTime = heure.toInstant().atZone(zone).toLocalTime();
        LocalDateTime dateHeure = LocalDateTime.of(localDate, localTime);

        Soiree soiree = new Soiree(nom, desc, dateHeure);
        MaisonEtudiante maison = _vues.get_ciup().getMaisonParNom(nomMaison);
        if (maison == null) {
            JOptionPane.showMessageDialog(null, "La maison spécifiée n'existe pas.");
            return null;
        } else {
            maison.ajouterSoiree(soiree);
        }

        java.util.List<Etudiant> etudiantsSelectionnes = listeEtudiants.getSelectedValuesList();
        if (etudiantsSelectionnes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner au moins un étudiant.");
            return null;
        }
        for (Etudiant etudiant : etudiantsSelectionnes) {
            soiree.inscrireEtudiantSoiree(etudiant);
        }

        this.fermer();
        return soiree;
    }
    // --------------------------
    //       ACCESSSEUR
    // --------------------------

    /**
     * @return le bouton de validation
     */
    public JButton getBoutonValider() { return boutonValider; }

    /**
     * @return le bouton d'annulation
     */
    public JButton getBoutonAnnuler() { return boutonAnnuler; }

    /**
     * @return le champ de texte pour le nom de la soirée
     */
    public JTextField getFieldNom() { return fieldNom; }

    /**
     * @return le champ de texte pour la description de la soirée
     */
    public JTextArea getFieldDesc() { return fieldDesc; }

    /**
     * @return le sélecteur de date
     */
    public JSpinner getSpinnerDate() { return spinnerDate; }

    /**
     * @return le sélecteur d'heure
     */
    public JSpinner getSpinnerHeure() { return spinnerHeure; }

    /**
     * @return le champ texte pour le nom de la maison
     */
    public JTextField getFieldMaison() { return fieldMaison; }
}