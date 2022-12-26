package exodecorateur_angryballs.solution.vues.awt;

import exodecorateur_angryballs.solution.Event.ControlleurBilleClavier;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.visiteur.WindowVisitor;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Vector;


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

    public void setKeyListner(ControlleurBilleClavier controlleurBilleClavier) {
        if(this.getKeyListeners().length > 0) {
            this.removeKeyListener(this.getKeyListeners()[0]);
            this.removeMouseListener(this.getMouseListeners()[1]);
        }
        this.addKeyListener(controlleurBilleClavier);
        this.addMouseListener(controlleurBilleClavier);
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

    public BufferStrategy getBuffer(){
        return this.buffer;
    }

    public void myRenderingLoop() {
           try {
           // Nettoyage de l'ecran
               this.graphics = this.buffer.getDrawGraphics();
               graphics.setColor(Color.WHITE);
               graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
           // Dessin des billes
               for (Bille bille : this.billes) {
                   this.graphics = this.buffer.getDrawGraphics();
                   this.visit(bille);
               }
           // Affichage
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

        // Dessin rond
        xMin = (int) Math.round(bille.getPosition().x - bille.getRayon());
        yMin = (int) Math.round(bille.getPosition().y - bille.getRayon());
        width = height = 2 * (int) Math.round(bille.getRayon());
        graphics.setColor(new Color(bille.getCouleur()));
        graphics.fillOval(xMin, yMin, width, height);

        // Si elle est choisie on met les contours en vert
        if(bille.getChoisie())
            graphics.setColor(Color.GREEN);
        // Sinon si la bille est noire on met les contours en noir
        else if(bille.getCouleur() == 0x000000)
            graphics.setColor(Color.CYAN);
        // Sinon on met les contours en noir
        else
            graphics.setColor(Color.BLACK);
        graphics.drawOval(xMin, yMin, width, height);
        try {
            graphics.drawString(bille.getClef() + "", (xMin + width / 2) - 3, (yMin + height / 2) + 5);
        }catch(Exception e){
            System.err.println("Resizing");
        }
    }

}
