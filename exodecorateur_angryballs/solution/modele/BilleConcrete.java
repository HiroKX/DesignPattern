package exodecorateur_angryballs.solution.modele;

import exodecorateur_angryballs.solution.Event.ControlleurGeneral;
import mesmaths.cinematique.Cinematique;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class BilleConcrete extends Bille {


    public Vecteur position;   // centre de la bille
    public double rayon;            // rayon > 0
    public int couleur;
    public Vecteur vitesse;
    public Vecteur acceleration;
    public int clef;                // identifiant unique de cette bille
    public static double ro = 1;        // masse volumique
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


    /**
     * @return the rayon
     */
    public double getRayon() {
        return this.rayon;
    }


    /**
     * @return the vitesse
     */
    public Vecteur getVitesse() {
        return this.vitesse;
    }


    public ControlleurGeneral getControlleurGeneral(){
        return null;
    }



    /**
     * @return the acceleration
     */
    public Vecteur getAcceleration() {
        return this.acceleration;
    }


    /**
     * @return the clef
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
