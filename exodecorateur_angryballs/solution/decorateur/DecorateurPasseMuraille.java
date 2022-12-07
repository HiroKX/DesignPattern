package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.cinematique.Collisions;

public class DecorateurPasseMuraille extends DecorateurBille{

    public DecorateurPasseMuraille(Bille b) {
        super(b);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche,
                                 double ordonneeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourPasseMuraille(this.getPosition(), abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
    }
}
