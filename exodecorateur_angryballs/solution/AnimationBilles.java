package exodecorateur_angryballs.solution;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.scenario.Scenario;
import exodecorateur_angryballs.solution.vues.VueBillard;

import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Responsable de l'animation des billes, c-à-d responsable du mouvement de la liste des billes.
 * Met perpetuellement à jour les billes.
 * Gère le delai entre 2 mises à jour (deltaT) et previent la vue responsable du dessin des billes qu'il faut mettre à jour la scene
 */
public class AnimationBilles {
    /**
     * Délai entre deux rafraichissements en millisecondes
     */
    public static final int DELAI = 2;

    /**
     * La liste de toutes les billes en mouvement
     */
    Vector<Bille> billes;

    /**
     * La vue responsable du dessin des billes
     */
    VueBillard vueBillard;

    /**
     * Le temps de la derniere mise à jour
     */
    private double tPrec;

    /**
     * Delai entre 2 mises à jour de la liste des billes
     */
    double deltaT;

    /**
     * Pour gèrer le delai entre 2 mises à jour
     */
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * @param billes     la liste de toutes les billes
     * @param vueBillard la vue responsable du dessin des billes
     */
    public AnimationBilles(Vector<Bille> billes, VueBillard vueBillard) {
        assert billes != null;
        assert vueBillard != null;
        this.billes = billes;
        this.vueBillard = vueBillard;
        // Affiche les billes avant le lancement de l'animation
        this.vueBillard.miseAJour();
    }

    /**
     * @param vueBillard la vue responsable du dessin des billes
     */
    public AnimationBilles(VueBillard vueBillard) {
        assert vueBillard != null;
        this.billes = new Vector<>();
        this.vueBillard = vueBillard;
        // Affiche les billes avant le lancement de l'animation
        this.vueBillard.miseAJour();
    }

    /**
     * Calcule le maximum de la norme carree (pour faire moins de calcul) des vecteurs vitesse de la liste de billes
     *
     * @param billes la liste de billes
     * @return le maximum de la norme carree des vecteurs vitesse de la liste de billes
     */
    static double maxVitessesCarrees(Vector<Bille> billes) {
        assert billes != null;
        double vitesse2Max = 0;
        double vitesse2Courante;
        for (Bille bille : billes)
            if ((vitesse2Courante = bille.getVitesse().normeCarrée()) > vitesse2Max)
                vitesse2Max = vitesse2Courante;
        return vitesse2Max;
    }

    /**
     * Calcule le minimum des rayons de la liste des billes
     *
     * @param billes la liste des billes
     * @return le minimum des rayons de la liste des billes
     */
    static double minRayons(Vector<Bille> billes) {
        assert billes != null;
        double rayonMin = Double.MAX_VALUE, rayonCourant;
        for (Bille bille : billes)
            if ((rayonCourant = bille.getRayon()) < rayonMin)
                rayonMin = rayonCourant;
        return rayonMin;
    }

    /**
     * Change les billes, utilisé pour changer de scénario
     *
     * @param scenario le scénario avec les nouvelles billes
     */
    public void setBilles(Scenario scenario) {
        assert scenario != null;
        // On remplace les billes
        this.billes = scenario.getBilles();
        // On arrête l'animation pour fermer le thread si ce n'est pas déjà fait
        this.arreterAnimation();
        // On change les billes dans la vue du billard
        this.vueBillard.changeScenario(scenario);
        this.vueBillard.miseAJour();
    }

    /**
     * Boucle qui se répète toutes les DELAI millisecondes pour mettre à jour l'animation
     */
    public void boucleAnimation() {
        // Temps en secondes
        double t = System.currentTimeMillis();
        // Calcul du temps écoulé depuis la dernière mise à jour
        deltaT = t - tPrec;
        tPrec = t;
        // Mise à jour de la liste des billes
        for (Bille bille : this.billes) {
            // Mise à jour position et vitesse de cette bille
            bille.deplacer(deltaT);
            // Calcul de l'acceleration subie par cette bille
            bille.gestionAcceleration(this.billes);
            bille.gestionCollisionBilleBille(this.billes);
            bille.collisionContour(0, 0, vueBillard.largeurBillard(), vueBillard.hauteurBillard());
        }
        // On prévient la vue qu'il faut redessiner les billes
        vueBillard.miseAJour();
    }

    /**
     * Lance l'animation
     */
    public void lancerAnimation() {
        assert this.scheduler != null;
        if (this.scheduler.isShutdown()) {
            tPrec = System.currentTimeMillis();
            this.scheduler = Executors.newScheduledThreadPool(1);
            this.scheduler.scheduleWithFixedDelay(this::boucleAnimation, 0, DELAI, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Arrete l'animation
     */
    public void arreterAnimation() {
        assert this.scheduler != null;
        if (!this.scheduler.isShutdown())
            this.scheduler.shutdown();
    }

    /**
     * Reinitialise l'animation
     */
    public void reinitialiser() {
        this.vueBillard.getScenarioCourant().resetBilles();
        this.setBilles(this.vueBillard.getScenarioCourant());
    }
}
