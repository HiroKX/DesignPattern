package exodecorateur_angryballs.solution.COR;

import exodecorateur_angryballs.solution.decorateur.DecorateurLance;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.visiteur.DessineParticulariteBille;

import static exodecorateur_angryballs.solution.decorateur.DecorateurBille.isDecoratedBy;

public class CORLance extends COR{

    public CORLance(COR suivant, DessineParticulariteBille visiteur) {
        super(suivant, visiteur);
    }

    @Override
    public Boolean dessine(Bille b) {
        DecorateurLance deco = null;
        if((deco = (DecorateurLance) isDecoratedBy(b, DecorateurLance.class))!=null){
            this.visiteur.visit(deco);
            return true;
        }
        return null;
    }
}
