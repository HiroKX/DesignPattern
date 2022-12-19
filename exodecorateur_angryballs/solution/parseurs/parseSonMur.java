package exodecorateur_angryballs.solution.parseurs;


import exodecorateur_angryballs.solution.vues.VueBillard;
import exodecorateur_angryballs.solution.decorateur.DecorateurSonMur;
import exodecorateur_angryballs.solution.modele.Bille;

import musique.SonLong;

public class parseSonMur extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(!ligne.equals('x')) {
            return bille;
        }
        return new DecorateurSonMur(bille, (SonLong) args[0], (VueBillard) args[1]);
    }
}
