package Application;

import Modele.*;
import Vue.*;
import Controleur.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Classe principale de l'application CIUP.
 * Cette classe initialise les composants principaux de l'application en suivant le
 * modèle MVC (Modèle-Vue-Contrôleur). Elle se charge du chargement de la CIUP,
 * de l'initialisation des vues, des contrôleurs et de la navigation entre les interfaces.
 * 
 * Elle constitue le point d'entrée principal de l'application pour le déploiement
 * graphique des différentes fonctionnalités.
 * 
 * @author Otman Benbouziane
 */
public class ApplicationCiup {
	
	 // --------------------------
    //       CONSTRUCTEUR
    // --------------------------
    
    /**
     * Constructeur de déploiement de l'application CIUP.
     * Cette méthode effectue les étapes suivantes :
     * <ul>
     *   <li>Chargement des données sauvegardées via {@link GestionSauvegarde#charger()}</li>
     *   <li>Création d'une instance de {@link Ciup} via {@link Factory#constructionCiup()} si aucune sauvegarde n'est trouvée</li>
     *   <li>Initialisation des vues : {@link VueListeMaisons}, {@link VueListeEtudiant}, {@link VueListeSoiree}</li>
     *   <li>Création des formulaires : {@link VueFormulaireEtudiant}, {@link vueFormulaireSoirees}</li>
     *   <li>Instanciation des contrôleurs : {@link ControleurEtudiant}, {@link ControleurSoiree}, {@link ControleurNavigation}</li>
     *   <li>Rafraîchissement du panneau gauche des étudiants avec le contrôleur approprié</li>
     * </ul>
     */
    public ApplicationCiup() {
         VueListeEtudiant panelEtudiant;
         VueListeSoiree panelSoiree;

         Ciup ciupCharge = GestionSauvegarde.charger();
         if (ciupCharge == null) {
             ciupCharge = Factory.constructionCiup();
         }

         VueListeMaisons VueMaison = new VueListeMaisons(ciupCharge);

         panelEtudiant = new VueListeEtudiant(ciupCharge);
         VueFormulaireEtudiant formulaireEtudiant = new VueFormulaireEtudiant(VueMaison.getFrame(), panelEtudiant);
         ControleurEtudiant controleurEtudiant = new ControleurEtudiant(panelEtudiant, formulaireEtudiant, VueMaison.getFrame());
         panelEtudiant.rafraichirPanelGauche(controleurEtudiant);

         panelSoiree = new VueListeSoiree(ciupCharge);
         vueFormulaireSoirees formulaire = new vueFormulaireSoirees(VueMaison.getFrame(), panelSoiree);
         ControleurSoiree controleurSoiree = new ControleurSoiree(panelSoiree, formulaire, VueMaison.getFrame());

         VueHeaderGlobal hv = VueMaison;
         ControleurNavigation navbar = new ControleurNavigation(panelEtudiant, panelSoiree, hv, VueMaison);
    }
    
    // --------------------------
    //       MAIN
    // --------------------------
    
    /**
     *Déploiement de l'application dans le Main.
     */
    public static void main(String[] args) {
    	ApplicationCiup app = new ApplicationCiup();
    	
    }
}
