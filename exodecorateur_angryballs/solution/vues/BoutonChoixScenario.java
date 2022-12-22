package exodecorateur_angryballs.solution.vues;

import exodecorateur_angryballs.solution.scenario.Scenario;

import java.awt.*;

public class BoutonChoixScenario extends Checkbox{
    Scenario scenario;

    public BoutonChoixScenario(CheckboxGroup checkboxGroup, boolean b, Scenario scenario) {
        super(scenario.getNom(), checkboxGroup, b);
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }

}

