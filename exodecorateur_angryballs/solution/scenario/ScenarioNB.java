package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.BilleConcrete;
import exodecorateur_angryballs.solution.vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;

import java.util.Vector;

public class ScenarioNB extends Scenario {
    public ScenarioNB(VueBillard cadre, SonLong sonBilleChoc, int nbBilles) {
        super(cadre, sonBilleChoc);
        this.nom = "ScenarioNB";
        this.setVecteur();
        for (int i = 0; i < nbBilles; i++) {
            Bille b = new BilleConcrete(Vecteur.créationAléatoire(0, 0, cadre.largeurBillard(), cadre.hauteurBillard()),rayon, Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax),new Vecteur(0, 0.0001));
            billes.add(b);
        }
    }
}

