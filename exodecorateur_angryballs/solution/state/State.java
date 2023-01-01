package exodecorateur_angryballs.solution.state;

import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

/** DP State */
public abstract class State {

    /** la bille associée à cet état */
    protected Bille b;

    public State(Bille b){
        this.b=b;
    }

    public abstract void traiterCasGeneral(Vecteur v);

}
