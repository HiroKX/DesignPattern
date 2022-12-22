package exodecorateur_angryballs.solution.vues.awt.boutons.ecouteurs;

import exodecorateur_angryballs.solution.AnimationBilles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * ICI : IL N'Y A RIEN A CHANGER
 */

public class EcouteurBoutonArreter implements ActionListener {
    AnimationBilles animationBilles;

    public EcouteurBoutonArreter(AnimationBilles animationBilles) {
        this.animationBilles = animationBilles;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.animationBilles.arreterAnimation();
    }

}
