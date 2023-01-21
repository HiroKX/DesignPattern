package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.decorateur.*;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.BilleConcrete;
import exodecorateur_angryballs.solution.son.SonLongRobin;
import exodecorateur_angryballs.solution.vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;

import java.awt.*;
import java.util.Vector;

/**
    Classe qui definit le scenario billard qui est en heritage de la clase abstraite Scenario
 */
public class ScenarioBillard extends Scenario {
    public ScenarioBillard(VueBillard cadre, SonLong sonBilleChoc) {
        assert cadre != null;
        assert sonBilleChoc != null;
        this.cadre = cadre;
        this.sonChocBille = sonBilleChoc;
        this.nom = "Billard";
        this.setVecteur();
        this.setBillard();
    }

    /** Crée la liste de billes presentent dans le billard sous la forme d'un billard */
    @Override
    public Vector<Bille> getInitBilles() {
        double coeffFrottement = 0.5;
        // Bille blanche
        Vector<Bille> lBilles = new Vector<>();
        Bille b = new DecorateurSonCollision(new DecorateurLance(new DecorateurRebond(
                new DecorateurFrottement(new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) - xMax / 4, (yMax / 2)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.WHITE), coeffFrottement))), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        int espace = 3;
        //Ligne 5
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) + xMax / 3, (yMax / 2)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.RED), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) + xMax / 3, (yMax / 2) + rayon * 2), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.GREEN), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) + xMax / 3, (yMax / 2) + (rayon * 2) * 2), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.MAGENTA), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) + xMax / 3, (yMax / 2) - (rayon * 2)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.ORANGE), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) + xMax / 3, (yMax / 2) - (rayon * 2) * 2), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.ORANGE), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);

        // Ligne 4
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 2) + espace, (yMax / 2) - rayon), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.RED), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 2) + espace, (yMax / 2) - (rayon * 3)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.RED), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 2) + espace, (yMax / 2) + rayon), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.GREEN), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 2) + espace, (yMax / 2) + (rayon * 3)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.MAGENTA), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);

        // Ligne 3
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 4) + espace * 2, (yMax / 2)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.BLACK), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 4) + espace * 2, (yMax / 2) + rayon * 2), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.RED), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 4) + espace * 2, (yMax / 2) - rayon * 2), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.BLUE), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);

        // Ligne 2
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 6) + espace * 3, (yMax / 2) + rayon), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.BLUE), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 6) + espace * 3, (yMax / 2) - rayon), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.YELLOW), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        // Ligne 1
        b = new DecorateurSonCollision(new DecorateurRebond(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 8) + espace * 4, (yMax / 2)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.YELLOW), coeffFrottement)), (SonLongRobin) sonChocBille, cadre);
        lBilles.add(b);
        return lBilles;
    }

    public void setBillard() {
        this.billes = getInitBilles();
    }
}

