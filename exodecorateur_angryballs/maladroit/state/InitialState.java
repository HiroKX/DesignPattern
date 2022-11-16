package exodecorateur_angryballs.maladroit.state;

import exodecorateur_angryballs.maladroit.Event.ControlleurEtat;
import exodecorateur_angryballs.maladroit.Event.ControlleurGeneral;
import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

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
