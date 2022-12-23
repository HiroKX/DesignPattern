package exodecorateur_angryballs.solution.vues.awt.boutons.ecouteurs;

import exodecorateur_angryballs.solution.AnimationBilles;

import java.util.Observable;
import java.util.Observer;

public class EcouteurBoutonArreter implements Observer {
    AnimationBilles animationBilles;

    public EcouteurBoutonArreter(AnimationBilles animationBilles) {
        this.animationBilles = animationBilles;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("arreter"))
            this.animationBilles.arreterAnimation();
    }
}
