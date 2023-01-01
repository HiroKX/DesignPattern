package exodecorateur_angryballs.solution.modele;

import exodecorateur_angryballs.solution.RobinLagler;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

import java.util.Vector;

/**
 * Operations utiles sur les billes
 */

public class OutilsBille {
    /**
     * @param billes     est la liste de TOUTES les billes en mouvement
     * @param cetteBille est l'une d'entre elles.
     * @return la liste des autres billes que cetteBille, c'est-e-dire la liste de toutes les billes sauf cetteBille
     */
    public static Vector<Bille> autresBilles(Bille cetteBille, Vector<Bille> billes) {
        Vector<Bille> autresBilles = (Vector<Bille>) billes.clone();
        autresBilles.remove(cetteBille);
        return autresBilles;
        /*
        Vector<Bille> autresBilles = new Vector<Bille>();
        for (Bille bille : billes) {
            if (!bille.equals(cetteBille))
                autresBilles.add(bille);
        }
        return autresBilles;
        */
    }


    /**
     * @param cetteBille : une bille particuliere
     * @param billes     : une liste de billes, cette liste peut contenir cettebille
     *                   <p>
     *                   gestion de l'eventuelle  collision de cette bille avec les autres billes
     *                   <p>
     *                   billes est la liste de toutes les billes en mouvement
     *                   <p>
     *                   Le comportement par defaut est le choc parfaitement elastique (c-e-d rebond sans amortissement)
     * @return true si il y a collision et dans ce cas les positions et vecteurs vitesses des 2 billes impliquees dans le choc sont modifiees
     * si renvoie false, il n'y a pas de collision et les billes sont laissees intactes
     */
    public static boolean gestionCollisionBilleBille(Bille cetteBille, Vector<Bille> billes) {
//--- on recupere d'abord dans autresBilles toutes les billes sauf cetteBille ----

        Vector<Bille> autresBilles = OutilsBille.autresBilles(cetteBille, billes);

//--- on cherche e present la 1ere des autres billes avec laquelle cetteBille est en collision ---------------------
//-------------- on suppose qu'il ne peut y avoir de collision qui implique plus de deux billes e la fois ---------------

        for (Bille autresBille : autresBilles) {
            if (RobinLagler.CollisionBilleBille(cetteBille.getPosition(), cetteBille.getRayon(), cetteBille.getVitesse(), cetteBille.masse(),
                    autresBille.getPosition(), autresBille.getRayon(), autresBille.getVitesse(), autresBille.masse()))
                return true;
        }
        return gestionCollisionBilleBilleStatique(cetteBille, autresBilles);
    }

    public static boolean gestionCollisionBilleBilleStatique(Bille cetteBille, Vector<Bille> autresBilles) {
        for (Bille autresBille : autresBilles) {
            mesmaths.cinematique.brouillon.Collisions.CollisionBilleBille2(cetteBille.getPosition(), cetteBille.getRayon(), new Vecteur(0.00000001, 0.0000000001),
                    cetteBille.masse(), autresBille.getPosition(), autresBille.getRayon(), new Vecteur(0.00000001, 0.0000000001), autresBille.masse());
        }
        return false;
    }


    /**
     * @param cetteBille : une bille particuliere
     * @param billes     : une liste de billes, cette liste peut contenir cettebille
     *                   <p>
     *                   On suppose que cetteBille subit l'attraction gravitationnelle due aux billes contenues dans "billes" autres que cetteBille.
     *                   <p>
     *                   teche : calcule a, le vecteur acceleration subi par cetteBille resultant de l'attraction par les autres billes de la liste.
     * @return a : le vecteur acceleration resultant
     */
    public static Vecteur gestionAccelerationNewton(Bille cetteBille, Vector<Bille> billes) {

//--- on recupere d'abord dans autresBilles toutes les billes sauf celle-ci ----

        Vector<Bille> autresBilles = OutilsBille.autresBilles(cetteBille, billes);

//-------------- e present on recupere les masses et les positions des autres billes ------------------

        int d = autresBilles.size();

        double[] masses = new double[d];   // les masses des autres billes
        Vecteur[] C = new Vecteur[d];      // les positions des autres billes

        for (int i = 0; i < d; ++i) {
            masses[i] = autresBilles.get(i).masse();
            C[i] = autresBilles.get(i).getPosition();
        }

//------------------ e present on calcule le champ de gravite exerce par les autres billes sur cette bille ------------------

        return MecaniquePoint.champGravitéGlobal(cetteBille.getPosition(), masses, C);
    }
}
