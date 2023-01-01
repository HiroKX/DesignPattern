package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.BilleConcrete;
import exodecorateur_angryballs.solution.vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;

import java.util.Vector;

/**
    Scenario où l'on a un seulement un nombre de billes à définir
 */
public class ScenarioNB extends Scenario {
    /** Le nombre de billes du scénario */
    int nbBilles;
    public ScenarioNB(VueBillard cadre, SonLong sonBilleChoc, int nbBilles) {
        assert cadre != null;
        assert sonBilleChoc != null;
        assert nbBilles >= 0;
        this.cadre = cadre;
        this.sonChocBille = sonBilleChoc;
        this.nbBilles = nbBilles;
        this.nom = "ScenarioNB";
        this.setVecteur();
        this.billes= this.getInitBilles();
    }

    /** Crée un nombre nbBilles de billes avec des positions et des vitesses aléatoires */
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

