package exodecorateur_angryballs.maladroit.decorateur;

import exodecorateur_angryballs.maladroit.Event.ControlleurGeneral;
import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.state.State;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;


public abstract class DecorateurBille extends Bille {

    protected Bille bille;

    public State getState(){
        return this.bille.getState();
    }
    public void setState(State state){
        this.bille.setState(state);
    }

    public DecorateurBille(Bille b){
        this.bille = b;
    }

    public int getCouleur() {
        return this.bille.getCouleur();
    }

    public void setCouleur(int couleur) {
        this.bille.setCouleur(couleur);
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

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur){
        this.bille.collisionContour(abscisseCoinHautGauche,ordonneeCoinHautGauche,largeur,hauteur);
    }
    @Override
    public Vecteur getAcceleration(){
        return this.bille.getAcceleration();
    }

    public Vecteur getVitesse(){
        return this.bille.getVitesse();
    }

    public Bille getBille() {
        return bille;
    }

    public void setBille(Bille bille) {
        this.bille = bille;
    }

    public ControlleurGeneral getControlleurGeneral(){
        return this.bille.getControlleurGeneral();
    }

    @Override
    public void deplacer(double deltaT) {
        this.bille.deplacer(deltaT);
    }

    public Vecteur gestionAcceleration(Vector<Bille> billes) {
        this.getAcceleration().ajoute(this.bille.gestionAcceleration(billes));
        return this.bille.gestionAcceleration(billes);
    }

    public String toString(){
        return this.bille.toString();
    }

}
