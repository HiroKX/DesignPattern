package exodecorateur_angryballs.solution.vues.awt.boutons.ecouteurs;

import exodecorateur_angryballs.solution.AnimationBilles;

import java.util.Observable;
import java.util.Observer;

public class EcouteurBoutonReinitialiser implements Observer {
    AnimationBilles animationBilles;

    public EcouteurBoutonReinitialiser(AnimationBilles animationBilles) {
        this.animationBilles = animationBilles;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("reinitialiser"))
            this.animationBilles.reinitialiser();
    }
}

