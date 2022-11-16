package exodecorateur_angryballs.maladroit.state;

import exodecorateur_angryballs.maladroit.Event.ControlleurEtat;
import exodecorateur_angryballs.maladroit.Event.ControlleurGeneral;
import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.util.stream.StreamSupport;

public class AttrapeState extends ControlleurEtat {


    public AttrapeState(ControlleurGeneral controlleurGeneral, ControlleurEtat suivant, ControlleurEtat retour) {
        super(controlleurGeneral, suivant, retour);
    }

    @Override
    public void traiteGeneral(Vecteur newVecteur) {
        Vecteur vecteurSouris = newVecteur.difference(this._controleurGeneral.oldVec);
        double masse = this._controleurGeneral.b.masse();
        double sensibilite = 5E1;
        vecteurSouris = vecteurSouris.produit((1./masse)*sensibilite);
        this._controleurGeneral.b.getAcceleration().ajoute(vecteurSouris);
        this._controleurGeneral.oldVec = newVecteur;
    }
}
