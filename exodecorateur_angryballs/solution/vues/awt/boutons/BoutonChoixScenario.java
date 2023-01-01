package exodecorateur_angryballs.solution.vues.awt.boutons;

import exodecorateur_angryballs.solution.scenario.Scenario;

import java.awt.*;
/** Bouton de choix de scénario
 */
public class BoutonChoixScenario extends Checkbox{
    /** Scénario associé à ce bouton
     */
    Scenario scenario;

    public BoutonChoixScenario(CheckboxGroup checkboxGroup, boolean b, Scenario scenario) {
        super(scenario.getNom(), checkboxGroup, b);
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }

}

