package exodecorateur_angryballs.solution;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.scenario.Scenario;
import exodecorateur_angryballs.solution.vues.VueBillard;

import java.util.Vector;

/**
 * responsable de l'animation des billes, c-e-d responsable du mouvement de la liste des billes. met perpetuellement e jour les billes.
 * gere le delai entre 2 mises e jour (deltaT) et previent la vue responsable du dessin des billes qu'il faut mettre e jour la scene
 * <p>
 * ICI : IL N'Y A RIEN A CHANGER
 */
public class AnimationBilles implements Runnable {


    private static final double COEFF = 0.5;
    Vector<Bille> billes;   // la liste de toutes les billes en mouvement
    VueBillard vueBillard;    // la vue responsable du dessin des billes
    private Thread thread;    // pour lancer et arreter les billes
    private double tPrec;

    /**
     * @param billes la liste de toutes les billes
     * @param vueBillard la vue responsable du dessin des billes
     */
    public AnimationBilles(Vector<Bille> billes, VueBillard vueBillard) {
        this.billes = billes;
        this.vueBillard = vueBillard;
        this.thread = null;     //est-ce utile ?
        // Affiche les billes avant le lancement de l'animation
        this.vueBillard.miseAJour();
    }

    public AnimationBilles( VueBillard vueBillard) {
        this.billes = new Vector<>();
        this.vueBillard = vueBillard;
        this.thread = null;     //est-ce utile ?
        // Affiche les billes avant le lancement de l'animation
        this.vueBillard.miseAJour();

    }

    /**
     * calcule le maximum de de la norme carree (pour faire moins de calcul) des vecteurs vitesse de la liste de billes
     */
    static double maxVitessesCarrees(Vector<Bille> billes) {
        double vitesse2Max = 0;

        int i;
        double vitesse2Courante;

        for (i = 0; i < billes.size(); ++i)
            if ((vitesse2Courante = billes.get(i).getVitesse().normeCarrée()) > vitesse2Max)
                vitesse2Max = vitesse2Courante;

        return vitesse2Max;
    }

    /**
     * calcule le minimum  des rayons de a liste des billes
     */
    static double minRayons(Vector<Bille> billes) {
        double rayonMin, rayonCourant;

        rayonMin = Double.MAX_VALUE;

        int i;
        for (i = 0; i < billes.size(); ++i)
            if ((rayonCourant = billes.get(i).getRayon()) < rayonMin)
                rayonMin = rayonCourant;

        return rayonMin;
    }

    // Change les billes, utilisé pour changer de scénario
    public void setBilles(Scenario scenario) {
            // On remplace les billes
            this.billes = scenario.getBilles();
            // On arrête l'animation pour fermer le thread si ce n'est pas déjà fait
            this.arreterAnimation();
            // On change les billes dans la vue du billard
            this.vueBillard.changeScenario(scenario);
            this.vueBillard.miseAJour();
    }

    @Override
    public void run() {
        tPrec = System.currentTimeMillis();
        double deltaT;  // delai entre 2 mises e jour de la liste des billes
        Bille billeCourante;

        double minRayons = AnimationBilles.minRayons(billes);   //necessaire au calcul de deltaT
        double minRayons2 = minRayons * minRayons;                //necessaire au calcul de deltaT

        while (!Thread.interrupted())                           // gestion du mouvement
        {
            double t = System.currentTimeMillis();//Temps en secondes
            //deltaT = COEFF*minRayons2/(1+maxVitessesCarrees(billes));       // mise e jour deltaT. L'addition + 1 est une astuce pour eviter les divisions par zero

            //System.err.println("deltaT = " + deltaT);
            deltaT = t - tPrec;
            tPrec = t;
            //deltaT = 10;
            int i;
            for (i = 0; i < billes.size(); ++i)    // mise e jour de la liste des billes
            {

                billeCourante = billes.get(i);

                billeCourante.deplacer(deltaT);                 // mise e jour position et vitesse de cette bille
                billeCourante.gestionAcceleration(billes);      // calcul de l'acceleration subie par cette bille
                billeCourante.gestionCollisionBilleBille(billes);
                billeCourante.collisionContour(0, 0, vueBillard.largeurBillard(), vueBillard.hauteurBillard());        //System.err.println("billes = " + billes);

            }

            vueBillard.miseAJour();                                // on previent la vue qu'il faut redessiner les billes


            try {
                Thread.sleep(1);                          // deltaT peut etre considere comme le delai entre 2 flashes d'un stroboscope qui eclairerait la scene
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
        }

    }

    public void lancerAnimation() {
        if (this.thread == null) {
            this.thread = new Thread(this);
            thread.start();
        }
    }

    public void arreterAnimation() {
        if (thread != null) {
            this.thread.interrupt();
            this.thread = null;
        }
    }

    public void reinitialiser() {
        this.vueBillard.getScenarioCourant().resetBilles();
        this.setBilles(this.vueBillard.getScenarioCourant());
    }
}
