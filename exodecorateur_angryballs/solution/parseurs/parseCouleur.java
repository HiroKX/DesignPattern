package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurCouleur;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.parseurs.couleur.parseCouleurCOR;

/**
    Parser qui recupere la couleur en Int, en RGB, ou en String
 */
public class parseCouleur extends Parser {
    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(ligne.isEmpty())
            return bille;
        return new DecorateurCouleur(bille, parseCouleurCOR.CouleurCOR(ligne));
    }
}