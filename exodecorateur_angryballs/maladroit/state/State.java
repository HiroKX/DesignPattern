package exodecorateur_angryballs.maladroit.state;

import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

public abstract class State {

    protected Bille b;

    public State(Bille b){
        this.b=b;
    }

    public abstract void traiterCasGeneral(Vecteur v);

}
