package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.maladroit.vues.VueBillard;
import exodecorateur_angryballs.solution.decorateur.DecorateurHurlement;
import exodecorateur_angryballs.solution.modele.Bille;
import musique.SonLong;

public class parseHurlement extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(ligne.isEmpty())
            return bille;
        return new DecorateurHurlement(bille, (SonLong) args[0], (VueBillard) args[1]);
    }
}
