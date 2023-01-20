package exodecorateur_angryballs.solution.visiteur;

import exodecorateur_angryballs.solution.decorateur.*;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;

public class VisiteurParticulariteBilleAWT implements DessineParticulariteBille{

    public Graphics graphics;
    public VisiteurParticulariteBilleAWT(Graphics g){
        this.graphics = g;
    }
    @Override
    public void visit(DecorateurSelectionnable b) {

    }

    @Override
    public void visit(DecorateurLance bille) {
        if(bille.isTenue()) {
            int xStart = (int) bille.getInfluence().x;
            int yStart = (int) bille.getInfluence().y;
            int widthD = 300;
            int heightD = 10;
            int xTarget = (int) bille.getPosition().x;
            int yTarget = (int) bille.getPosition().y;
            int maxDistance = 150 + (int) bille.getRayon();

// calculer la distance entre la souris et la bille
            double distance = Math.sqrt(Math.pow(xStart - xTarget, 2) + Math.pow(yStart - yTarget, 2));

// limiter les coordonnées de départ de sorte qu'elles ne dépassent pas la bille
            if (distance < maxDistance) {
                double angle = Math.atan2(yTarget - yStart, xTarget - xStart);
                xStart = (int) (xTarget - maxDistance * Math.cos(angle));
                yStart = (int) (yTarget - maxDistance * Math.sin(angle));
            }

// calculer l'angle de rotation
            double angle = Math.atan2(yTarget - yStart, xTarget - xStart);

// calculer les coordonnées des quatre coins
            int x1 = (int) (xStart + (widthD / 2) * Math.cos(angle) - (heightD / 2) * Math.sin(angle));
            int y1 = (int) (yStart + (widthD / 2) * Math.sin(angle) + (heightD / 2) * Math.cos(angle));
            int x2 = (int) (xStart - (widthD / 2) * Math.cos(angle) - (heightD / 2) * Math.sin(angle));
            int y2 = (int) (yStart - (widthD / 2) * Math.sin(angle) + (heightD / 2) * Math.cos(angle));
            int x3 = (int) (xStart - (widthD / 2) * Math.cos(angle) + (heightD / 2) * Math.sin(angle));
            int y3 = (int) (yStart - (widthD / 2) * Math.sin(angle) - (heightD / 2) * Math.cos(angle));
            int x4 = (int) (xStart + (widthD / 2) * Math.cos(angle) + (heightD / 2) * Math.sin(angle));
            int y4 = (int) (yStart + (widthD / 2) * Math.sin(angle) - (heightD / 2) * Math.cos(angle));

// dessiner le rectangle
            int[] xPoints = {x1, x2, x3, x4};
            int[] yPoints = {y1, y2, y3, y4};
            graphics.setColor(Color.BLACK);
            graphics.fillPolygon(xPoints, yPoints, 4);
            int xSym = (int) ((int) (2 * bille.getPosition().x) - bille.getInfluence().x);
            int ySym = (int) ((int) (2 * bille.getPosition().y) - bille.getInfluence().y);
            Vecteur oppose = new Vecteur(xSym, ySym);
            graphics.drawLine((int) bille.getPosition().x, (int) bille.getPosition().y, (int) oppose.x, (int) oppose.y);
        }
    }

    public double distanceVec(Vecteur x1, Vecteur x2){
        return Math.sqrt(Math.pow(x2.x - x1.x, 2) + Math.pow(x2.y - x1.y, 2));

    }

    @Override
    public void visit(DecorateurAttraper b) {

    }

    @Override
    public void visit(DecorateurLorentz b) {
        Vecteur position = b.getPosition();
        graphics.fillOval((int) ((int) position.x+ b.v.x), (int) ((int) position.y+b.v.y),10,10);
    }

    @Override
    public void visit(DecorateurBille b) {

    }


    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }
}
