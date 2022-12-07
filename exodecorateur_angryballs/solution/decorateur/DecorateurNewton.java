package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.OutilsBille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class DecorateurNewton extends DecorateurBille{
    public DecorateurNewton(Bille b) {
        super(b);
    }

    @Override
    public Vecteur gestionAcceleration(Vector<Bille> billes)
    {
        super.gestionAcceleration(billes);
        this.getAcceleration().ajoute(OutilsBille.gestionAccelerationNewton(this.bille, billes));     // contribution de l'acceleration due à l'attraction des autres billes
        return this.getAcceleration();
    }

}
