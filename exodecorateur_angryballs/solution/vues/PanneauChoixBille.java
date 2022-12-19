package exodecorateur_angryballs.solution.vues;

import exodecorateur_angryballs.solution.modele.Bille;

import java.awt.*;
import java.util.Vector;

public class PanneauChoixBille extends Panel {
    CheckboxGroup checkboxGroup;
    BoutonChoixBille boutons[];

    public PanneauChoixBille(Vector<Bille> billes) {
        this.add(new Label("Choix de la bille :"));
        this.boutons = new BoutonChoixBille[billes.size()];
        this.checkboxGroup = new CheckboxGroup();
        this.setLayout(new GridLayout(1, this.boutons.length));
        for (int i = 0; i < this.boutons.length; ++i) {
            this.boutons[i] = new BoutonChoixBille(checkboxGroup, false, billes.get(i));
            this.add(this.boutons[i]);
        }
    }

}