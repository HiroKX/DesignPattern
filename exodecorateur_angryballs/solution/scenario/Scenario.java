package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.vues.VueBillard;
import musique.SonLong;

import java.util.Vector;

/**
    Classe abstraite qui permet de definir un scenario (une liste de bille avec des caracteristiques)
 */
public abstract class Scenario {
    /** Cadre */
    protected VueBillard cadre;
    /** Le nom du scénario */
    protected String nom;
    /** Les billes du scénario */
    protected Vector<Bille> billes;
    /** Son des chocs des billes */
    protected SonLong sonChocBille;

    protected double vMax;
    protected double xMax, yMax;
    protected double rayon;
    protected SonLong sonHurlement;

    public void setVecteur(){
        this.vMax = 0.1;
        this.xMax = this.cadre.largeurBillard();      // abscisse maximal
        this.yMax = this.cadre.hauteurBillard();      // ordonnee maximale
        this.rayon  = 0.05 * Math.min(xMax, yMax);    // rayon des billes
    }

    public String getNom() {
        return nom;
    }

    public Vector<Bille> getBilles() {
        return billes;
    }

    /**
     * Cherche une bille avec sa clée
     * @param i la clée de la bille
     * @return la bille
     */
    public Bille getBilleInt(int i){
        for(int j = 0; j < billes.size(); j++) {
            if (billes.elementAt(j).getClef() == i)
                return billes.elementAt(j);
        }
        return null;
    }

    public void setBilles(Vector<Bille> billes) {
        this.billes = billes;
    }

    /**
     * Remet les billes dans leur position de départ
     */
    public void resetBilles(){
        this.billes = this.getInitBilles();
    }

    public abstract Vector<Bille> getInitBilles();

    /**
     * Supprime une bille du scenario
     * @param bille la bille à supprimer
     */
    public void removeBille(Bille bille){
        this.billes.remove(bille);
    }
}