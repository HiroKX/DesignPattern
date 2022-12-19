package exodecorateur_angryballs.solution;

import exodecorateur_angryballs.solution.scenario.Scenario;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EcouteurChoixScenario implements ItemListener {
    private final Scenario scenario;
    private final AnimationBilles animationBilles;

    public EcouteurChoixScenario(Scenario scenario, AnimationBilles animationBilles) {
        this.scenario = scenario;
        this.animationBilles = animationBilles;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("Scenario choisi : " + this.scenario.getNom());
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.animationBilles.setBilles(this.scenario.getBilles());
        }
    }
}
