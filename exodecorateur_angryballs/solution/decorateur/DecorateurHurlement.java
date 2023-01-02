package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.maladroit.vues.BoutonChoixHurlement;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
    Ce decorateur gere le hurlement (son) de la bille
 */
public class DecorateurHurlement extends DecorateurBille{
    /** Delai minimum de rafraichissement du son en millisecondes */
    private static final int DELAI_MIN = 10;
    /** Delai maximum de rafraichissement du son en millisecondes */
    public static final int DELAI_MAX = 150;
    /** bande son e diffuser */
    public SonLong sonLong;
    /** ne de l'element de sonLong à jouer. On doit avoir i >= 0.
     sonLong se charge de faire le modulo pour obtenir un indice correct
     et pour boucler ainsi sur le tableau inscrit dans sonLong */
    int i;
    /** Dernier instant où le son a ete diffuse */
    long dernierInstant;
    VueBillard vueBillard;

    /** Coefficient du volume
     */
    private static final double COEFF_VOLUME = 6;

    public DecorateurHurlement(Bille b, SonLong sonLong, VueBillard vueBillard) {
        super(b);
        this.sonLong = sonLong;
        i = 0;
        dernierInstant = System.currentTimeMillis();
        this.vueBillard = vueBillard;
    }

    @Override
    public void deplacer(double deltaT)
    {
        Vecteur p = this.getPosition();
        Vecteur v = this.getVitesse();
        double xMax;

        xMax = vueBillard.largeurBillard();

        double n = v.norme();
    //double n2 = v.normeCarree();
        double y = Math.exp(-COEFF_VOLUME*n);                // y = e^(-COEFF*n). on obtient donc 0 < y <= 1
        double volume = 1-y;                                 // on obtient 0 <= volume < 1 avec volume == 0 si n == 0  et volume proche de 1 si n est grand
        double x1 = p.x/xMax;                   /* on obtient 0 <= x1 <= 1 */ ////System.err.println("dans BilleHurlante.deplacer() : x1 =  "+ x1);
        double balance = 2*x1 - 1;              // on obtient -1 <= balance <= 1
        ////System.err.println("volume = " + volume);
    //double v2 = volume*volume;
        int delai = (int)(DELAI_MIN*volume + DELAI_MAX*y);              /* le delai entre 2 diffusions diminue lorsque la vitesse augmente */
        long instant = System.currentTimeMillis();
        if (instant - this.dernierInstant >=delai)                      /* la frequence de diffusion augmente donc avec la vitesse de la bille */
        {
            double coeffPitch = 1;
            this.sonLong.joue(i++, volume, balance, coeffPitch);            /* le son est diffuse dans un thread separe */
            this.dernierInstant= instant;
        }
        super.deplacer(deltaT);
    }

    public void setHurlement(SonLong sonLong) {                                                                               //System.err.println("dans BilleHurlanteMvtNewtonArret.itemStateChanged au debut");
            this.sonLong = sonLong;                                //System.err.println("dans BilleHurlanteMvtNewtonArret.itemStateChanged");
    }
}
