package Modele;

import java.util.*;
import java.time.LocalDateTime;

/**
 * Classe utilitaire permettant la construction initiale
 * d'un objet Ciup complet avec ses maisons, étudiants et soirées.
 * Cette factory crée une instance de Ciup contenant une maison internationale,
 * plusieurs maisons étudiantes avec leurs capacités de chambres,
 * des étudiants affectés à ces maisons, ainsi que des soirées associées.
 * 
 * Méthode principale disponible :
 * <ul>
 *   <li>{@link #constructionCiup()} : crée et retourne un objet Ciup initialisé.</li>
 * </ul>
 * Cette classe facilite la création d'un jeu de données cohérent pour
 * démarrer l'application ou les tests.
 * @author Lucas Pausé-Chapuis
 */
public class Factory {
	/**
	 * Construit et retourne une instance de Ciup pré-remplie avec :
	 * <ul>
	 *   <li>Une maison internationale</li>
	 *   <li>Quatre maisons étudiantes avec listes de capacités de chambres</li>
	 *   <li>Des étudiants associés aux maisons étudiantes</li>
	 *   <li>Des soirées créées et assignées aux maisons et aux étudiants</li>
	 * </ul>
	 * 
	 * @return un objet Ciup complet avec maisons, étudiants et soirées initialisés
	 */
	
	//--------------------------
  	//      METHODE
  	//--------------------------
	public static Ciup constructionCiup() {
		MaisonInternationale mInternationale;
		mInternationale = new MaisonInternationale("Maison internationale de la CIUP", "Française", "M. Jean-Marc Sauvé", "17 Bd Jourdan, 75014 Paris");
		Ciup ciup = new Ciup(mInternationale);
		
       List<Integer> LISTE_CAPACITE_CHAMBRE_MAISON_METUDIANTE1 = Arrays.asList(
		        1, 1
		    );

	   List<Integer> LISTE_CAPACITE_CHAMBRE_MAISON_METUDIANTE2 = Arrays.asList(
		        2, 1, 1, 2, 1, 2, 1, 2, 2, 1,
		        1, 1, 2, 1, 2, 1, 2, 1, 1, 2,
		        2, 1, 1, 2, 2, 2, 1, 2, 1, 1,
		        1, 2, 1, 1, 2, 1, 2, 2, 1, 1,
		        2, 1, 2, 2, 1, 2, 1, 1, 2, 1,
		        2, 2, 1, 2, 1, 1, 1, 2, 1, 1,
		        2, 1, 2, 2, 1, 2, 1, 1, 1, 2,
		        2, 1, 2, 1, 2, 2, 1, 2, 1, 1,
		        1, 2, 1, 2, 2, 1, 2, 1, 2, 2,
		        1, 2, 1, 2, 1, 2, 2, 1, 2, 2,
		        1, 1, 2, 1, 2, 1, 1
		    );

	 List<Integer> LISTE_CAPACITE_CHAMBRE_MAISON_METUDIANTE3 = Arrays.asList(
			  	1, 2, 1, 2, 1, 1, 2, 1, 2, 2,
		        1, 2, 2, 1, 1, 2, 1, 1, 2, 2,
		        1, 1, 2, 1, 2, 2, 1, 2, 1, 1,
		        2, 1, 1, 2, 2, 1, 2, 1, 1, 2,
		        1, 1, 2, 2, 1, 1, 2, 1, 2, 2,
		        2, 1, 2, 1, 2, 1, 2, 2, 1, 2,
		        1, 2, 1, 1, 2, 2, 1, 2, 1, 2,
		        2, 1, 1, 2, 2, 1, 2, 1, 1, 2,
		        2, 2, 1, 1, 2, 1, 2, 1, 2, 1,
		        1, 2, 1, 2, 2, 1, 1, 2, 1, 1,
		        2, 1, 2, 2, 1, 1, 2, 1, 2, 2,
		        1, 1, 2, 2
		    );

	List<Integer> LISTE_CAPACITE_CHAMBRE_MAISON_METUDIANTE4 = Arrays.asList(
			1, 1, 2, 1, 2, 1, 1, 2, 2, 1,
			2, 1, 2, 1, 1, 2, 2, 1, 1, 2,
			1, 1, 2, 2, 1, 1, 2, 1, 2, 1,
			2, 2, 1, 1, 1, 2, 1, 1, 2, 2,
			1, 1, 2, 1, 2, 2, 1, 1, 2, 2,
			1, 2, 1, 1, 2, 1, 2, 1, 1, 2,
			2, 1, 1, 2, 2, 1, 1, 2, 1, 1,
			2, 2, 1, 1, 2, 2, 1, 1, 2, 2,
			1, 1, 2, 2, 1, 2, 1, 1, 2, 2,
			1, 2, 1, 2, 1, 2, 2, 1, 2, 1,
			1, 1, 2, 2, 1, 1, 2, 1, 2, 1,
			1, 2, 1, 2, 1, 2, 1, 2, 1, 1,
			2, 2, 1, 2, 1, 2, 1, 2, 1, 1,
			2, 1, 2, 1, 1, 2, 2, 1, 2, 2,
			1, 1, 2, 2, 1, 2, 1, 2, 1, 1,
			2, 2, 1, 2, 1, 1, 2, 2, 1, 2,
			2, 1, 1, 2, 2, 1, 1, 2, 2, 1,
			2, 1, 1, 2, 1, 2, 1, 1, 2, 1,
			2, 1, 2, 1, 2, 2, 1, 1, 2, 1,
			2, 1, 1, 2, 2, 1, 1, 2, 1, 2,
			1, 2, 1, 2, 1, 1, 2, 2, 1, 1,
			2, 1, 2, 2, 1, 2, 1, 1, 2, 1,
			1, 2, 2, 1, 2, 1, 2, 2, 1, 1,
			2, 1, 2, 2, 1, 2, 1, 1, 2, 1,
			2, 1, 1, 2, 1, 2, 1, 1, 2, 2, 
			1, 1, 2, 1, 2, 2, 1, 2, 1, 1, 
            2, 1, 2, 1, 2, 1, 2, 2, 1, 1, 
            2, 1, 2, 1, 1, 2, 2, 1, 1, 2, 
            1, 2, 1, 2, 1, 2, 1, 1, 2, 2, 
            1, 1
		    );
		
		MaisonEtudiante mEtudiante1 = new MaisonEtudiante(ciup, "Maison des provinces de France", "Hubert DUCOU LE POINTE", "Français", "55 Bd Jourdan, 75014 Paris",LISTE_CAPACITE_CHAMBRE_MAISON_METUDIANTE1);
		MaisonEtudiante mEtudiante2 = new MaisonEtudiante(ciup, "Maison de l'Italie", "Maria Chiara PRODI", "Italien", "7 A Bd Jourdan, 75014 Paris",LISTE_CAPACITE_CHAMBRE_MAISON_METUDIANTE2);
		MaisonEtudiante mEtudiante3 = new MaisonEtudiante(ciup, "Collège franco-britannique", "Corinne NATIVEL", "Britannique", "9 B Bd Jourdan, 75014 Paris",LISTE_CAPACITE_CHAMBRE_MAISON_METUDIANTE3);
		MaisonEtudiante mEtudiante4 = new MaisonEtudiante(ciup, "Maison du Japon", "Naoki KANAYAMA", "Japonais", "7 C Av. Rockefeller, 75014 Paris",LISTE_CAPACITE_CHAMBRE_MAISON_METUDIANTE4);
		
		ciup.ajouterMaison(mEtudiante1);
		ciup.ajouterMaison(mEtudiante2);
		ciup.ajouterMaison(mEtudiante3);
		ciup.ajouterMaison(mEtudiante4);
		
		Etudiant etudiant1 = new Etudiant(ciup,"Douglas", "Alfred", "Français", mEtudiante1);
		Etudiant etudiant2 = new Etudiant(ciup,"Crude", "Donatien", "Français", mEtudiante1);
		Etudiant etudiant3 = new Etudiant(ciup,"Nolan", "Louis", "Italien", mEtudiante1);
		Etudiant etudiant9 = new Etudiant(ciup,"Gostier", "Gerard", "Allemand", mEtudiante1);
		Etudiant etudiant4 = new Etudiant(ciup,"Delafosse", "Nathalie", "Français", mEtudiante3);
		Etudiant etudiant5 = new Etudiant(ciup,"Johnson", "Dave", "Américain", mEtudiante3);
		Etudiant etudiant6 = new Etudiant(ciup,"Miles", "Adam", "Britannique", mEtudiante3);
		Etudiant etudiant7 = new Etudiant(ciup,"Tele", "Kent", "Japonais", mEtudiante4);
		Etudiant etudiant8 = new Etudiant(ciup,"Bayashi", "Kentaro", "Japonais", mEtudiante4);
		
		Soiree soiree1 = new Soiree("Soirée Salsa", "Ambiance latino festive", LocalDateTime.now().plusDays(1));
		Soiree soiree2 = new Soiree("Soirée Cinéma", "Projection de films cultes en plein air", LocalDateTime.now().plusDays(2));
		Soiree soiree3 = new Soiree("Soirée Déguisée", "Concours de costumes et DJ toute la nuit", LocalDateTime.now().plusDays(3));
		Soiree soiree4 = new Soiree("Soirée Karaoké", "Chantez vos tubes préférés entre amis", LocalDateTime.now().plusDays(4));
		Soiree soiree5 = new Soiree("Soirée Pyjama", "Projection de films courts", LocalDateTime.now().plusDays(5));
				
        mEtudiante1.ajouterSoiree(soiree2);
        mEtudiante1.ajouterSoiree(soiree3);
        mEtudiante2.ajouterSoiree(soiree4);
        mEtudiante3.ajouterSoiree(soiree5);
        mEtudiante4.ajouterSoiree(soiree1);
         
        soiree1.inscrireEtudiantSoiree(etudiant7);
        soiree1.inscrireEtudiantSoiree(etudiant8);
        soiree2.inscrireEtudiantSoiree(etudiant1);
        soiree3.inscrireEtudiantSoiree(etudiant2);
        soiree4.inscrireEtudiantSoiree(etudiant3);
        soiree5.inscrireEtudiantSoiree(etudiant6);
        
     // Affichage des maisons
        System.out.println("----- MAISONS -----");
        for (Maison maison : ciup.getListeMaisonsEtudiantes()) {
            System.out.println(maison);
        }

        // Affichage des étudiants
        System.out.println("----- ETUDIANTS -----");
        for (Maison maison : ciup.getListeMaisonsEtudiantes()) {
            if (maison instanceof MaisonEtudiante) {
                MaisonEtudiante me = (MaisonEtudiante) maison;
                for (Etudiant e : me.getListeEtudiante()) {
                    System.out.println(e);
                }
            }
        }

        // Affichage des soirées
        System.out.println("----- SOIREES -----");
        for (Maison maison : ciup.getListeMaisonsEtudiantes()) {
            if (maison instanceof MaisonEtudiante) {
                MaisonEtudiante me = (MaisonEtudiante) maison;
                for (Soiree s : me.getAgenda()) {
                    System.out.println(s);
                }
            }
        }
  
        
		return ciup;
	}

}
