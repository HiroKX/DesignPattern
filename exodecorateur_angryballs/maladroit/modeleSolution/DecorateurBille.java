package exodecorateur_angryballs.maladroit.modeleSolution;

import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.cinematique.Cinematique;
import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;


public abstract class DecorateurBille extends Bille {

    protected Bille bille;

    public DecorateurBille(Bille b){
        this.bille = b;
    }

    @Override
    public Vecteur getPosition() {
        return this.bille.getPosition();
    }

    @Override
    public double getRayon() {
        return this.bille.getRayon();
    }

    @Override
    public int getClef() {
        return this.bille.getClef();
    }

    public double masse() {
        return this.bille.masse();
    }

    public Bille getBille() {
        return bille;
    }

    public void setBille(Bille bille) {
        this.bille = bille;
    }

    @Override
    public void deplacer(double deltaT) {
        this.bille.deplacer(deltaT);
    }

    public void gestionAcceleration(Vector<Bille> billes) {
        this.bille.gestionAcceleration(billes);
    }

    public String toString(){
        return this.bille.toString();
    }

}
