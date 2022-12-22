package exodecorateur_angryballs.solution.vues.awt.boutons.ecouteurs;

import exodecorateur_angryballs.solution.AnimationBilles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurBoutonLancer implements ActionListener {
    AnimationBilles animationBilles;

    public EcouteurBoutonLancer(AnimationBilles animationBilles) {
        this.animationBilles = animationBilles;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.animationBilles.lancerAnimation();
    }
}
