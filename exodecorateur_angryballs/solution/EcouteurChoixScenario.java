package exodecorateur_angryballs.solution;

import exodecorateur_angryballs.solution.scenario.Scenario;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EcouteurChoixScenario implements ItemListener {
    Scenario scenario;
    AnimationBilles animationBilles;

    public EcouteurChoixScenario(Scenario scenario, AnimationBilles animationBilles) {
        this.scenario = scenario;
        this.animationBilles = animationBilles;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED)
            this.animationBilles.setBilles(this.scenario);
    }
}
