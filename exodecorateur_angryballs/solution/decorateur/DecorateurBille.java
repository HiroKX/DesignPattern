package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.Event.ControlleurType;
import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

/**
    Classe abstraite à partir de laquelle on construira tous les decorateurs
 */
public abstract class DecorateurBille extends Bille {

    /**
     * La bille décorée
     */
    protected Bille bille;

    /*
    Constructeur de la classe
     */
    public DecorateurBille(Bille b){
        this.bille = b;
    }

    /*
    Recupere la couleur de la bille
     */
    public int getCouleur() {
        return this.bille.getCouleur();
    }

    public void setCouleur(int couleur) {
        this.bille.setCouleur(couleur);
    }

    /*
    Recupere la position de la bille
     */
    @Override
    public Vecteur getPosition() {
        return this.bille.getPosition();
    }

    /**
    Recupere le rayon
     **/
    @Override
    public double getRayon() {
        return this.bille.getRayon();
    }

    /*
    Recupere la clef de la bille
     */
    @Override
    public int getClef() {
        return this.bille.getClef();
    }

    /** definit la masse de la bille
     * @return la masse de la bille
     */
    public double masse() {
        return this.bille.masse();
    }

    /*
    Definit où se trouve les collisions sur les bords du cadre
     */
    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur){
        this.bille.collisionContour(abscisseCoinHautGauche,ordonneeCoinHautGauche,largeur,hauteur);
    }


    @Override
    public String toStringCentre() {
        return this.bille.toStringCentre();
    }

    /*
    Definit la collision entre billes
     */
    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        return this.bille.gestionCollisionBilleBille(billes);
    }

    /*
    Recupere l acceleration
     */
    @Override
    public Vecteur getAcceleration(){
        return this.bille.getAcceleration();
    }

    /*
    Recupere la vitesse
     */
    public Vecteur getVitesse(){
        return this.bille.getVitesse();
    }

    /*
    Recupere la bille
     */
    public Bille getBille() {
        return bille;
    }

    public void setBille(Bille bille) {
        this.bille = bille;
    }

    public Vecteur setPosition(Vecteur position) {
        return this.bille.setPosition(position);
    }

    public ControlleurType getControlleur(){
        return this.bille.getControlleur();
    }

    /*
    Definit le deplacement de la bille sur une duree de deltaT
     */
    @Override
    public void deplacer(double deltaT) {
        this.bille.deplacer(deltaT);
    }

    /** Gere l'acceleration de la bille
     * @param billes les billes du billard
     * @return la nouvelle acceleration de la bille
     */
    public Vecteur gestionAcceleration(Vector<Bille> billes) {
        this.getAcceleration().ajoute(this.bille.gestionAcceleration(billes));
        return this.bille.gestionAcceleration(billes);
    }

    /*
    Renvoie un string contenant les informations de la bille
     */
    public String toString(){
        return this.bille.toString();
    }


}
