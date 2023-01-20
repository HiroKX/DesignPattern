package exodecorateur_angryballs.solution.COR;

import exodecorateur_angryballs.solution.decorateur.DecorateurAttraper;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.visiteur.DessineParticulariteBille;

import static exodecorateur_angryballs.solution.decorateur.DecorateurBille.isDecoratedBy;

public class CORAttraper extends COR{


    public CORAttraper(COR suivant, DessineParticulariteBille visiteur) {
        super(suivant, visiteur);
    }

    @Override
    public Boolean dessine(Bille b) {
        DecorateurAttraper deco = null;
        if((deco = (DecorateurAttraper) isDecoratedBy(b, DecorateurAttraper.class))!=null){
            this.visiteur.visit(deco);
            return true;
        }
        return null;
    }
}
