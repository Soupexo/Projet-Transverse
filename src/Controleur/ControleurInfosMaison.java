package Controleur;

import javax.swing.*;
import java.awt.Cursor;
import java.awt.event.*;

import Modele.*;
import Vue.*;

/**
 * Contrôleur des actions liées à l'affichage des informations d'une maison étudiante.
 * 
 * Cette classe fournit des listeners statiques pour gérer les interactions dans la vue {@link VueInfosMaison} :
 * <ul>
 *   <li>Retour à la liste des maisons</li>
 *   <li>Édition d'une maison existante</li>
 *   <li>Suppression d'une maison</li>
 *   <li>Modification de l'image en cliquant sur celle-ci</li>
 * </ul>
 * 
 * @author Lucas Pausé-Chapuis
 * @version 1.0
 * 
 * @see VueInfosMaison
 * @see MaisonEtudiante
 * @see VueFormulaireMaison
 * @see GestionSauvegarde
 */
public class ControleurInfosMaison {
	
	  // --------------------------
    //       METHODE
    // --------------------------
    
    /**
     * Crée un {@link ActionListener} pour revenir à la liste des maisons en fermant les fenêtres courantes.
     * 
     * @param frame la fenêtre d'informations actuelle à fermer
     * @param parent la fenêtre parent éventuellement ouverte (peut être {@code null})
     * @param ciup le modèle principal contenant les maisons
     * @return un {@code ActionListener} pour le bouton "Retour"
     */
    public static ActionListener creerListenerRetour(JFrame frame, JFrame parent, Ciup ciup) {
        return e -> {
            if (parent != null) {
                parent.dispose();
            }
            frame.dispose();
            GestionSauvegarde.sauvegarder(ciup);
            VueListeMaisons.lanceApplication();
        };
    }

    /**
     * Crée un {@link ActionListener} pour lancer l'édition d'une maison dans un formulaire.
     * 
     * @param frame la fenêtre actuelle à fermer
     * @param ciup le modèle principal
     * @param maison la maison étudiante à éditer
     * @return un {@code ActionListener} pour le bouton "Éditer"
     */
    public static ActionListener creerListenerEditer(JFrame frame, Ciup ciup, MaisonEtudiante maison) {
        return e -> {
            new VueFormulaireMaison(frame, maison);
            frame.dispose();
            new VueInfosMaison(ciup, maison); // Réouverture après l'édition (facultatif selon logique métier)
            GestionSauvegarde.sauvegarder(ciup);
        };
    }

    /**
     * Crée un {@link ActionListener} pour supprimer une maison du modèle avec confirmation.
     * 
     * @param frame la fenêtre courante à fermer après suppression
     * @param parent la fenêtre parent éventuellement ouverte (peut être {@code null})
     * @param ciup le modèle principal
     * @param maison la maison à supprimer
     * @return un {@code ActionListener} pour le bouton "Supprimer"
     */
    public static ActionListener creerListenerSupprimer(JFrame frame, JFrame parent, Ciup ciup, MaisonEtudiante maison) {
        return e -> {
            int confirmation = JOptionPane.showConfirmDialog(
                frame,
                "La suppression d'une maison est irréversible.",
                "Confirmer la suppression ?",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            if (confirmation == JOptionPane.OK_OPTION) {
                ciup.supprimerMaison(maison);
                frame.dispose();
                if (parent != null) {
                    parent.dispose();
                }
                GestionSauvegarde.sauvegarder(ciup);
            }
        };
    }

    /**
     * Crée un {@link MouseListener} pour gérer les interactions avec l'image d'une maison :
     * <ul>
     *   <li>Clic : permet de sélectionner une nouvelle image</li>
     *   <li>Survol : change le curseur en main</li>
     * </ul>
     * 
     * @param labelImage le label contenant l'image à modifier
     * @param maison la maison à laquelle l'image est associée
     * @return un {@code MouseListener} à associer au {@code JLabel}
     */
    public static MouseListener creerListenerImage(JLabel labelImage, MaisonEtudiante maison) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControleurImageMaison.choisirEtAfficherImage(labelImage, maison);
                GestionSauvegarde.sauvegarder(maison.getCiup());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                GestionSauvegarde.sauvegarder(maison.getCiup());
            }
        };
    }
}
