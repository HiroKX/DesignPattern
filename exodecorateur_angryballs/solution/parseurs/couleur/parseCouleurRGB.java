package exodecorateur_angryballs.solution.parseurs.couleur;

import java.awt.*;
import java.util.regex.PatternSyntaxException;

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

    private int nettoyer(String s){
        return Integer.parseInt(s.replaceAll("[^0-9]", "").trim());
    }
}
