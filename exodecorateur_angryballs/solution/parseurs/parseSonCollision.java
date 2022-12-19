package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurSonCollision;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.vues.VueBillard;
import musique.SonLong;

public class parseSonCollision extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(ligne.isEmpty())
            return bille;
        return new DecorateurSonCollision(bille, (SonLong) args[0], (VueBillard) args[1]);
    }
}
