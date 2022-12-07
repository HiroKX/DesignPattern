package exodecorateur_angryballs.solution.vues;

import java.awt.*;
import java.util.Vector;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.visiteur.WindowVisitor;


/**
 * responsable du dessin des billes
 * <p>
 * ICI : IL N'Y A RIEN A CHANGER
 */

public class BillardAWT extends Canvas implements WindowVisitor {
    Vector<Bille> billes;
    Graphics graphics;

    public BillardAWT(Vector<Bille> billes) {
        this.billes = billes;

    }

    /* (non-Javadoc)
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics graphics) {
        int i;

        for (i = 0; i < this.billes.size(); ++i){
            //this.billes.get(i).dessine(graphics);
            this.graphics = graphics;
            this.visit(this.billes.get(i));
        }

        //System.out.println("billes dans le billard = " + billes);
    }

    @Override
    public void visit(Bille bille) {
        int width, height;
        int xMin, yMin;

        xMin = (int) Math.round(bille.getPosition().x - bille.getRayon());
        yMin = (int) Math.round(bille.getPosition().y - bille.getRayon());
        width = height = 2 * (int) Math.round(bille.getRayon());
        graphics.setColor(new Color(bille.getCouleur()));
        graphics.fillOval(xMin, yMin, width, height);
        graphics.setColor(Color.CYAN);
        graphics.drawOval(xMin, yMin, width, height);
    }

}
