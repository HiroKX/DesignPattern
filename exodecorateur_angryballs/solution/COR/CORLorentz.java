package exodecorateur_angryballs.solution.COR;

import exodecorateur_angryballs.solution.decorateur.DecorateurLorentz;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.visiteur.DessineParticulariteBille;

import static exodecorateur_angryballs.solution.decorateur.DecorateurBille.isDecoratedBy;

public class CORLorentz extends COR{
    public CORLorentz(COR suivant, DessineParticulariteBille visiteur) {
        super(suivant, visiteur);
    }

    @Override
    public Boolean dessine(Bille b) {
        DecorateurLorentz deco = null;

        if((deco = (DecorateurLorentz) isDecoratedBy(b, DecorateurLorentz.class))!=null){

            this.visiteur.visit(deco);
            return true;
        }
        return null;
    }
}
