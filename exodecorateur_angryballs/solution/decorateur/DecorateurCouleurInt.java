package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;

/**
    Ce décorateur gere aussi la couleur de la bille en reconnaissant le code rgb
 */
public class DecorateurCouleurInt extends DecorateurBille{

    public DecorateurCouleurInt(Bille b, int rgb){
        super(b);
        this.setCouleur(rgb);
    }

}
