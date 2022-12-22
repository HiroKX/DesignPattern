package exodecorateur_angryballs.solution.vues.awt.panneaux;

import exodecorateur_angryballs.solution.scenario.Scenario;
import exodecorateur_angryballs.solution.vues.awt.boutons.BoutonChoixScenario;

import java.awt.*;
import java.util.ArrayList;

public class PanneauChoixScenario extends Panel {
    CheckboxGroup checkboxGroup;
    public BoutonChoixScenario boutons[];

    public PanneauChoixScenario(ArrayList<Scenario> scenarios) {
        this.add(new Label("Choix du scenario :"));
        this.boutons = new BoutonChoixScenario[scenarios.size()];
        this.checkboxGroup = new CheckboxGroup();
        this.setLayout(new GridLayout(1, this.boutons.length));
        for (int i = 0; i < this.boutons.length; ++i) {
            this.boutons[i] = new BoutonChoixScenario(checkboxGroup, false, scenarios.get(i));
            this.add(this.boutons[i]);
        }
        this.boutons[0].setState(true);
    }

}

