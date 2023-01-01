package exodecorateur_angryballs.solution.modele;

import exodecorateur_angryballs.solution.Event.ControlleurGeneral;
import mesmaths.cinematique.Cinematique;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

/**
    implementation d'une bille (concrete) qui hérite de Bille
 */
public class BilleConcrete extends Bille {

    /** centre de la bille */
    public Vecteur position;
    /** rayon > 0 */
    public double rayon;
    /** couleur de la bille */
    public int couleur;
    /** vitesse de la bille */
    public Vecteur vitesse;
    /** acceleration de la bille */
    public Vecteur acceleration;
    /** identifiant unique de cette bille */
    public int clef;
    /** masse volumique de la bille */
    public static double ro = 1;

    public static boolean TEMP = false;
    @Override
    public int getCouleur() {
        return couleur;
    }

    @Override
    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    /**
     * @return the position
     */
    public Vecteur getPosition() {
        return this.position;
    }

    public Vecteur setPosition(Vecteur position) {
        return this.position = position;
    }

    /**
     * @return le rayon
     */
    public double getRayon() {
        return this.rayon;
    }


    /**
     * @return la vitesse
     */
    public Vecteur getVitesse() {
        return this.vitesse;
    }


    public ControlleurGeneral getControlleurGeneral(){
        return null;
    }



    /**
     * @return l acceleration
     */
    public Vecteur getAcceleration() {
        return this.acceleration;
    }


    /**
     * @return la clef
     */
    public int getClef() {
        return this.clef;
    }


    public double masse() {
        return ro * Math.PI*(rayon*rayon);
    }

    public BilleConcrete(Vecteur centre, double rayon, Vecteur vitesse, Vecteur acceleration) {
        this.position = centre;
        this.rayon = rayon;
        this.vitesse = vitesse;
        this.acceleration = acceleration;
        this.clef = BilleConcrete.prochaineClef++;
    }

    @Override
    public void deplacer(double deltaT) {
        Cinematique.mouvementUniformémentAccéléré(this.getPosition(), this.getVitesse(), this.getAcceleration(), deltaT);
    }

    public Vecteur gestionAcceleration(Vector<Bille> billes) {
        this.getAcceleration().set(new Vecteur(0,0));
        return new Vecteur(0,0);
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {

        temp = OutilsBille.gestionCollisionBilleBille(this, billes);
        return temp;
    }

    public BilleConcrete(Vecteur position, double rayon, Vecteur vitesse) {
        this(position, rayon, vitesse, new Vecteur());
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {

    }
    public String toString() {
        return "centre = " + this.position + " rayon = " + this.rayon + " vitesse = " + this.vitesse + " acceleration = " + this.acceleration + "clef = " + this.clef;
    }

    public String toStringCentre(){
        return this.clef + " : "+ (int) this.position.x + ","+ (int) this.position.y;
    }
}
