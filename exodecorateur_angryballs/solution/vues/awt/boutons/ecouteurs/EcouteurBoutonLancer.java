package exodecorateur_angryballs.solution.vues.awt.boutons.ecouteurs;

import exodecorateur_angryballs.solution.AnimationBilles;

import java.util.Observable;
import java.util.Observer;

/** Ecouteur de bouton pour lancer l'animation
 */
public class EcouteurBoutonLancer implements Observer {
    AnimationBilles animationBilles;

    public EcouteurBoutonLancer(AnimationBilles animationBilles) {
        this.animationBilles = animationBilles;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("lancer"))
            this.animationBilles.lancerAnimation();
    }
}
