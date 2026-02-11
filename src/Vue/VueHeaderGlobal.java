package Vue;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant l'en-tête global de l'application.
 * 
 * <p>Ce composant graphique fournit une barre d'en-tête (header) contenant :
 * <ul>
 *   <li>Un logo CIUP à gauche</li>
 *   <li>Trois boutons à droite permettant de naviguer vers différentes vues :
 *       <ul>
 *         <li>Maisons</li>
 *         <li>Étudiants</li>
 *         <li>Soirées</li>
 *       </ul>
 *   </li>
 * </ul>
 * 
 * <p>Les boutons sont stylisés avec une police spécifique, une couleur et un tooltip explicatif.
 * Les actions des boutons sont identifiées par des chaînes de caractères constantes
 * pour faciliter l'écoute dans les contrôleurs.
 * 
 * <p>Cette classe ne gère pas directement les événements des boutons, elle se contente de fournir
 * les composants graphiques configurés et accessibles via leurs getters/setters.
 * 
 * @author Lucas Pausé-Chapuis
 */
public class VueHeaderGlobal {
	
	// --------------------------
    //       ATTRIBUT
    // --------------------------
	
	/** Bouton pour accéder à la gestion des maisons */
	protected JButton boutonHeaderMaisons;

	/** Bouton pour accéder à la gestion des étudiants */
	protected JButton boutonHeaderEtudiants;

	/** Bouton pour accéder à l'agenda des soirées */
	protected JButton boutonHeaderSoirees;

	/** Action command pour afficher la vue des étudiants */
	public static final String ACTION_AFFICHER_VUE_ETUDIANT = "AFFICHER_VUE_ETUDIANT";

	/** Action command pour afficher la vue des soirées */
	public static final String ACTION_AFFICHER_VUE_SOIREE = "AFFICHER_VUE_SOIREE";
    
	// --------------------------
    //       CONSTRUCTEUR
    // --------------------------
	/**
	 * Constructeur qui initialise les boutons avec leur texte par défaut.
	 */
	public VueHeaderGlobal() {
		boutonHeaderMaisons = new JButton("Maisons");
		boutonHeaderEtudiants = new JButton("Étudiants");
		boutonHeaderSoirees = new JButton("Soirées");
	}
	
	// --------------------------
    //       METHODE
    // --------------------------

	/**
	 * Initialise et ajoute le panneau d'en-tête complet à la fenêtre principale.
	 * 
	 * <p>Ce panneau contient le logo à gauche, un espace horizontal, puis les boutons à droite.
	 * La couleur de fond est blanche.
	 * 
	 * @param frame la fenêtre principale JFrame dans laquelle ajouter le header
	 */
	public void initialiserHeader(JFrame frame) {
		JPanel panelHeaderGlobal = new JPanel(new BorderLayout());
		JPanel panelHeaderLogoCiup = new JPanel();
		JPanel panelHeaderBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		// Logo CIUP
		JLabel headerLogoCiupImg = new JLabel();
		headerLogoCiupImg.setIcon(new ImageIcon("contenuVues/images/ciup_logo_header.png"));
		panelHeaderLogoCiup.setBackground(Color.WHITE);
		panelHeaderLogoCiup.add(headerLogoCiupImg);

		// Configuration des boutons
		boutonHeaderMaisons.setFont(new Font("Arial Black", Font.PLAIN, 25));
		boutonHeaderMaisons.setToolTipText("<html>Accéder au <b>gestionnaire des maisons</b>.</html>");
		boutonHeaderMaisons.setForeground(Color.decode("#0ca779"));

		boutonHeaderEtudiants.setActionCommand(ACTION_AFFICHER_VUE_ETUDIANT);
		boutonHeaderEtudiants.setFont(new Font("Arial Black", Font.PLAIN, 25));
		boutonHeaderEtudiants.setToolTipText("<html>Gérer la <b>liste des étudiants</b>.</html>");
		boutonHeaderEtudiants.setForeground(Color.decode("#0ca779"));

		boutonHeaderSoirees.setActionCommand(ACTION_AFFICHER_VUE_SOIREE);
		boutonHeaderSoirees.setFont(new Font("Arial Black", Font.PLAIN, 25));
		boutonHeaderSoirees.setToolTipText("<html>Accéder à <b>l'agenda des soirées</b>.</html>");
		boutonHeaderSoirees.setForeground(Color.decode("#0ca779"));

		// Ajout des boutons au panneau
		panelHeaderBoutons.add(boutonHeaderMaisons);
		panelHeaderBoutons.add(boutonHeaderEtudiants);
		panelHeaderBoutons.add(boutonHeaderSoirees);
		panelHeaderBoutons.setBackground(Color.WHITE);

		// Assemblage final dans le panneau global
		panelHeaderGlobal.add(panelHeaderLogoCiup, BorderLayout.WEST);
		panelHeaderGlobal.add(Box.createHorizontalStrut(50), BorderLayout.CENTER); // espace entre logo et boutons
		panelHeaderGlobal.add(panelHeaderBoutons, BorderLayout.EAST);
		panelHeaderGlobal.setBackground(Color.WHITE);

		// Ajout à la fenêtre principale
		frame.add(panelHeaderGlobal, BorderLayout.NORTH);
	}
	
	 // --------------------------
    //       ACCESSEUR
    // --------------------------

	/**
	 * Retourne le bouton "Maisons".
	 * 
	 * @return JButton bouton maisons
	 */
	public JButton getBoutonHeaderMaisons() {
		return boutonHeaderMaisons;
	}

	/**
	 * Définit le bouton "Maisons".
	 * 
	 * @param boutonHeaderMaisons le JButton à utiliser
	 */
	public void setBoutonHeaderMaisons(JButton boutonHeaderMaisons) {
		this.boutonHeaderMaisons = boutonHeaderMaisons;
	}

	/**
	 * Retourne le bouton "Étudiants".
	 * 
	 * @return JButton bouton étudiants
	 */
	public JButton getBoutonHeaderEtudiants() {
		return boutonHeaderEtudiants;
	}

	/**
	 * Définit le bouton "Étudiants".
	 * 
	 * @param boutonHeaderEtudiants le JButton à utiliser
	 */
	public void setBoutonHeaderEtudiants(JButton boutonHeaderEtudiants) {
		this.boutonHeaderEtudiants = boutonHeaderEtudiants;
	}

	/**
	 * Retourne le bouton "Soirées".
	 * 
	 * @return JButton bouton soirées
	 */
	public JButton getBoutonHeaderSoirees() {
		return boutonHeaderSoirees;
	}

	/**
	 * Définit le bouton "Soirées".
	 * 
	 * @param boutonHeaderSoirees le JButton à utiliser
	 */
	public void setBoutonHeaderSoirees(JButton boutonHeaderSoirees) {
		this.boutonHeaderSoirees = boutonHeaderSoirees;
	}
}
