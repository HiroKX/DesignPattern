package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.vues.awt.boutons.BoutonChoixHurlement;
import exodecorateur_angryballs.solution.vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*
    Ce decorateur gere le son des collisions des billes sur les bords du cadre
 */
public class DecorateurSonMur extends DecorateurBille implements ItemListener {

    private static final int DELAI_MIN = 10;    /* delai minimum de rafraichissement du son. en millisecondes */
    public static final int DELAI_MAX = 150;    /* delai maximum de rafraichissement du son. en millisecondes */
    public SonLong sonLong;                        /* bande son e diffuser */
    int i;              /* ne de l'element de sonLong e jouer. on doit avoir i >= 0.
                    sonLong se charge de faire le modulo pour obtenir un indice correct
                    et pour boucler ainsi sur le tableau inscrit dans sonLong */
    long dernierInstant;        /* dernier instant oe le son a ete diffuse */
    VueBillard vueBillard;

    private static final double COEFF_VOLUME = 6;


    public DecorateurSonMur(Bille b, SonLong sonLong, VueBillard vueBillard) {
        super(b);
        this.sonLong = sonLong;
        i = 0;
        dernierInstant = System.currentTimeMillis();
        this.vueBillard = vueBillard;
    }


    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur){

        Vecteur p = this.getPosition();
        Vecteur v = this.getVitesse();

        int[] x = new int[4];
        if(largeur>hauteur){

            x[0] = (int) (DistanceToLine(this.getPosition(),new Vecteur(0,0),new Vecteur(largeur,0)) -(this.getRayon()-10));
            x[1] = (int) (DistanceToLine(this.getPosition(),new Vecteur(largeur,hauteur),new Vecteur(0,hauteur))- (this.getRayon()-10));
            x[2] = (int) (DistanceToLine(this.getPosition(),new Vecteur(largeur,0),new Vecteur(largeur,hauteur)) - (this.getRayon()-10));
            x[3] = (int) (DistanceToLine(this.getPosition(),new Vecteur(0,0),new Vecteur(0,hauteur)) - (this.getRayon()-10));
        }
        double min = getMin(x);
        double volume =  Math.abs((1 - (min - 10) / (250 - 10)));
        double n = v.norme();
        double y = Math.exp(-COEFF_VOLUME*n);                // y = e^(-COEFF*n). on obtient donc 0 < y <= 1
        double x1 = p.x/largeur;
        double balance = 2*x1 - 1;
        //System.err.println("volume = " + volume);
        int delai = (int)(DELAI_MIN*volume + DELAI_MAX*y);              /* le delai entre 2 diffusions diminue lorsque la vitesse augmente */
        long instant = System.currentTimeMillis();
        if (instant - this.dernierInstant >=delai)                      /* la frequence de diffusion augmente donc avec la vitesse de la bille */
        {
            double coeffPitch = 1;
            this.sonLong.joue(i++, volume, balance, coeffPitch);            /* le son est diffuse dans un thread separe */ //TODO : modifier toute la classe pour ne faire le son qu'une fois qu'elle touche une fois le mur et avec l'intensité
            this.dernierInstant= instant;
        }



        //this.bille.collisionContour(abscisseCoinHautGauche,ordonneeCoinHautGauche,largeur,hauteur);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {                                                                               //System.err.println("dans BilleHurlanteMvtNewtonArret.itemStateChanged au debut");
        if (e.getSource() instanceof BoutonChoixHurlement) {
            BoutonChoixHurlement boutonChoixHurlement = (BoutonChoixHurlement) (e.getSource());
            this.sonLong = boutonChoixHurlement.sonLong;                                //System.err.println("dans BilleHurlanteMvtNewtonArret.itemStateChanged");
        }
    }


    public double getMin(int[] x){
        int min = 10000;
        for(int i = 0; i<4; i++){
            min= Math.min(min,x[i]);
        }
        return min;
    }

    public static double distanceRect(Vecteur p, Vecteur rD, Vecteur rF) {
        var dx = Math.max(rD.x - p.x, p.x - rF.x);
        var dy = Math.max(rD.y - p.y, p.y - rF.y);
        return Math.sqrt(dx*dx + dy*dy);
    }
    public static double DistanceToLine(Vecteur point, Vecteur dL, Vecteur fL) {
        return Math.sqrt(DistanceSquareToLine(point, dL, fL));
    }


    public static double DistanceSquareToLine(Vecteur p, Vecteur dL, Vecteur fL) {
        double d1 = dL.x - fL.x;
        double d2 = dL.y - fL.y;
        double d = d1 * d1 + d2 * d2;
        if (d <= 0)
            return 0;
        double n = p.x * dL.y - dL.x * p.y + dL.x * fL.y - fL.x * dL.y + fL.x * p.y - p.x * fL.y;
        return n * n / d;
    }



}
