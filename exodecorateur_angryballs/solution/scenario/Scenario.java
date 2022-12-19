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

    protected SonLong sonChocBille;

    public Scenario(VueBillard cadre, SonLong sonChoc){
        this.cadre =cadre;
        this.sonChocBille = sonChoc;
        this.nom="pas de nom";
        this.billes = new Vector<Bille>();
    }

    public Scenario(String nom, Vector<Bille> billes) {
        this.nom = nom;
        this.billes = billes;
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


}