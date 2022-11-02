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
        System.out.println("1"+this.getAcceleration());
        this.getAcceleration().ajoute(MecaniquePoint.freinageFrottement(this.masse(), this.getVitesse())); // contribution de l'acceleration due au frottement dans l'air

        System.out.println("2"+this.getAcceleration());
        super.gestionAcceleration(billes);
        //this.bille.gestionAcceleration(billes);
        System.out.println("3"+this.getAcceleration());
    }


    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {
        this.bille.collisionContour(abscisseCoinHautGauche,ordonneeCoinHautGauche,largeur,hauteur);
    }
}
