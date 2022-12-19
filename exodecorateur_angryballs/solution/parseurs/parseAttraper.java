package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.decorateur.DecorateurAttraper;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.vues.CadreAngryBallsAWT;
import musique.SonLong;


public class parseAttraper extends Parser {

    @Override
    public Object parser(String ligne, Bille bille, Object[] args) throws Exception {
        if(ligne.isEmpty()) {
            return bille;
        }
        DecorateurAttraper b = new DecorateurAttraper(bille);
        return b;
    }
  }
