package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.RobinLagler;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.OutilsBille;
import exodecorateur_angryballs.solution.son.SonLongRobin;
import exodecorateur_angryballs.solution.vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class DecorateurSonCollision extends DecorateurBille{

    private static final int DELAI_MIN = 10;    /* delai minimum de rafraichissement du son. en millisecondes */
    public static final int DELAI_MAX = 150;    /* delai maximum de rafraichissement du son. en millisecondes */
    public SonLongRobin sonLong;                            /* bande son e diffuser */
    int i;              /* ne de l'element de sonLong e jouer. on doit avoir i >= 0.
                    sonLong se charge de faire le modulo pour obtenir un indice correct
                    et pour boucler ainsi sur le tableau inscrit dans sonLong */
    long dernierInstant;        /* dernier instant oe le son a ete diffuse */
    VueBillard vueBillard;

    private static final double COEFF_VOLUME = 6;


    public DecorateurSonCollision(Bille b, SonLongRobin sonLong, VueBillard vueBillard) {
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
            int delai = 200;              /* le delai entre 2 diffusions diminue lorsque la vitesse augmente */
            long instant = System.currentTimeMillis();
            System.err.println(volume);
            if (instant - this.dernierInstant >=delai)                      /* la frequence de diffusion augmente donc avec la vitesse de la bille */
            {
                double coeffPitch = 1;
                this.sonLong.joue(i++, volume, balance, coeffPitch);            /* le son est diffuse dans un thread separe */ //TODO : modifier toute la classe pour ne faire le son qu'une fois qu'elle touche une fois le mur et avec l'intensité
                this.dernierInstant= instant;
            }else{
                i=0;
            }
        }
        return temp;
    }
}
