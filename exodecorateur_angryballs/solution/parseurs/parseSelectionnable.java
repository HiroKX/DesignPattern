package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurSelectionnable;
import exodecorateur_angryballs.solution.modele.Bille;

public class parseSelectionnable extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(ligne.isEmpty()) {
            return bille;
        }
        return new DecorateurSelectionnable(bille);
    }
}
