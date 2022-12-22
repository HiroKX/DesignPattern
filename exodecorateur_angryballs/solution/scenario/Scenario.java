package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.vues.VueBillard;
import musique.SonLong;

import java.util.Vector;

public abstract class Scenario {
    protected VueBillard cadre;
    // Le nom du scénario
    protected String nom;
    // Les billes du scénario
    protected Vector<Bille> billes;
    protected Vector<Bille> billesReset;

    protected SonLong sonChocBille;

    protected double vMax;
    protected double xMax, yMax;
    protected double rayon;

    public Scenario(VueBillard cadre, SonLong sonChoc){
        this.cadre =cadre;
        this.sonChocBille = sonChoc;
        this.nom="pas de nom";
        this.billes = new Vector<Bille>();
        this.billesReset = billes;
    }

    public Scenario(String nom, Vector<Bille> billes) {
        this.nom = nom;
        this.billes = billes;
        this.billesReset = billes;
    }

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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setBilles(Vector<Bille> billes) {
        this.billes = billes;
    }

    public void resetBilles(){
        //this.billes.remove(0);

        this.billes = this.getInitBilles();
    }

    public abstract Vector<Bille> getInitBilles();
    public void removeBille(Bille bille){
        this.billes.remove(bille);
    }
}