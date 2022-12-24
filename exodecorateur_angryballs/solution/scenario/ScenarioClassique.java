package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.vues.VueBillard;
import musique.SonLong;

import java.util.Vector;

public class ScenarioClassique extends Scenario {
    public ScenarioClassique(VueBillard cadre, SonLong sonChocBille){
        super(cadre, sonChocBille);
    }

    public ScenarioClassique(String nomScenario, Vector<Bille> billes,SonLong sonChocBille) {
        super(nomScenario,billes);
        this.sonChocBille = sonChocBille;
    }
    @Override
    public Vector<Bille> getBilles(){
        return this.billes;
    }

    public void addBille(Bille b){
        this.billes.add(b);
    }

    @Override
    public Vector<Bille> getInitBilles() {
        return this.billes;
    }
}
