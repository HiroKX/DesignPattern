package exodecorateur_angryballs.solution.parseurs.couleur;

import java.awt.*;
import java.lang.reflect.Field;

public class parseCouleurString extends parseCouleurCOR {
    public parseCouleurString(parseCouleurCOR suivant) {
        super(suivant);
    }

    @Override
    public Color parse(String couleur){
        try {
            Field field = Class.forName("java.awt.Color").getField(couleur);
            return (Color)field.get(null);
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            if(this.suivant == null)
                return Color.white;
            return null;
        }
    }
}
