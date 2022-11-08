package exodecorateur_angryballs.maladroit.decorateur;

import exodecorateur_angryballs.maladroit.modele.Bille;

public class DecorateurCouleurInt extends DecorateurBille{

    public DecorateurCouleurInt(Bille b, int rgb){
        super(b);
        this.setCouleur(rgb);
    }

}
