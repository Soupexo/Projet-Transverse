package Controleur;

import Vue.*;
import Modele.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

/**
 * Contrôleur gérant les interactions de la liste des maisons étudiantes dans l'interface graphique.
 * 
 * Cette classe permet de gérer les événements liés à la sélection d'une maison existante
 * ainsi que la création d'une nouvelle maison via des listeners souris.
 * 
 * Elle agit en lien avec le modèle {@link Ciup} et la vue principale {@link JFrame}.
 * 
 * @author Lucas Pausé-Chapuis
 * @version 1.0
 * 
 * @see VueInfosMaison
 * @see MaisonEtudiante
 * @see GestionSauvegarde
 */
public class ControleurListeMaisons {
	
	 // --------------------------
    //       ATTRIBUT
    // --------------------------

    /**
     * Modèle principal contenant la liste des maisons étudiantes.
     */
    private Ciup ciup;

    /**
     * Fenêtre principale de l'application.
     */
    private JFrame frame;
    
    // --------------------------
    //       CONSTRUCTEUR
    // --------------------------

    /**
     * Constructeur du contrôleur.
     * 
     * @param ciup le modèle principal
     * @param frame la fenêtre principale
     */
    public ControleurListeMaisons(Ciup ciup, JFrame frame) {
        this.ciup = ciup;
        this.frame = frame;
    }
    
    // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Retourne un {@link MouseAdapter} pour ouvrir la vue d'informations d'une maison étudiante existante.
     * 
     * @param maison la maison étudiante sur laquelle l'utilisateur a cliqué
     * @return un {@code MouseAdapter} à associer à un composant graphique
     */
    public MouseAdapter getMouseListenerMaisonEtudiante(MaisonEtudiante maison) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new VueInfosMaison(ciup, frame, maison);
                GestionSauvegarde.sauvegarder(ciup);
            }
        };
    }

    /**
     * Retourne un {@link MouseAdapter} permettant de créer une nouvelle maison étudiante avec des valeurs par défaut,
     * puis d'ouvrir sa vue d'informations.
     * 
     * @return un {@code MouseAdapter} à associer à un bouton ou autre composant graphique
     */
    public MouseAdapter getMouseListenerAjoutMaison() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Initialise une liste de capacités de taille 100, toutes à 1
                List<Integer> capacites = Arrays.asList(new Integer[100]);
                for (int i = 0; i < capacites.size(); i++) {
                    capacites.set(i, 1);
                }

                // Crée une maison avec valeurs par défaut
                MaisonEtudiante nouvelleMaison = new MaisonEtudiante(
                    ciup,
                    "Sans nom",
                    "Inconnu",
                    "Inconnue",
                    "Inconnue",
                    capacites
                );

                // Ouvre la vue d'informations sur cette nouvelle maison
                new VueInfosMaison(ciup, frame, nouvelleMaison);

                // Sauvegarde le modèle
                GestionSauvegarde.sauvegarder(ciup);
            }
        };
    }
}
