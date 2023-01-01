package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.modele.Bille;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Classe qui permet de parser une ligne de fichier
 */
public class ParserCOR {
    /**
     * Crée la chaine de responsabilité de parseurs
     * @param attributs Liste des décorateurs dans l'odre de leur application
     * @param ligne Ligne à parser
     * @param bille Bille à décorer
     * @param args Arguments supplémentaires
     * @return Bille décorée
     */
    public static Bille Parser(String[] attributs, String ligne, Bille bille, Object[] args) throws Exception {
        ArrayList<Parser> parsers = new ArrayList<>();
        // Crée la chaîne de décoration
        for(int i = 2; i < attributs.length; i++) {
            try {
                parsers.add((Parser) Class.forName("exodecorateur_angryballs.solution.parseurs.parse"+attributs[i].substring(10)).getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        // On sépare les données de la ligne
        String[] donnesLigne = ligne.split(";");
        // On applique les décorateurs
        for(int i = 0; i< parsers.size();i++ ){
            try {
                bille = (Bille) parsers.get(i).parser(donnesLigne[i + 2], bille, args );
            } catch (Exception e) {
                bille = (Bille) parsers.get(i).parser("", bille, args);
            }
        }
        return bille;
    }
}
