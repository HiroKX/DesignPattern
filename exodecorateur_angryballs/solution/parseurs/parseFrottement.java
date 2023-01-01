package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurFrottement;
import exodecorateur_angryballs.solution.modele.Bille;

/**
    Parser qui permet de dire si la bille subit les frottements de l air ou non, et si oui indique le niveau de frottements
 */
public class parseFrottement extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(ligne.isEmpty())
            return bille;
        return new DecorateurFrottement(bille,Double.parseDouble(ligne));
    }
}
