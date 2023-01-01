package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;

import java.awt.*;

/**
    Ce décorateur gere aussi la couleur de la bille en reconnaissant le code : r, g, b
 */
public class DecorateurCouleurRGB extends DecorateurBille{

    public DecorateurCouleurRGB(Bille b, int r, int g, int bl){
        super(b);
        Color c = new Color(r,g,bl);
        this.setCouleur(c.getRGB());
    }

}
