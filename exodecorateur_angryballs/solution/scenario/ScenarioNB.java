package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.BilleConcrete;
import exodecorateur_angryballs.solution.vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;

import java.util.Vector;

public class ScenarioNB extends Scenario {
    int nbBilles;
    public ScenarioNB(VueBillard cadre, SonLong sonBilleChoc, int nbBilles) {
        this.cadre = cadre;
        this.sonChocBille = sonBilleChoc;
        this.nbBilles = nbBilles;
        this.nom = "ScenarioNB";
        this.setVecteur();
        this.billes= this.getInitBilles();
    }

    @Override
    public Vector<Bille> getInitBilles() {
        Vector<Bille> lBilles = new Vector<>();
        for (int i = 0; i < nbBilles; i++) {
            Bille b = new BilleConcrete(Vecteur.créationAléatoire(0, 0, cadre.largeurBillard(), cadre.hauteurBillard()),rayon, Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax),new Vecteur(0, 0.0001));
            lBilles.add(b);
        }
        return lBilles;
    }
}

