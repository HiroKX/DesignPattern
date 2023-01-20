package exodecorateur_angryballs.solution.Event;

import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class ControlleurType implements MouseMotionListener, MouseListener, KeyListener {
    protected ControlleurEtat controlleurCourant;

    public Bille b;
    /** Vieux vecteur */
    public Vecteur oldVec;

    protected boolean tenue;
    protected Vecteur influence;


    public void setControlleurCourant(ControlleurEtat ce){
        this.controlleurCourant = ce;
    }

    public abstract void attraper(Vecteur baseVecteur);

    public abstract void relacher(Vecteur endVecteur);

    public abstract void dragged(Vecteur newVecteur);

    public boolean getTenue(){
        return this.tenue;
    }
    public Vecteur getInfluence(){
        return this.influence;
    }
}
