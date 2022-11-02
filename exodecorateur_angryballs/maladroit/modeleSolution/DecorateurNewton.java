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
        System.out.println("Clef : "+ this.getClef()+"  "+ this.getAcceleration());
        System.out.println("1"+this.getAcceleration());
        this.bille.gestionAcceleration(billes);
        this.getAcceleration().set(OutilsBille.gestionAccelerationNewton(this.bille, billes));     // contribution de l'acceleration due à l'attraction des autres billes
        System.out.println("2"+this.getAcceleration());
        //this.bille.gestionAcceleration(billes);
        System.out.println("3"+this.getAcceleration());

    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {
    }
}
