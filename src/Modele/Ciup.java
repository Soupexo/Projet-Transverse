package Modele;

import java.util.*;
import java.io.*;

/**
 * Représente la Cité Internationale Universitaire de Paris (CIUP),
 * composée d'une maison internationale et d'une liste de maisons étudiantes.
 * Cette classe permet de gérer l'affectation des étudiants aux maisons selon leur nationalité,
 * ainsi que diverses opérations sur les maisons et les étudiants.
 * @author Otman Benbouziane
 */
public class Ciup implements Serializable {

    //--------------------------
    //         ATTRIBUTS
    //--------------------------

    /** La maison internationale de la CIUP */
    private MaisonInternationale maisonInternationale;

    /** Liste des maisons étudiantes de la CIUP */
    private List<MaisonEtudiante> listeMaisonsEtudiantes;

    /** UID de version pour la sérialisation */
    private static final long serialVersionUID = 1L;

    //--------------------------
    //       CONSTRUCTEUR
    //--------------------------

    /**
     * Construit une instance de la CIUP avec une maison internationale donnée.
     * Initialise la liste des maisons étudiantes à vide.
     * Associe la maison internationale à cette CIUP.
     *
     * @param maisonInternationale la maison internationale à associer à la CIUP
     */
    public Ciup(MaisonInternationale maisonInternationale) {
        this.maisonInternationale = maisonInternationale;
        maisonInternationale.setCiup(this);
        this.listeMaisonsEtudiantes = new ArrayList<MaisonEtudiante>();
    }

    //--------------------------
    //         METHODES
    //--------------------------

    /**
     * Ajoute une maison étudiante à la CIUP si elle n'est pas déjà présente.
     *
     * @param maison la maison étudiante à ajouter
     */
    public void ajouterMaison(MaisonEtudiante maison) {
        if (!listeMaisonsEtudiantes.contains(maison)) {
            listeMaisonsEtudiantes.add(maison);
        }
    }

    /**
     * Supprime une maison étudiante de la CIUP.
     *
     * @param maison la maison étudiante à supprimer
     */
    public void supprimerMaison(MaisonEtudiante maison) {
        listeMaisonsEtudiantes.remove(maison);
    }

    /**
     * Affecte un étudiant à une maison étudiante selon sa nationalité.
     * Si aucune maison correspondant à la nationalité n'est trouvée,
     * l'étudiant est affecté de manière équitable dans une maison.
     *
     * @param etudiant l'étudiant à affecter
     */
    public void affecterMaisonNationale(Etudiant etudiant) {
        for (MaisonEtudiante maison : listeMaisonsEtudiantes) {
            if (maison.getNationalite() != null && maison.getNationalite().equals(etudiant.getNationalite())) {
                maison.inscrireEtudiant(etudiant);
                return;
            }
        }
        affecterMaisonEquitablement(etudiant);
    }

    /**
     * Calcule le pourcentage d'étudiants d'une nationalité donnée dans une maison.
     *
     * @param maison la maison concernée
     * @param index l'index de la nationalité dans la liste des nationalités de la maison
     * @return le pourcentage d'étudiants de cette nationalité dans la maison (0 si index invalide)
     */
    public double calculePourcentageNationaliteDansMaison(MaisonEtudiante maison, int index) {
        int nbTotalEtudiantMaison = maison.getNbEtudiants();
        if (index != -1 && nbTotalEtudiantMaison > 0) {
            int nbEtudiantNationalite = maison.getListeNbEtudiantParNationalite().get(index);
            return ((double) nbEtudiantNationalite * 100) / nbTotalEtudiantMaison;
        } else {
            return 0.00;
        }
    }

    /**
     * Affecte un étudiant à une maison où sa nationalité est minoritaire (≤ 50%).
     * Si aucune maison ne remplit cette condition, l'étudiant est affecté à la première maison.
     *
     * @param etudiant l'étudiant à affecter
     */
    public void affecterMaisonEquitablement(Etudiant etudiant) {
        int indexParDefaut = 0;
        for (int cpt = 0; cpt < listeMaisonsEtudiantes.size(); cpt++) {
            MaisonEtudiante maison = listeMaisonsEtudiantes.get(cpt);
            int index = maison.getListeNationalites().indexOf(etudiant.getNationalite());

            double pourcentageComparaison = calculePourcentageNationaliteDansMaison(maison, index);
            if (pourcentageComparaison <= 50.00) {
                maison.inscrireEtudiant(etudiant);
                return;
            } else if (pourcentageComparaison > 50.00 && cpt == listeMaisonsEtudiantes.size() - 1) {
                listeMaisonsEtudiantes.get(indexParDefaut).inscrireEtudiant(etudiant);
            }
        }
    }

    /**
     * Recherche une maison étudiante par son nom (recherche insensible à la casse).
     *
     * @param nomRecherche le nom de la maison à rechercher
     * @return la maison étudiante correspondante, ou null si aucune n'est trouvée
     */
    public MaisonEtudiante getMaisonParNom(String nomRecherche) {
        for (MaisonEtudiante maison : this.listeMaisonsEtudiantes) {
            if (maison.getNom() != null && maison.getNom().equalsIgnoreCase(nomRecherche)) {
                return maison;
            }
        }
        return null;
    }

    //--------------------------
    //        ACCESSEURS
    //--------------------------

    /**
     * Retourne la maison internationale de la CIUP.
     *
     * @return la maison internationale
     */
    public MaisonInternationale getMaisonInternationale() {
        return maisonInternationale;
    }

    /**
     * Définit la maison internationale de la CIUP.
     * Met à jour également la référence à la CIUP dans la maison internationale.
     *
     * @param maisonInternationale la nouvelle maison internationale
     */
    public void setMaisonInternationale(MaisonInternationale maisonInternationale) {
        this.maisonInternationale = maisonInternationale;
        maisonInternationale.setCiup(this);
    }

    /**
     * Retourne la liste des maisons étudiantes de la CIUP.
     *
     * @return la liste des maisons étudiantes
     */
    public List<MaisonEtudiante> getListeMaisonsEtudiantes() {
        return listeMaisonsEtudiantes;
    }

    /**
     * Définit la liste des maisons étudiantes de la CIUP.
     *
     * @param listeMaisonsEtudiantes la nouvelle liste des maisons étudiantes
     */
    public void setListeMaisonsEtudiantes(List<MaisonEtudiante> listeMaisonsEtudiantes) {
        this.listeMaisonsEtudiantes = listeMaisonsEtudiantes;
    }

    /**
     * Calcule le nombre total d'étudiants dans toutes les maisons étudiantes.
     *
     * @return le nombre total d'étudiants
     */
    public int getNbEtudiants() {
        int nbEtudiants = 0;
        for (MaisonEtudiante maison : listeMaisonsEtudiantes) {
            nbEtudiants += maison.getNbEtudiants();
        }
        return nbEtudiants;
    }

    /**
     * Calcule le nombre total de chambres dans toutes les maisons étudiantes.
     *
     * @return le nombre total de chambres
     */
    public int getNbChambres() {
        int nbChambres = 0;
        for (MaisonEtudiante maison : listeMaisonsEtudiantes) {
            nbChambres += maison.getNbChambres();
        }
        return nbChambres;
    }

    //--------------------------
    //           MAIN
    //--------------------------

    /**
     * Méthode principale de test : construit une CIUP via la factory.
     *
     * @param args arguments en ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        Factory fac = new Factory();
        fac.constructionCiup();
    }
}
