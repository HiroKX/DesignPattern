package exodecorateur_angryballs.solution.vues.awt;

import exodecorateur_angryballs.solution.COR.CORIndex;
import exodecorateur_angryballs.solution.Event.ControlleurBilleClavier;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.visiteur.DessineParticulariteBille;
import exodecorateur_angryballs.solution.visiteur.VisiteurParticulariteBilleAWT;
import exodecorateur_angryballs.solution.visiteur.WindowVisitor;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Vector;


/**
 * Responsable du dessin des billes
 */

public class BillardAWT extends Canvas implements WindowVisitor {
    /** Billes à dessiner
     */
    Vector<Bille> billes;
    /** Animation
     */
    Graphics graphics;
    /** Buffer
     */
    BufferStrategy buffer;

    CORIndex cor;
    DessineParticulariteBille dessinerParticularite;

    public BillardAWT(Vector<Bille> billes) {
        assert billes != null;
        this.setIgnoreRepaint(true); // Active rendering
        this.billes = billes;
        this.cor= new CORIndex(new VisiteurParticulariteBilleAWT(graphics));
    }

    /**
     * Met en place le key listener pour les billes sélectionnables
     * Le remplace si il existe déjà
     * @param controlleurBilleClavier le controlleur
     */
    public void setKeyListner(ControlleurBilleClavier controlleurBilleClavier) {
        if(this.getKeyListeners().length > 0) {
            this.removeKeyListener(this.getKeyListeners()[0]);
            this.removeMouseListener(this.getMouseListeners()[1]);
        }
        this.addKeyListener(controlleurBilleClavier);
        this.addMouseListener(controlleurBilleClavier);
    }

    /**
     * Initialise le buffer
     */
    public void initBuffer(){
        try {
            this.createBufferStrategy(2);
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.buffer = this.getBufferStrategy();
    }

    /**
     * Dessine les billes
     */
    public void myRenderingLoop() {
        if(this.buffer != null) {
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
                if (!this.buffer.contentsLost())
                    this.buffer.show();
            } finally {
                if (this.graphics != null)
                    this.graphics.dispose();
            }
        }
    }

    /**
     * Dessine une bille, DP Visitor
     * @param bille la bille à dessiner
     */
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
        // Sinon si la bille est noire on met les contours en noir
        if(bille.isTenue())
            graphics.setColor(Color.GREEN);
        else if(bille.getCouleur() == 0x000000)
            graphics.setColor(Color.CYAN);
        // Sinon, on met les contours en noir
        else
            graphics.setColor(Color.BLACK);
        graphics.drawOval(xMin, yMin, width, height);

        VisiteurParticulariteBilleAWT visiteur = (VisiteurParticulariteBilleAWT) this.cor.visiteur;
        visiteur.setGraphics(graphics);
        this.cor.dessiner(bille);


        try {
            graphics.drawString(bille.getClef() + "", (xMin + width / 2) - 3, (yMin + height / 2) + 5);
        }catch(Exception e){
            System.err.println("Resizing");
        }
    }


}
