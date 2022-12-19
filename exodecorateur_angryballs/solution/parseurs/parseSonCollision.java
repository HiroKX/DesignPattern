package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurSonCollision;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.son.SonLongRobin;
import exodecorateur_angryballs.solution.vues.VueBillard;

public class parseSonCollision extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(!ligne.equals("x"))
            return bille;
        return new DecorateurSonCollision(bille, (SonLongRobin) args[0], (VueBillard) args[1]);
    }
}
