package exodecorateur_angryballs.solution.state;

import exodecorateur_angryballs.solution.Event.ControlleurEtat;
import exodecorateur_angryballs.solution.Event.ControlleurGeneral;
import mesmaths.geometrie.base.Vecteur;

/** Etat initial */
public class InitialState extends ControlleurEtat {

    public InitialState(ControlleurGeneral controlleurGeneral, ControlleurEtat suivant, ControlleurEtat retour) {
        super(controlleurGeneral, suivant, retour);
    }

    @Override
    public void traiteGeneral(Vecteur newVecteur) {
        this._controleurGeneral.oldVec = newVecteur;
        this._controleurGeneral.setControlleurCourant(this._suivant);
    }
}
