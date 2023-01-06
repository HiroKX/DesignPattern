package exodecorateur_angryballs.solution.Event;

import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class ControlleurType implements MouseMotionListener, MouseListener {
    protected ControlleurEtat controlleurCourant;

    public Bille b;
    /** Vieux vecteur */
    public Vecteur oldVec;

    boolean attraper;


    public void setControlleurCourant(ControlleurEtat ce){
        this.controlleurCourant = ce;
    }

    public abstract void attraper(Vecteur baseVecteur);

    public abstract void relacher(Vecteur endVecteur);

    public abstract void dragged(Vecteur newVecteur);



}
