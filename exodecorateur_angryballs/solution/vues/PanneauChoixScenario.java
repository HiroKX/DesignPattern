package exodecorateur_angryballs.solution.vues;

import exodecorateur_angryballs.solution.Scenario;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

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

