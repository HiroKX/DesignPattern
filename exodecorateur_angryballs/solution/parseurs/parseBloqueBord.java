package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurBloqueBord;
import exodecorateur_angryballs.solution.modele.Bille;

/**
    Parser qui indique si les billes entre en collision avec les bords du cadre ou non
 */
public class parseBloqueBord extends Parser {
    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(!ligne.equals("x"))
            return bille;
        return new DecorateurBloqueBord(bille);
    }

  }
