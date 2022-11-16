package exodecorateur_angryballs.maladroit.Event;

import exodecorateur_angryballs.maladroit.decorateur.DecorateurAttraper;
import exodecorateur_angryballs.maladroit.state.AttrapeState;
import exodecorateur_angryballs.maladroit.state.InitialState;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ControlleurGeneral implements MouseMotionListener, MouseListener {

    public DecorateurAttraper b;

    public Vecteur oldVec;


    protected ControlleurEtat controlleurCourant;

    protected AttrapeState etatAttrape;

    protected InitialState etatInit;

    public ControlleurGeneral(DecorateurAttraper b){
        this.b = b;
        this.etatInit = new InitialState(this,null,null);
        this.etatAttrape = new AttrapeState(this,this.etatInit,this.etatInit);
        this.etatInit._suivant = this.etatAttrape;
        this.etatInit._retour = this.etatAttrape;
        this.controlleurCourant = this.etatInit;
    }


    public void setControlleurCourant(ControlleurEtat ce){
        this.controlleurCourant = ce;
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
