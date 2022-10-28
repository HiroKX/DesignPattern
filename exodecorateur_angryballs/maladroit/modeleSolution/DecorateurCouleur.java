package exodecorateur_angryballs.maladroit.modeleSolution;

import exodecorateur_angryballs.maladroit.modele.Bille;

import java.awt.*;

public class DecorateurCouleur extends DecorateurBille {

    private Color couleur;

    public DecorateurCouleur(Bille b, Color couleur) {
        super(b);
        this.couleur = couleur;
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {

    }

    public String toString(){
        return this.bille.toString() + " couleur = "+ couleur;
    }
}
