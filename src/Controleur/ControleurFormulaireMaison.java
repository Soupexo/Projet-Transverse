package Controleur;

import Vue.*;
import Modele.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

/**
 * Contrôleur chargé de la gestion du formulaire de modification des informations d'une maison.
 * 
 * Il permet :
 * <ul>
 *   <li>la validation des informations saisies (nom, description, nationalité, directeur),</li>
 *   <li>la sélection et l'enregistrement d'une image représentant la maison,</li>
 *   <li>l'annulation des modifications en fermant simplement le formulaire.</li>
 * </ul>
 * 
 * Toute modification validée entraîne une sauvegarde automatique de l'état de la CIUP.
 * 
 * @see VueFormulaireMaison
 * @see Maison
 * @see GestionSauvegarde
 * 
 * @version 1.0
 * @author Lucas Pausé-Chapuis
 */
public class ControleurFormulaireMaison {
	
	 // --------------------------
    //       ATTRIBUTS
    // --------------------------

    /** Vue du formulaire associé à la maison. */
    private final VueFormulaireMaison vue;

    /** Maison dont les informations sont modifiées. */
    private final Maison maison;
    
    // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Initialise le contrôleur avec la vue du formulaire et la maison à modifier.
     * 
     * @param vue la vue du formulaire de maison
     * @param maison la maison à modifier
     */
    public ControleurFormulaireMaison(VueFormulaireMaison vue, Maison maison) {
        this.vue = vue;
        this.maison = maison;

        initListeners();
    }

    /**
     * Initialise les écouteurs d'événements pour les boutons du formulaire :
     * <ul>
     *   <li>Valider : sauvegarde les modifications,</li>
     *   <li>Annuler : ferme le formulaire,</li>
     *   <li>Choisir une image : ouvre un sélecteur de fichiers et associe une image à la maison.</li>
     * </ul>
     */
    private void initListeners() {
        vue.getBoutonValider().addActionListener(e -> validerInfos());
        vue.getBoutonAnnuler().addActionListener(e -> vue.dispose());
        vue.getBoutonChoisirImage().addActionListener(e -> choisirImage());
    }

    /**
     * Récupère les données saisies dans les champs du formulaire
     * et les applique à l'objet maison, puis sauvegarde.
     */
    private void validerInfos() {
        maison.setNom(vue.getChampNom().getText());
        maison.setDescription(vue.getChampDescription().getText());
        maison.setNationalite(vue.getChampNationalite().getText());
        maison.setDirecteur(vue.getChampDirecteur().getText());
        GestionSauvegarde.sauvegarder(maison.getCiup());
        vue.dispose();
    }

    /**
     * Ouvre une boîte de dialogue pour sélectionner une image.
     * Si une image est choisie, elle est redimensionnée, affichée dans le formulaire
     * et associée à la maison. Une sauvegarde est effectuée.
     */
    private void choisirImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionner une image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "png", "jpeg", "gif"));

        int result = fileChooser.showOpenDialog(vue);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();
            vue.getLabelImage().setIcon(vue.redimensionnerImage(100, 100, imagePath));
            maison.setCheminImage(imagePath);
            GestionSauvegarde.sauvegarder(maison.getCiup());
        }
    }
}
