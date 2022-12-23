package exodecorateur_angryballs.solution.vues.awt.boutons;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.HeadlessException;

import musique.SonLong;

public class BoutonChoixHurlement extends Checkbox {
    public SonLong sonLong;

    /**
     * @param sonLong : son associé à ce bouton
     */
    public BoutonChoixHurlement(CheckboxGroup group, boolean state, SonLong sonLong) throws HeadlessException {
        super(sonLong.getNom(), group, state);
        this.sonLong = sonLong;
    }

}
