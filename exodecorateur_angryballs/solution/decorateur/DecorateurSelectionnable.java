package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;

/*
    Ce decorateur permet à la bille d'être selectionnee. Ce decorateur est surtout utile si le decorateur Attraper est actif aussi
 */
public class DecorateurSelectionnable extends DecorateurBille{

    boolean choisie;

    public DecorateurSelectionnable(Bille b) {
        super(b);
    }

    public void setChoisie(boolean b) {
        this.choisie = b;
    }
    public boolean getChoisie() {
        return this.choisie;
    }

}
