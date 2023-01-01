package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurPesanteur;
import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

/*
    Parser qui recupere l information de si la bille subit la pesanteur (bille attirée vers le bord sud du cadre) ou non
 */
public class parsePesanteur extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(!ligne.equals("x"))
            return bille;
        return new DecorateurPesanteur(bille,new Vecteur(0, 0.001));
    }
}
