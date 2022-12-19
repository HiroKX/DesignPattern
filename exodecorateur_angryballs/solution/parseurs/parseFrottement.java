package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurFrottement;
import exodecorateur_angryballs.solution.modele.Bille;

public class parseFrottement extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(ligne.isEmpty())
            return bille;
        return new DecorateurFrottement(bille,Double.parseDouble(ligne));
    }
}
