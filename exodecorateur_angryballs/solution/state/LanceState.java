package exodecorateur_angryballs.solution.state;

import exodecorateur_angryballs.solution.Event.ControlleurEtat;
import exodecorateur_angryballs.solution.Event.ControlleurType;
import mesmaths.geometrie.base.Vecteur;

/**
 * Etat dans lequel la bille est attrapée par la souris
 */
public class LanceState extends ControlleurEtat {

    public LanceState(ControlleurType controlleurGeneral, ControlleurEtat suivant, ControlleurEtat retour) {
        super(controlleurGeneral, suivant, retour);
    }

    @Override
    public void traiteGeneral(Vecteur newVecteur) {
        System.out.println("lance");
        Vecteur vecteurSouris = newVecteur;
        double masse = this.controleur.b.masse();
        double sensibilite = 2E1;
        vecteurSouris = vecteurSouris.produit((1./masse)*sensibilite);
        this.controleur.b.getAcceleration().ajoute(vecteurSouris);
        this.controleur.oldVec = newVecteur;
    }
}
