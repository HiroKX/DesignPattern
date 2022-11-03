package exodecorateur_angryballs.maladroit.modeleSolution;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.modele.OutilsBille;

import java.util.Vector;

public class DecorateurNewton extends DecorateurBille{
    public DecorateurNewton(Bille b) {
        super(b);
    }

    @Override
    public void gestionAcceleration(Vector<Bille> billes)
    {
        this.bille.gestionAcceleration(billes);
        this.getAcceleration().set(OutilsBille.gestionAccelerationNewton(this.bille, billes));     // contribution de l'acceleration due à l'attraction des autres billes

    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {
    }
}
