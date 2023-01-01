package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurPasseMuraille;
import exodecorateur_angryballs.solution.modele.Bille;

/**
    Parser qui sert à definir si une bille peut passer à travers les murs ou non
 */
public class parsePasseMuraille extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(!ligne.equals("x"))
            return bille;
        return new DecorateurPasseMuraille(bille);
    }
}
