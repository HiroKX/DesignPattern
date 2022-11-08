package exodecorateur_angryballs.maladroit.decorateur;

import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

public class MouseFollower implements MouseMotionListener, MouseListener {

    Vector<Bille> billes;

    Vecteur oldVec;

    int largeur;
    int hauteur;

    boolean hold;

    Bille b;

    public MouseFollower(Vector<Bille> billes, int largeur, int hauteur) {
        this.billes = billes;
        this.largeur = largeur;
        this.hauteur = hauteur;
        hold = false;
        this.b = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (b == null) {
            for (Bille b : billes) {
                if (mesmaths.geometrie.base.Geop.appartientDisque(new Vecteur(e.getX(), e.getY()), b.getPosition(), b.getRayon())) {

                    //System.out.println("j'tattrape");
                    oldVec = new Vecteur(e.getX(), e.getY());
                    this.b = b;
                    this.b.attraper();
                    return;
                }
            }
        }

        this.b = null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.b = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    /* gravité de la souris
        if (!mesmaths.geometrie.base.Geop.appartientDisque(new Vecteur(e.getX(), e.getY()), b.getPosition(), b.getRayon())) {
            Vecteur var = mesmaths.mecanique.MecaniquePoint.champGravitéGlobal(new Vecteur(e.getX(), e.getY()), new double[]{b.masse()*100}, new Vecteur[]{b.getPosition()},2.0E-3);
            b.getPosition().retire(var);
        }*/

        /** Suis la souris sans masse
        Vecteur var = new Vecteur((e.getX() - oldVec.x) / 3E3, (e.getY() - oldVec.y) / 3E3);
        System.out.println(var);
        if(var == Vecteur.VECTEURNUL){
            this.b.attraper();
        }else {
            this.b.relacher(var);
        }
*/

        Vecteur var = new Vecteur((e.getX() - oldVec.x) * (1/b.masse()*2E1), (e.getY() - oldVec.y) * (1/b.masse()*5E2));
        System.out.println(var);
        if(var == Vecteur.VECTEURNUL){
            this.b.attraper();
        }else {
            this.b.relacher(var);
        }
        oldVec = new Vecteur(e.getX(), e.getY());

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("aaaadazdzadza" + oldVec);
    }
}
