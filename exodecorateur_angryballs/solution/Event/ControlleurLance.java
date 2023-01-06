package exodecorateur_angryballs.solution.Event;

import exodecorateur_angryballs.solution.decorateur.DecorateurLance;
import exodecorateur_angryballs.solution.state.InitialState;
import exodecorateur_angryballs.solution.state.LanceState;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/** Controleur génaral de la souris
 */
public class ControlleurLance extends ControlleurType implements MouseMotionListener, MouseListener {
    /**
     * Controleur d'état courant
     */
    protected ControlleurEtat controlleurCourant;
    /**
     * Etat attrapé
     */
    protected LanceState etatAttrape;

    /**
     * Etat initial
     */
    protected InitialState etatInit;

    public ControlleurLance(DecorateurLance b) {
        this.b = b;
        this.etatInit = new InitialState(this, null, null);
        this.etatAttrape = new LanceState(this, this.etatInit, this.etatInit);
        this.etatInit._suivant = this.etatAttrape;
        this.etatInit._retour = this.etatAttrape;
        this.controlleurCourant = this.etatInit;
    }
    public void setControlleurCourant(ControlleurEtat ce){
        this.controlleurCourant = ce;
    }

    @Override
    public void attraper(Vecteur baseVecteur) {
        this.oldVec = baseVecteur;
        this.attraper = true;
        this.controlleurCourant=this.etatAttrape;
    }

    @Override
    public void relacher(Vecteur endVecteur) {
            this.controlleurCourant.traiteGeneral(oldVec.difference(endVecteur));
            this.attraper = false;
            this.controlleurCourant=this.etatInit;
    }

    @Override
    public void dragged(Vecteur newVecteur) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        controlleurCourant.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        controlleurCourant.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controlleurCourant.mouseDraggeed(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
