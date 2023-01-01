package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

/**
    Ce decorateur gere le poids de la bille. La bille est attirée vers le bas
 */
public class DecorateurPesanteur extends DecorateurBille{

    Vecteur pesanteur;

    public DecorateurPesanteur(Bille b, Vecteur pesanteur){
        super(b);
        this.pesanteur = pesanteur;
    }

    @Override
    public Vecteur gestionAcceleration(Vector<Bille> billes) {
        super.gestionAcceleration(billes);
        this.getAcceleration().ajoute(this.pesanteur);
        return this.getAcceleration();
    }

}
