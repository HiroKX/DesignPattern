package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.vues.VueBillard;
import musique.SonLong;

import java.util.Vector;

public class ScenarioNB extends Scenario {
    public ScenarioNB(VueBillard cadre, SonLong sonBilleChoc, int nbBilles) {
        super(cadre, sonBilleChoc);
        this.nom = "ScenarioNB";
        this.billes = new Vector<>();
        for (int i = 0; i < nbBilles; i++) {
           // Bille b = new BilleConcrete(Vecteur.créationAléatoire(0, 0, cadre.largeurBillard(), cadre.hauteurBillard()),r,Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax),new Vecteur(0, 0.0001));
           // billes.add(b);
        }
    }
}

