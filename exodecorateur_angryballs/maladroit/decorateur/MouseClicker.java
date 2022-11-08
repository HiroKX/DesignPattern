package exodecorateur_angryballs.maladroit.decorateur;

import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class MouseClicker implements MouseListener {

    Vector<Bille> billes;

    Vecteur oldVec;


    int largeur;
    int hauteur;


    Bille b;


    public MouseClicker(Vector<Bille> billes, int largeur, int hauteur) {
        this.billes = billes;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.b=null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Bille b : billes) {
            if (mesmaths.geometrie.base.Geop.appartientDisque(new Vecteur(e.getX(), e.getY()), b.getPosition(), b.getRayon())) {
                b.attraper();

                //System.out.println("j'tattrape");
                oldVec = b.getPosition();
                this.b = b;
                return;
            }
        }
        this.b=null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(b!=null) {
            double x,y;/**

            if(e.getX() > oldVec.x){
                if(e.getY() > oldVec.y){
                    x = e.getX()-oldVec.x;
                    y = e.getY()-oldVec.y;
                }else{
                    x = e.getX()-oldVec.x;
                    y = oldVec.y-e.getY();
                }
            }else{
                if(e.getY() > oldVec.y){
                    x = oldVec.x - e.getX();
                    y = e.getY()-oldVec.y;
                }else{
                    x = oldVec.x - e.getX();
                    y = oldVec.y - e.getY();
                }
            }*/

            Vecteur vec = new Vecteur((e.getX() - oldVec.x) /largeur, (e.getY() - oldVec.y)/hauteur);

            System.out.println("Vec " + vec);
            b.relacher(vec);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
