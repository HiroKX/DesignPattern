package exodecorateur_angryballs.maladroit.modele;

import mesmaths.cinematique.Cinematique;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class BilleConcrete extends Bille {

    public BilleConcrete(Vecteur centre, double rayon, Vecteur vitesse, Vecteur acceleration) {
        this.position = centre;
        this.rayon = rayon;
        this.vitesse = vitesse;
        this.acceleration = acceleration;
        this.clef = Bille.prochaineClef++;
    }

    @Override
    public void deplacer(double deltaT) {
        Cinematique.mouvementUniformémentAccéléré(this.getPosition(), this.getVitesse(), this.getAcceleration(), deltaT);
    }

    public void gestionAcceleration(Vector<Bille> billes) {
        this.getAcceleration().set(Vecteur.VECTEURNUL);
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
}
