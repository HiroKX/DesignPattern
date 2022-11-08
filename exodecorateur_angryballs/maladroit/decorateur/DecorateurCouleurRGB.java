package exodecorateur_angryballs.maladroit.decorateur;

import exodecorateur_angryballs.maladroit.modele.Bille;

import java.awt.*;

public class DecorateurCouleurRGB extends DecorateurBille{

    public DecorateurCouleurRGB(Bille b, int r, int g, int bl){
        super(b);
        Color c = new Color(r,g,bl);
        this.setCouleur(c.getRGB());
    }

}
