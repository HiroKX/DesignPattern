package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurLance;
import exodecorateur_angryballs.solution.modele.Bille;

/**
 Parser qui sert à definir si nous pouvons lancer une bille ou non
 */
public class parseLance extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(ligne.isEmpty()) {
            return bille;
        }
        return new DecorateurLance(bille);
    }
}
