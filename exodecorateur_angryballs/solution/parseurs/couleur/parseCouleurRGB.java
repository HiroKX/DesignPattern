package exodecorateur_angryballs.solution.parseurs.couleur;

import java.awt.*;
import java.util.regex.PatternSyntaxException;

/** Parse si la couleur est sous format R G B soit 3 entiers */
public class parseCouleurRGB extends parseCouleurCOR {
    public parseCouleurRGB(parseCouleurCOR suivant) {
        super(suivant);
    }

    @Override
    public Color parse(String couleur) {
        String[] rgb;
        try {
            rgb = couleur.split(",");
        } catch (PatternSyntaxException e) {
            return null;
        }
        if (rgb.length == 3) {
            try {
                return new Color(nettoyer(rgb[0]), nettoyer(rgb[1]), nettoyer(rgb[2]));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Nettoie la chaine de caractère pour enlever les espaces et les caractères spéciaux et retourner les entiers propres
     * @param s la chaine à nettoyer
     * @return la chaine nettoyée
     */
    private int nettoyer(String s){
        return Integer.parseInt(s.replaceAll("[^0-9]", "").trim());
    }
}
