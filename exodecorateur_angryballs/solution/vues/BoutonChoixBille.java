package exodecorateur_angryballs.solution.vues;

import exodecorateur_angryballs.solution.modele.Bille;

import java.awt.*;

public class BoutonChoixBille extends Checkbox {
    public Bille bille;

    public BoutonChoixBille(CheckboxGroup group, boolean state, Bille bille) throws HeadlessException {
        super(bille.getClef()+"", group, state);
        this.bille = bille;
    }
}