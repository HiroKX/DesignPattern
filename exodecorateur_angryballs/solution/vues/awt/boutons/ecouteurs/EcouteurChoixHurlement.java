package exodecorateur_angryballs.solution.vues.awt.boutons.ecouteurs;

import exodecorateur_angryballs.solution.AnimationBilles;
import musique.SonLong;

import java.util.Observable;
import java.util.Observer;

/**
 * Ecouteur de choix de hurlement NON UTILISE
 */


public class EcouteurChoixHurlement implements Observer {
    /** Hurlement choisi */
    private final SonLong sonLong;
    private final AnimationBilles animationBilles;

    public EcouteurChoixHurlement(SonLong sonLong, AnimationBilles animationBilles) {
        this.sonLong = sonLong;
        this.animationBilles = animationBilles;
    }

    @Override
    public void update(Observable o, Object arg) {
        String s = (String) arg;
        String[] args = s.split(";");
        if (args[0].equals("hurlement") && args[1].equals(this.sonLong.getNom()))
            this.animationBilles.setHurlement(this.sonLong);
    }
}
