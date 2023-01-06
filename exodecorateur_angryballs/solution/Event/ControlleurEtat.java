package exodecorateur_angryballs.solution.Event;

import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseEvent;

/** Contrôleur de la souris pour le changement d'état de la bille
 */
public abstract class ControlleurEtat {
    /** Controleur */
    public ControlleurType controleur;
    /** Controleurs d'état */
    public ControlleurEtat _suivant, _retour;
    public ControlleurEtat(ControlleurType controlleurGeneral, ControlleurEtat suivant, ControlleurEtat retour){
        super();
        this.controleur =controlleurGeneral;
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
        if (mesmaths.geometrie.base.Geop.appartientDisque(new Vecteur(newVecteur.x, newVecteur.y), this.controleur.b.getPosition(), this.controleur.b.getRayon())) {
            this.controleur.attraper(newVecteur);
        }
    }

    private void eventReleased(Vecteur v){
        this.controleur.relacher(v);
    }


    private void eventDragged(Vecteur newVecteur){
        this.controleur.dragged(newVecteur);
    }

    public abstract void traiteGeneral(Vecteur newVecteur);
}
