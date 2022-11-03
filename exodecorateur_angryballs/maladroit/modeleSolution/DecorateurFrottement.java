package exodecorateur_angryballs.maladroit.modeleSolution;

import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.mecanique.MecaniquePoint;

import java.util.Vector;

public class DecorateurFrottement extends DecorateurBille {

    public DecorateurFrottement(Bille b) {
        super(b);
    }

    @Override
    public void gestionAcceleration(Vector<Bille> billes)
    {
        this.bille.gestionAcceleration(billes);
        this.getAcceleration().set(MecaniquePoint.freinageFrottement(this.masse(), this.getVitesse())); // contribution de l'acceleration due au frottement dans l'air
    }


    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {
        this.bille.collisionContour(abscisseCoinHautGauche,ordonneeCoinHautGauche,largeur,hauteur);
    }
}
