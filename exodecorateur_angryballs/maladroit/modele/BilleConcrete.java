package exodecorateur_angryballs.maladroit.modele;

import mesmaths.cinematique.Cinematique;
import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public class BilleConcrete extends Bille {


    public Vecteur position;   // centre de la bille
    public double rayon;            // rayon > 0
    public Vecteur vitesse;
    public Vecteur acceleration;
    public int clef;                // identifiant unique de cette bille
    protected static int prochaineClef = 0;
    public static double ro = 1;        // masse volumique

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
        return ro * Geop.volumeSphère(rayon);
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

    public void gestionAcceleration(Vector<Bille> billes) {

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

    public void dessine(Graphics g)    // reference awt : mauvais
    {
        int width, height;
        int xMin, yMin;

        xMin = (int) Math.round(this.getPosition().x - this.getRayon());
        yMin = (int) Math.round(this.getPosition().y - this.getRayon());

        width = height = 2 * (int) Math.round(this.getRayon());

        g.setColor(Color.BLACK);
        g.fillOval(xMin, yMin, width, height);
        g.setColor(Color.CYAN);
        g.drawOval(xMin, yMin, width, height);
    }
}
