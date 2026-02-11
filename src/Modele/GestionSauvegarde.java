package Modele;

import java.io.*;

/**
 * Classe abstraite pour la gestion de la sauvegarde et du chargement de l'état
 * de l'application CIUP (Cité Internationale Universitaire de Paris) à partir d'un fichier sérialisé.
 * @author Otman Benbouziane
 */
public abstract class GestionSauvegarde {
	
	 // --------------------------
    //       ATTRIBUT
    // --------------------------

    /** Nom du fichier utilisé pour la sauvegarde de l'objet Ciup */
    private static final String FICHIER_SAUVEGARDE = "ciup.ser";
    
    // --------------------------
    //       METHODE
    // --------------------------

    /**
     * Sauvegarde l'état de l'objet {@link Ciup} en le sérialisant dans un fichier.
     *
     * @param ciup l'objet CIUP à sauvegarder
     */
    public static void sauvegarder(Ciup ciup) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FICHIER_SAUVEGARDE))) {
            out.writeObject(ciup);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge un objet {@link Ciup} depuis un fichier de sauvegarde.
     * Si le fichier n'existe pas ou est illisible, retourne {@code null}.
     *
     * @return l'objet CIUP restauré, ou {@code null} en cas d'erreur
     */
    public static Ciup charger() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FICHIER_SAUVEGARDE))) {
            return (Ciup) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Aucune sauvegarde trouvée ou erreur de lecture.");
            return null;
        }
    }
}
