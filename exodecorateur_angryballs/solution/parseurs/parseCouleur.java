package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurCouleur;
import exodecorateur_angryballs.solution.modele.Bille;

import java.awt.*;
import java.lang.reflect.Field;

public class parseCouleur extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(ligne.isEmpty())
            return bille;
        Field field = Class.forName("java.awt.Color").getField(ligne);
        Color color = (Color)field.get(null);
        return new DecorateurCouleur(bille, color);
    }
}