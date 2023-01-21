package exodecorateur_angryballs.solution;


import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;

public class RobinLagler {
    static double EPSILON = 1.0E-6;
    public static double A;

    public RobinLagler() {}

    public static void collisionBilleContourPasseMuraille(Vecteur position, double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        position.x = traverseBord(position.x, abscisseCoinHautGauche, largeur);
        position.y = traverseBord(position.y, ordonnéeCoinHautGauche, hauteur);
    }

    private static double traverseBord(double x, double xMin, double largeur) {
        double xMax = xMin + largeur;
        double d;
        double d1;
        int q;
        if (x > xMax) {
            d = (x - xMin) / largeur;
            d1 = Math.floor(d);
            q = (int)d1;
            return x - (double)q * largeur;
        } else if (x < xMin) {
            d = (xMax - x) / largeur;
            d1 = Math.floor(d);
            q = (int)d1;
            return x + (double)q * largeur;
        } else {
            return x;
        }
    }

    public static void collisionBilleContourAvecArretHorizontal(Vecteur position, double rayon, Vecteur vitesse, double abscisseCoinHautGauche, double largeur) {
        double[] t = arretSurBord(vitesse.x, position.x, rayon, abscisseCoinHautGauche, largeur);
        vitesse.x = t[0];
        position.x = t[1];
    }

    public static void collisionBilleContourAvecArretVertical(Vecteur position, double rayon, Vecteur vitesse, double ordonnéeCoinHautGauche, double hauteur) {
        double[] t = arretSurBord(vitesse.y, position.y, rayon, ordonnéeCoinHautGauche, hauteur);
        vitesse.y = t[0];
        position.y = t[1];
    }

    private static double[] arretSurBord(double v, double x, double rayon, double xMin, double largeur) {
        double[] t = new double[2];
        double xMax = xMin + largeur;
        if (x + rayon > xMax) {
            t[0] = 0.0;
            t[1] = xMax - rayon - EPSILON;
        } else if (x - rayon < xMin) {
            t[0] = 0.0;
            t[1] = xMin + EPSILON + rayon;
        } else {
            t[0] = v;
            t[1] = x;
        }

        return t;
    }

    public static boolean collisionBilleSegmentAvecRebond(Vecteur position, double rayon, Vecteur vitesse, Vecteur P0, Vecteur P1) {
        Vecteur[] base = Geop.base(P0, P1);
        Vecteur N = base[1];
        double distanceSignée = position.difference(P0).produitScalaire(N);
        if (distanceSignée >= rayon) {
            return false;
        } else {
            double vN = vitesse.produitScalaire(N);
            if (vN >= 0.0) {
                return false;
            } else {
                Vecteur deltaV = N.produit(-2.0 * vN);
                vitesse.ajoute(deltaV);
                return true;
            }
        }
    }

    public static boolean collisionBilleContourAvecRebond(Vecteur position, double rayon, Vecteur vitesse, double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        Vecteur min = new Vecteur(abscisseCoinHautGauche, ordonnéeCoinHautGauche);
        Vecteur diago = new Vecteur(largeur, hauteur);
        Vecteur max = min.somme(diago);
        Vecteur[] coins = new Vecteur[]{min, new Vecteur(max.x, min.y), max, new Vecteur(min.x, max.y), null};
        coins[4] = coins[0];

        for(int i = 1; i < coins.length; ++i) {
            if (collisionBilleSegmentAvecRebond(position, rayon, vitesse, coins[i - 1], coins[i])) {
                return true;
            }
        }

        return false;
    }

    public static boolean CollisionBilleBille(final Vecteur G1, double rayon1, Vecteur v1, double m1,
                                              final Vecteur G2, double rayon2, Vecteur v2, double m2) {
        Vecteur G1G2 = Vecteur.difference(G2, G1);
        double nG1G2_2 = G1G2.normeCarrée();
        double r = rayon1 + rayon2;
        double r2 = r * r;
        A = 0;
        if (nG1G2_2 >= r2) {
            return false;
        } else {
            double nG1G2 = Math.sqrt(nG1G2_2);
            Vecteur N = G1G2.produit(1.0 / nG1G2);
            double v1N = v1.produitScalaire(N);
            double v2N = v2.produitScalaire(N);
            double a = v1N - v2N;
            if (a <= 0.0) {
                return false;
            } else {
                A = a;
                double masseTotale = m1 + m2;
                double alfa = (m1 - m2) / masseTotale;
                double deuxSurM = 2.0 / masseTotale;
                double v1Np = alfa * v1N + m2 * deuxSurM * v2N;
                double v2Np = m1 * deuxSurM * v1N - alfa * v2N;
                Vecteur U = N.rotationQuartDeTour();
                double v1T = v1.produitScalaire(U);
                double v2T = v2.produitScalaire(U);
                v1.set(Vecteur.combinaisonLinéaire(v1Np, N, v1T, U));
                v2.set(Vecteur.combinaisonLinéaire(v2Np, N, v2T, U));
                return true;
            }
        }
    }

}
