package exodecorateur_angryballs.solution.Event;

import exodecorateur_angryballs.solution.decorateur.DecorateurSelectionnable;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.scenario.Scenario;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlleurBilleClavier implements KeyListener, MouseListener {
    private DecorateurSelectionnable bille;
    private final Scenario scenario;

    public ControlleurBilleClavier(Scenario scenario) {
        this.scenario = scenario;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'z' || e.getKeyCode()==KeyEvent.VK_UP)
            this.bille.setPosition(new Vecteur(this.bille.getPosition().x,this.bille.getPosition().y-1));
        if(e.getKeyChar() == 'Z' || e.getKeyCode()==KeyEvent.VK_UP)
            this.bille.setPosition(new Vecteur(this.bille.getPosition().x,this.bille.getPosition().y-10));

        if(e.getKeyChar() == 's' || e.getKeyCode()==KeyEvent.VK_DOWN)
            this.bille.setPosition(new Vecteur(this.bille.getPosition().x,this.bille.getPosition().y+1));
        if(e.getKeyChar() == 'S' || e.getKeyCode()==KeyEvent.VK_DOWN)
            this.bille.setPosition(new Vecteur(this.bille.getPosition().x,this.bille.getPosition().y+10));

        if(e.getKeyChar() == 'q' || e.getKeyCode()==KeyEvent.VK_LEFT)
            this.bille.setPosition(new Vecteur(this.bille.getPosition().x-1,this.bille.getPosition().y));
        if(e.getKeyChar() == 'Q' || e.getKeyCode()==KeyEvent.VK_LEFT)
            this.bille.setPosition(new Vecteur(this.bille.getPosition().x-10,this.bille.getPosition().y));

        if(e.getKeyChar() == 'd' || e.getKeyCode()==KeyEvent.VK_RIGHT)
            this.bille.setPosition(new Vecteur(this.bille.getPosition().x+1,this.bille.getPosition().y));
        if(e.getKeyChar() == 'D' || e.getKeyCode()==KeyEvent.VK_RIGHT)
            this.bille.setPosition(new Vecteur(this.bille.getPosition().x+10,this.bille.getPosition().y));


        if(e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyChar() == 'x' || e.getKeyChar() == 'X'){
            this.scenario.removeBille(this.bille);
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyChar() == 'w' || e.getKeyChar() == 'W'){
            this.bille.setPosition(new Vecteur(this.bille.getPosition().x,this.bille.getPosition().y-100));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.bille != null)
            this.bille.setChoisie(false);
        for (Bille b : this.scenario.getBilles()) {
            if (mesmaths.geometrie.base.Geop.appartientDisque(new Vecteur(e.getX(), e.getY()), b.getPosition(), b.getRayon())) {
                try {
                    this.bille = (DecorateurSelectionnable) b;
                    this.bille.setChoisie(true);
                    break;
                } catch (ClassCastException ex) {
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
