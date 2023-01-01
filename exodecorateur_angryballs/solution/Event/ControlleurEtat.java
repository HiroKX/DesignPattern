package exodecorateur_angryballs.solution.Event;

import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseEvent;

/** Contrôleur de la souris pour le changement d'état de la bille
 */
public abstract class ControlleurEtat {
    /** Controleur */
    public ControlleurGeneral _controleurGeneral;
    /** Controleurs d'état */
    public ControlleurEtat _suivant, _retour;
    public ControlleurEtat(ControlleurGeneral controlleurGeneral, ControlleurEtat suivant, ControlleurEtat retour){
        super();
        this._controleurGeneral =controlleurGeneral;
        this._suivant = suivant;
        this._retour = retour;
    }

    public void mousePressed(MouseEvent e){
        eventPressed(new Vecteur(e.getX(), e.getY()));
    }

    public void mouseDraggeed(MouseEvent e){
        eventDragged(new Vecteur(e.getX(), e.getY()));
    }
    public void mouseReleased(MouseEvent e){
        eventReleased(new Vecteur(e.getX(), e.getY()));
    }

    private void eventPressed(Vecteur newVecteur){
        if (mesmaths.geometrie.base.Geop.appartientDisque(new Vecteur(newVecteur.x, newVecteur.y), this._controleurGeneral.b.getPosition(), this._controleurGeneral.b.getRayon())) {
            this._controleurGeneral.b.attrape();
            this.traiteGeneral(newVecteur);
        }
    }

    private void eventReleased(Vecteur v){
        if(this._controleurGeneral.b.isAttraper()){
            this._controleurGeneral.b.relacher();
            this._controleurGeneral.setControlleurCourant(this._suivant);
        }
    }


    private void eventDragged(Vecteur newVecteur){
        if(this._controleurGeneral.b.isAttraper()){
            this.traiteGeneral(newVecteur);
        }
    }

    public abstract void traiteGeneral(Vecteur newVecteur);
}
