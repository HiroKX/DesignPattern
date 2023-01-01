package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.modele.Bille;
import musique.SonLong;

import java.util.Vector;
/** Classe qui permet de definir un scenario classique (de base) */
public class ScenarioClassique extends Scenario {
    public ScenarioClassique(String nomScenario, Vector<Bille> billes,SonLong sonChocBille) {
        assert nomScenario != null;
        assert billes != null;
        assert sonChocBille != null;
        this.nom = nomScenario;
        this.billes = billes;
        this.sonChocBille = sonChocBille;
    }

    public ScenarioClassique(String nomScenario,SonLong sonChocBille) {
        assert nomScenario != null;
        assert sonChocBille != null;
        this.nom = nomScenario;
        this.billes = new Vector<>();
        this.sonChocBille = sonChocBille;
    }
    @Override
    public Vector<Bille> getBilles(){
        return this.billes;
    }

    public void addBille(Bille b){
        this.billes.add(b);
    }

    //public void addBille(Bille b){ this.billes.add(b);}

    @Override
    public Vector<Bille> getInitBilles() {
        return this.billes;
    }
}
