package exodecorateur_angryballs.solution.modele;

import exodecorateur_angryballs.solution.Event.ControlleurGeneral;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;


/**
 * Cas general d'une bille de billard

 * Aucune des classes du package maladroit.modele ne doit faire de reference a des classes de java.awt ni aucune autre librairie graphique JAVA car
 * le modele NE DOIT PAS dependre de la vue !!! Vous devez faire les modifications en consequence !! Exploitez les Design Patterns.

 * On rappelle que les references a une librairie graphique sont nefastes car si on change de librairie graphique, voire on fait migrer le projet sur android,
 * il faut modifier les classes du modele. La maintenance devient catastrophique

 * A MODIFIER
 */
public abstract class Bille {
//----------------- classe Bille-------------------------------------


    protected static int prochaineClef = 0;
    public boolean temp = false;

    public abstract int getCouleur();

    public abstract void setCouleur(int couleur);


    public abstract Vecteur getPosition();

    public abstract Vecteur setPosition(Vecteur position);


    /**
     * @return le rayon
     */
    public abstract double getRayon();

    /**
     * @return la vitesse
     */
    public abstract Vecteur getVitesse();

    /**
     * @return l acceleration
     */
    public abstract Vecteur getAcceleration();

    public abstract double masse();

    /**
     * @return la clef
     */
    public abstract int getClef();
    /**
     * mise a jour de position et vitesse et deltaT a partir de position et vitesse a l'instant t

     * modifie le vecteur position et le vecteur vitesse

     * laisse le vecteur acceleration intact

     * La bille subit par defaut un mouvement uniformement accelere
     */
    public abstract void deplacer(double deltaT);

    /**
     * calcul (c-e-d mise e jour) eventuel  du vecteur acceleration.
     * billes est la liste de toutes les billes en mouvement
     * Cette methode peut avoir besoin de "billes" si this subit l'attraction gravitationnelle des autres billes
     * La nature du calcul du vecteur acceleration de la bille  est definie dans les classes derivees
     * A ce niveau le vecteur acceleration est mis e zero (c'est e dire pas d'acceleration)
     *
     * @return
     */
    public abstract Vecteur gestionAcceleration(Vector<Bille> billes);

    /**
     * gestion de l'eventuelle  collision de cette bille avec les autres billes
     * <p>
     * billes est la liste de toutes les billes en mouvement
     * <p>
     * Le comportement par defaut est le choc parfaitement elastique (c-e-d rebond sans amortissement)
     *
     * @return true si il y a collision et dans ce cas les positions et vecteurs vitesses des 2 billes impliquees dans le choc sont modifiees
     * si renvoie false, il n'y a pas de collision et les billes sont laissees intactes
     */
    public abstract boolean gestionCollisionBilleBille(Vector<Bille> billes);

    public abstract ControlleurGeneral getControlleurGeneral();


    /**
     * gestion de l'eventuelle collision de la bille (this) avec le contour rectangulaire de l'ecran defini par (abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur)

     * detecte si il y a collision et le cas echeant met a jour position et vitesse

     * La nature du comportement de la bille en reponse a cette collision est definie dans les classes derivees
     */
    public abstract void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur);

    public abstract String toStringCentre();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bille b)
            return this.getClef() == b.getClef();
        return false;
    }
}

