package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.RobinLagler;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.OutilsBille;
import exodecorateur_angryballs.maladroit.vues.BoutonChoixHurlement;
import exodecorateur_angryballs.solution.vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

public class DecorateurSonCollision extends DecorateurBille{

    private static final int DELAI_MIN = 10;    /* delai minimum de rafraichissement du son. en millisecondes */
    public static final int DELAI_MAX = 150;    /* delai maximum de rafraichissement du son. en millisecondes */
    public SonLong sonLong;                            /* bande son e diffuser */
    int i;              /* ne de l'element de sonLong e jouer. on doit avoir i >= 0.
                    sonLong se charge de faire le modulo pour obtenir un indice correct
                    et pour boucler ainsi sur le tableau inscrit dans sonLong */
    long dernierInstant;        /* dernier instant oe le son a ete diffuse */
    VueBillard vueBillard;

    private static final double COEFF_VOLUME = 6;


    public DecorateurSonCollision(Bille b, SonLong sonLong, VueBillard vueBillard) {
        super(b);
        this.sonLong = sonLong;
        i = 0;
        dernierInstant = System.currentTimeMillis();
        this.vueBillard = vueBillard;
    }


    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        boolean temp = OutilsBille.gestionCollisionBilleBille(this, billes);

        if(temp){
            Vecteur p = this.getPosition();
            Vecteur v = this.getVitesse();
            double a = RobinLagler.A;
            double volume =  1-Math.exp(-a);
            double n = v.norme();
            double y = Math.exp(-COEFF_VOLUME*n);                // y = e^(-COEFF*n). on obtient donc 0 < y <= 1
            double x1 = p.x/this.vueBillard.largeurBillard();
            double balance = 2*x1 - 1;
            int delai = (int)(DELAI_MIN*volume + DELAI_MAX*y);              /* le delai entre 2 diffusions diminue lorsque la vitesse augmente */
            long instant = System.currentTimeMillis();
                double coeffPitch = 1;
                this.sonLong.joue(i++, volume, balance, coeffPitch);            /* le son est diffuse dans un thread separe */
                this.dernierInstant= instant;
        }
        return temp;
    }
}
