package exodecorateur_angryballs.maladroit.modele;

import java.util.Vector;

import exodecorateur_angryballs.maladroit.Event.ControlleurGeneral;
import exodecorateur_angryballs.maladroit.state.State;
import mesmaths.geometrie.base.Vecteur;


/**
 * Cas general d'une bille de billard
 * <p>
 * Aucune des classes du package maladroit.modele ne doit faire de reference e des classes de java.awt ni e aucune autre librairie graphique JAVA car
 * le modele NE DOIT PAS dependre de la vue !!! Vous devez faire les modifications en consequence !! Exploitez les Design Patterns.
 * <p>
 * On rappelle que ces references e une librairie graphique sont nefastes car si on change de librairie graphique, voire on fait migrer le projet sur android,
 * il faut modifier les classes du modele. La maintenance devient catastrophique
 * <p>
 * A MODIFIER
 */
public abstract class Bille {
//----------------- classe Bille-------------------------------------


    protected static int prochaineClef = 0;

    public State state;

    public abstract int getCouleur();

    public abstract void setCouleur(int couleur);


    public abstract Vecteur getPosition();


    /**
     * @return the rayon
     */
    public abstract double getRayon();

    /**
     * @return the vitesse
     */
    public abstract Vecteur getVitesse();

    /**
     * @return the acceleration
     */
    public abstract Vecteur getAcceleration();

    public abstract double masse();

    public abstract State getState();

    public abstract void setState(State state);

    /**
     * @return the clef
     */
    public abstract int getClef();
    /**
     * mise e jour de position et vitesse e t+deltaT e partir de position et vitesse e l'instant t
     * <p>
     * modifie le vecteur position et le vecteur vitesse
     * <p>
     * laisse le vecteur acceleration intact
     * <p>
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
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        return OutilsBille.gestionCollisionBilleBille(this, billes);
    }

    public abstract ControlleurGeneral getControlleurGeneral();


    /**
     * gestion de l'eventuelle collision de la bille (this) avec le contour rectangulaire de l'ecran defini par (abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur)
     * <p>
     * detecte si il y a collision et le cas echeant met e jour position et vitesse
     * <p>
     * La nature du comportement de la bille en reponse e cette collision est definie dans les classes derivees
     */
    public abstract void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur);

    /* cette methode engendre des clignotements : idee : utiliser l'active rendering et le double buffering pour eviter ea */




//----------------- classe Bille -------------------------------------
}

