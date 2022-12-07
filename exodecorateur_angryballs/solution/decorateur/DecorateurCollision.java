package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.OutilsBille;

import java.util.Vector;

public class DecorateurCollision extends DecorateurBille{
    public DecorateurCollision(Bille b) {
        super(b);
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes)  {
        return OutilsBille.gestionCollisionBilleBille(this, billes);
    }
}
