package exodecorateur_angryballs.solution.COR;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.visiteur.DessineParticulariteBille;

public abstract class COR {
    COR suivant;
    DessineParticulariteBille visiteur;

    public COR (COR suivant,DessineParticulariteBille visiteur){
        this.visiteur = visiteur;
        this.suivant = suivant;
    }


    protected abstract Boolean dessine(Bille b);

    public boolean resoudre(Bille b) {//Ou autre
        Boolean res = null;
        res = this.dessine(b);
        if (res != null) {
            return res;
        }else {
            if (this.suivant != null)
                return this.suivant.resoudre(b);
            else
                return false;
        }
    }
}
