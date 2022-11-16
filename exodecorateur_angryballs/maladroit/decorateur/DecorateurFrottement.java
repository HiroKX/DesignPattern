package exodecorateur_angryballs.maladroit.decorateur;

import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

import java.util.Vector;

public class DecorateurFrottement extends DecorateurBille {

    double coeff;
    public DecorateurFrottement(Bille b,double coeff) {
        super(b);
        this.coeff = coeff;
    }

    @Override
    public Vecteur gestionAcceleration(Vector<Bille> billes)
    {
        super.gestionAcceleration(billes);
        this.getAcceleration().ajoute(MecaniquePoint.freinageFrottement(this.masse(), this.getVitesse(),coeff)); // contribution de l'acceleration due au frottement dans l'air
        return this.getAcceleration();
    }


}
