package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.maladroit.vues.VueBillard;
import exodecorateur_angryballs.solution.decorateur.DecorateurHurlement;
import exodecorateur_angryballs.solution.modele.Bille;
import musique.SonLong;

/**
    Parser qui recupere l'information de si les billes font du bruit ou non
 */
public class parseHurlement extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(!ligne.equals("x"))
            return bille;
        return new DecorateurHurlement(bille, (SonLong) args[0], (VueBillard) args[1]);
    }
}
