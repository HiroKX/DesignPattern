package exodecorateur_angryballs.solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurBoutonReinitialiser implements ActionListener {
    AnimationBilles animationBilles;

    public EcouteurBoutonReinitialiser(AnimationBilles animationBilles) {
        this.animationBilles = animationBilles;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.animationBilles.reinitialiser();
    }
}

