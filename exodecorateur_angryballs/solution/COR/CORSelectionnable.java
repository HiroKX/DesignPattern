package exodecorateur_angryballs.solution.COR;

import exodecorateur_angryballs.solution.decorateur.DecorateurSelectionnable;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.visiteur.DessineParticulariteBille;

import static exodecorateur_angryballs.solution.decorateur.DecorateurBille.isDecoratedBy;

public class CORSelectionnable extends COR{


    public CORSelectionnable(COR suivant, DessineParticulariteBille visiteur) {
        super(suivant, visiteur);
    }

    @Override
    public Boolean dessine(Bille b) {
        DecorateurSelectionnable deco = null;
        if((deco = (DecorateurSelectionnable) isDecoratedBy(b, DecorateurSelectionnable.class))!=null){
            this.visiteur.visit(deco);
            return true;
        }
        return null;
    }
}
