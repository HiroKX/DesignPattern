package exodecorateur_angryballs.solution.vues;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
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
    BufferStrategy buffer;

    public BillardAWT(Vector<Bille> billes) {
        this.setIgnoreRepaint(true);
        this.billes = billes;
    }

    public void initBuffer(){
        try {
            this.createBufferStrategy(2);
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.buffer = this.getBufferStrategy();
    }

    public void myRenderingLoop() {
           try {
               // Nettoyage de l'ecran
               this.graphics = this.buffer.getDrawGraphics();
               graphics.setColor(Color.WHITE);
               graphics.clearRect(0, 0, this.getWidth(), this.getHeight());

               for (Bille bille : this.billes) {
                   this.graphics = this.buffer.getDrawGraphics();
                   this.visit(bille);
               }

               if(!this.buffer.contentsLost())
                   this.buffer.show();
              }finally {
                if(this.graphics != null)
                     this.graphics.dispose();
           }
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
        if(bille.getCouleur() == 0x000000)
            graphics.setColor(Color.CYAN);
        else
            graphics.setColor(Color.BLACK);
        graphics.drawOval(xMin, yMin, width, height);
        graphics.drawString(bille.getClef()+"", (xMin+width/2)-3, (yMin+height/2)+5);
    }

}
