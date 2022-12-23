package exodecorateur_angryballs.solution.vues.awt.boutons.ecouteurs;

import exodecorateur_angryballs.solution.AnimationBilles;
import exodecorateur_angryballs.solution.scenario.Scenario;

import java.util.Observable;
import java.util.Observer;

public class EcouteurChoixScenario implements Observer {
    Scenario scenario;
    AnimationBilles animationBilles;

    public EcouteurChoixScenario(Scenario scenario, AnimationBilles animationBilles) {
        this.scenario = scenario;
        this.animationBilles = animationBilles;
    }

    @Override
    public void update(Observable o, Object arg) {
        String s = (String) arg;
        String[] args = s.split(";");
        if (args[0].equals("scenario") && args[1].equals(this.scenario.getNom()))
            this.animationBilles.setBilles(this.scenario);
    }
}
