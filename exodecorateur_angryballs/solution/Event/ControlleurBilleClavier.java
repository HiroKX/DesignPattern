package exodecorateur_angryballs.solution.Event;

import exodecorateur_angryballs.solution.decorateur.DecorateurSelectionnable;
import exodecorateur_angryballs.solution.state.AttrapeState;
import exodecorateur_angryballs.solution.state.InitialState;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
    Cette classe permet de contrôler une b sélectionnée avec les touches du clvier
 */
public class ControlleurBilleClavier extends ControlleurType implements KeyListener, MouseListener {
    /** b contrôlée */
    protected ControlleurEtat controlleurCourant;
    /** Etat attrapé */
    protected AttrapeState etatAttrape;

    protected double puissance;


    /** Etat initial */
    protected InitialState etatInit;

    public ControlleurBilleClavier(DecorateurSelectionnable b){
        this.b = b;
        this.tenue=false;
        this.puissance = 0.07;
        this.etatInit = new InitialState(this,null,null);
        this.etatAttrape = new AttrapeState(this,this.etatInit,this.etatInit);
        this.etatInit._suivant = this.etatAttrape;
        this.etatInit._retour = this.etatAttrape;
        this.controlleurCourant = this.etatInit;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        this.traiteKeyTyped(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.traiteKeyTyped(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.traiteKeyTyped(e);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /** permet de choisir une b en cliquant sur celle-ci */
    @Override
    public void mousePressed(MouseEvent e) {
        if(this.b != null) {
            if(this.tenue){
                this.tenue = !mesmaths.geometrie.base.Geop.appartientDisque(new Vecteur(e.getX(), e.getY()), b.getPosition(), b.getRayon());
            }else{
                this.tenue = mesmaths.geometrie.base.Geop.appartientDisque(new Vecteur(e.getX(), e.getY()), b.getPosition(), b.getRayon());
            }
            if(this.tenue)
                this.controlleurCourant = this.etatAttrape;
            else
                this.controlleurCourant = this.etatInit;
        }
    }

    public void traiteKeyTyped(KeyEvent e){
        this.oldVec=Vecteur.VECTEURNUL;
        Vecteur v = new Vecteur(0,0);
        if(e.getKeyChar() == 'z' || e.getKeyCode()==KeyEvent.VK_UP)
            v.y-=puissance;
        if(e.getKeyChar() == 'Z' || e.getKeyCode()==KeyEvent.VK_UP)
            v.y-=puissance;
        if(e.getKeyChar() == 's' || e.getKeyCode()==KeyEvent.VK_DOWN)
            v.y+=puissance;
        if(e.getKeyChar() == 'S' || e.getKeyCode()==KeyEvent.VK_DOWN)
            v.y+=puissance;

        if(e.getKeyChar() == 'q' || e.getKeyCode()==KeyEvent.VK_LEFT)
            v.x-=puissance;
        if(e.getKeyChar() == 'Q' || e.getKeyCode()==KeyEvent.VK_LEFT)
            v.x-=puissance;
        if(e.getKeyChar() == 'd' || e.getKeyCode()==KeyEvent.VK_RIGHT)
            v.x+=puissance;
        if(e.getKeyChar() == 'D' || e.getKeyCode()==KeyEvent.VK_RIGHT)
            v.x+=puissance;
        this.controlleurCourant.traiteGeneral(v);
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyChar() == 'w' || e.getKeyChar() == 'W'){
            this.b.setPosition(new Vecteur(this.b.getPosition().x,this.b.getPosition().y-100));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void attraper(Vecteur baseVecteur) {

    }

    @Override
    public void relacher(Vecteur endVecteur) {

    }

    @Override
    public void dragged(Vecteur newVecteur) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
