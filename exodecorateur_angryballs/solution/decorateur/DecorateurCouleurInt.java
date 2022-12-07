package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;

public class DecorateurCouleurInt extends DecorateurBille{

    public DecorateurCouleurInt(Bille b, int rgb){
        super(b);
        this.setCouleur(rgb);
    }

}
