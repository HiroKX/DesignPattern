package exodecorateur_angryballs.maladroit.modeleSolution;

import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.cinematique.Collisions;

public class DecorateurRebond extends DecorateurBille {
    public DecorateurRebond(Bille b) {
        super(b);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche,
                                 double ordonneeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourAvecRebond(this.bille.getPosition(), this.bille.getRayon(), this.bille.getVitesse(), abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);

    }

    @Override
    public void deplacer(double deltaT) {
        this.bille.deplacer(deltaT);
    }
}
