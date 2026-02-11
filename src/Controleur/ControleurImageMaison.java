package Controleur;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

import Modele.GestionSauvegarde;
import Modele.Maison;

/**
 * Contrôleur utilitaire permettant de gérer la sélection et l'affichage d'une image pour une maison.
 * 
 * Cette classe est statique et peut être utilisée indépendamment pour associer une image à une instance de {@link Maison}.
 * 
 * Elle propose :
 * <ul>
 *   <li>l'ouverture d'une boîte de dialogue pour choisir une image sur le disque,</li>
 *   <li>l'affichage de l'image redimensionnée dans un composant {@code JLabel},</li>
 *   <li>la mise à jour du chemin de l'image dans l'objet {@code Maison} et la sauvegarde automatique.</li>
 * </ul>
 * 
 * @see Maison
 * @see GestionSauvegarde
 * 
 * @version 1.0
 * @author Lucas Pausé-Chapuis
 */
public class ControleurImageMaison {
	
	 // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Ouvre une boîte de dialogue pour sélectionner une image, l'affiche dans le label fourni
     * et associe son chemin à l'objet {@code Maison} donné, puis déclenche une sauvegarde.
     *
     * @param labelImage le composant {@code JLabel} dans lequel l'image redimensionnée sera affichée
     * @param maison l'objet {@code Maison} auquel l'image est associée
     */
    public static void choisirEtAfficherImage(JLabel labelImage, Maison maison) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionner une image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif"));

        int result = fileChooser.showOpenDialog(null); // null = centré sur l'écran

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();

            // Affichage de l'image redimensionnée
            labelImage.setIcon(redimensionnerImage(150, 150, imagePath));

            // Mise à jour du modèle
            maison.setCheminImage(imagePath);
            GestionSauvegarde.sauvegarder(maison.getCiup());
        }
    }

    /**
     * Redimensionne l'image donnée par son chemin pour l'adapter à la taille spécifiée.
     *
     * @param largeur la largeur cible
     * @param hauteur la hauteur cible
     * @param path le chemin de l'image à redimensionner
     * @return une {@code ImageIcon} redimensionnée
     */
    private static ImageIcon redimensionnerImage(int largeur, int hauteur, String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
