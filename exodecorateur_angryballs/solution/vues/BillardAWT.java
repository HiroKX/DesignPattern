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
    GraphicsEnvironment ge;
    GraphicsDevice gd;
    GraphicsConfiguration gc;
    BufferedImage bi;

    public BillardAWT(Vector<Bille> billes) {
        this.setIgnoreRepaint(true);
        this.billes = billes;

        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();
        gc = gd.getDefaultConfiguration();

        // Create off-screen drawing surface
        bi = gc.createCompatibleImage(500, 500);
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
               this.graphics = this.buffer.getDrawGraphics();
               // Nettoyage de l'ecran

               //graphics = bi.createGraphics();
               graphics.setColor(Color.WHITE);
               graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
               //graphics.fillRect(0, 0, this.getWidth(), this.getHeight());


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
        graphics.setColor(Color.CYAN);
        graphics.drawOval(xMin, yMin, width, height);
    }

}
