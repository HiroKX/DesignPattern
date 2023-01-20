package exodecorateur_angryballs.solution.state;

import exodecorateur_angryballs.solution.Event.ControlleurEtat;
import exodecorateur_angryballs.solution.Event.ControlleurType;
import mesmaths.geometrie.base.Vecteur;

/**
 * Etat dans lequel la bille est attrapée par la souris
 */
public class AttrapeState extends ControlleurEtat {


    public AttrapeState(ControlleurType controlleurGeneral, ControlleurEtat suivant, ControlleurEtat retour) {
        super(controlleurGeneral, suivant, retour);
    }

    @Override
    public void traiteGeneral(Vecteur newVecteur) {
        Vecteur vecteurSouris = newVecteur.difference(this.controleur.oldVec);
        double masse = this.controleur.b.masse();
        double sensibilite = 5E1;
        vecteurSouris = vecteurSouris.produit((1./masse)*sensibilite);
        this.controleur.b.getAcceleration().ajoute(vecteurSouris);
        this.controleur.oldVec = newVecteur;
    }

    public String __toString(){
        return "attraperState";
    }
}
