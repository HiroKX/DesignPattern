package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.decorateur.*;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.BilleConcrete;
import exodecorateur_angryballs.solution.son.SonLongRobin;
import exodecorateur_angryballs.solution.vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;

import java.awt.*;

public class ScenarioBillard extends Scenario {
    public ScenarioBillard(VueBillard cadre, SonLong sonBilleChoc) {
        super(cadre, sonBilleChoc);
        this.nom = "Billard";
        this.setVecteur();
        this.setBillard();
    }

    public void setBillard() {
        // Bille blanche
        Bille b = new DecorateurSonCollision(new DecorateurAttraper(new DecorateurBloqueBord(
                new DecorateurFrottement(new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) - xMax / 4, (yMax / 2)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.WHITE), 4.5))), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        int espace = 3;
        //Ligne 5
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) + xMax / 3, (yMax / 2)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.RED), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) + xMax / 3, (yMax / 2) + rayon * 2), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.GREEN), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) + xMax / 3, (yMax / 2) + (rayon * 2) * 2), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.MAGENTA), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) + xMax / 3, (yMax / 2) - (rayon * 2)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.ORANGE), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((xMax / 2) + xMax / 3, (yMax / 2) - (rayon * 2) * 2), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.ORANGE), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);

        // Ligne 4
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 2) + espace, (yMax / 2) - rayon), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.RED), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 2) + espace, (yMax / 2) - (rayon * 3)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.RED), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 2) + espace, (yMax / 2) + rayon), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.GREEN), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 2) + espace, (yMax / 2) + (rayon * 3)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.MAGENTA), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);

        // Ligne 3
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 4) + espace * 2, (yMax / 2)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.BLACK), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 4) + espace * 2, (yMax / 2) + rayon * 2), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.RED), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 4) + espace * 2, (yMax / 2) - rayon * 2), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.BLUE), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);

        // Ligne 2
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 6) + espace * 3, (yMax / 2) + rayon), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.BLUE), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 6) + espace * 3, (yMax / 2) - rayon), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.YELLOW), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
        // Ligne 1
        b = new DecorateurSonCollision(new DecorateurBloqueBord(new DecorateurFrottement(
                new DecorateurCouleur(new BilleConcrete(
                        new Vecteur((((xMax / 2) + xMax / 3) - rayon * 8) + espace * 4, (yMax / 2)), rayon, new Vecteur(0, 0), new Vecteur(0, 0.0001)), Color.YELLOW), 4.5)), (SonLongRobin) sonChocBille, cadre);
        this.billes.add(b);
    }
}

