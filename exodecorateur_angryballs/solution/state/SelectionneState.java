package exodecorateur_angryballs.solution.state;

import exodecorateur_angryballs.solution.Event.ControlleurEtat;
import exodecorateur_angryballs.solution.Event.ControlleurAttraper;
import mesmaths.geometrie.base.Vecteur;

/**
 * Etat de selection
 */
public class SelectionneState extends ControlleurEtat {

    public SelectionneState(ControlleurAttraper controlleurGeneral, ControlleurEtat suivant, ControlleurEtat retour) {
        super(controlleurGeneral, suivant, retour);
    }

    @Override
    public void traiteGeneral(Vecteur newVecteur) {
        this.controleur.oldVec = newVecteur;
        this.controleur.setControlleurCourant(this._suivant);
    }
}

