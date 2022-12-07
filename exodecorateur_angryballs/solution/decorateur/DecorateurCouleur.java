package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;

import java.awt.*;

public class DecorateurCouleur extends DecorateurBille {

    private Color couleur;

    public DecorateurCouleur(Bille b, Color couleur) {
        super(b);
        this.bille.setCouleur(couleur.getRGB());
    }

    public String toString(){
        return this.bille.toString() + " couleur = "+this.getCouleur();
    }
}
