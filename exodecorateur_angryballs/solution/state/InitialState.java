package exodecorateur_angryballs.solution.state;

import exodecorateur_angryballs.solution.Event.ControlleurEtat;
import exodecorateur_angryballs.solution.Event.ControlleurType;
import mesmaths.geometrie.base.Vecteur;

/** Etat initial */
public class InitialState extends ControlleurEtat {

    public InitialState(ControlleurType controlleur, ControlleurEtat suivant, ControlleurEtat retour) {
        super(controlleur, suivant, retour);
    }

    @Override
    public void traiteGeneral(Vecteur newVecteur) {
        this.controleur.oldVec = newVecteur;
        this.controleur.setControlleurCourant(this._suivant);
    }

    public String __toString(){
        return "InitState";
    }
}
