package exodecorateur_angryballs.solution.parseurs.couleur;

import java.awt.*;

/**
    Implementation d'un COR pour la couleur afin que le programme puisse comprendre plusieurs formats de couleur
    à savoir: Int, RGB et String
 */
public abstract class parseCouleurCOR {
    /** Le successeur de la chaine de responsabilité */
    parseCouleurCOR suivant;
    /** La couleur à retourner */
    Color couleur;

    public parseCouleurCOR (parseCouleurCOR suivant){
        this.suivant = suivant;
    }

    public static Color CouleurCOR(String couleur){
        parseCouleurCOR parseCouleurString = new parseCouleurString(null);
        parseCouleurCOR parseCouleurRGB = new parseCouleurRGB(parseCouleurString);
        parseCouleurCOR parseCouleurInt = new parseCouleurInt(parseCouleurRGB);
        return parseCouleurInt.resoudre(couleur);
    }

    public abstract Color parse(String couleur);

    public Color resoudre(String s) {//Ou autre
        this.couleur = null;
        this.couleur = this.parse(s);
        if (this.couleur != null)
            return this.couleur;
        else {
            if (this.suivant != null)
                return this.suivant.resoudre(s);
            else
                return this.couleur;
        }
    }
}
