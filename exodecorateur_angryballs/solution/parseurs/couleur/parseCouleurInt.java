package exodecorateur_angryballs.solution.parseurs.couleur;

import java.awt.*;

public class parseCouleurInt extends parseCouleurCOR {
    public parseCouleurInt(parseCouleurCOR suivant) {
        super(suivant);
    }

    @Override
    public Color parse(String couleur) {
        if(couleur.matches("[0-9]+"))
            return new Color(Integer.parseInt(couleur));
        return null;
    }
}
