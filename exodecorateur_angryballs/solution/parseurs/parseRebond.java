package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurRebond;
import exodecorateur_angryballs.solution.modele.Bille;

public class parseRebond extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(!ligne.equals("x"))
            return bille;
        return new DecorateurRebond(bille);
    }
}
