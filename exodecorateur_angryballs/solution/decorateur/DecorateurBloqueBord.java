package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.cinematique.Collisions;

/**
    Ce décorateur empeche la bille de sortir du cadre, il crée une collision entre la bille et les bords du cadre
 */
public class DecorateurBloqueBord extends DecorateurBille{

    public DecorateurBloqueBord(Bille b) {
        super(b);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourAvecArretHorizontal(this.getPosition(), this.getRayon(), this.getVitesse(), abscisseCoinHautGauche, largeur);
        Collisions.collisionBilleContourAvecArretVertical(this.getPosition(), this.getRayon(), this.getVitesse(), ordonneeCoinHautGauche, hauteur);
    }
}
