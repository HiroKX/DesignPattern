package exodecorateur_angryballs.solution.vues.awt.cadre;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import exodecorateur_angryballs.solution.modele.Bille;


import exodecorateur_angryballs.solution.scenario.Scenario;
import exodecorateur_angryballs.solution.vues.VueBillard;
import exodecorateur_angryballs.solution.vues.awt.BillardAWT;
import outilsvues.EcouteurTerminaison;

import outilsvues.Outils;

/**
 * Vue dessinant les billes et contenant les 3 boutons de contrele (arret du programme, lancer les billes, arreter les billes) 
 * 
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 * 
 * */
public class CadreAngryBalls_4_9_2022 extends Frame implements VueBillard
{
TextField presentation;
BillardAWT billard;
public Button lancerBilles, arreterBilles;
Panel haut, centre, bas;

EcouteurTerminaison ecouteurTerminaison;

public CadreAngryBalls_4_9_2022(String titre, String message, Vector<Bille> billes) throws HeadlessException
{
super(titre);
Outils.place(this, 0.33, 0.33, 0.5, 0.5);
this.ecouteurTerminaison = new EcouteurTerminaison(this);


this.haut = new Panel(); this.haut.setBackground(Color.LIGHT_GRAY);
this.add(this.haut,BorderLayout.NORTH);

this.centre = new Panel();
this.add(this.centre,BorderLayout.CENTER);

this.bas = new Panel(); this.bas.setBackground(Color.LIGHT_GRAY);
this.add(this.bas,BorderLayout.SOUTH);

this.presentation = new TextField(message, 100); this.presentation.setEditable(false);
this.haut.add(this.presentation);

this.billard = new BillardAWT(billes);
this.add(this.billard);

this.lancerBilles = new Button("lancer les billes"); this.bas.add(this.lancerBilles);
this.arreterBilles = new Button("arreter les billes"); this.bas.add(this.arreterBilles);

}

public double largeurBillard() 
{
return this.billard.getWidth();
}

public double hauteurBillard()
{
return this.billard.getHeight();
}

@Override
public void miseAJour()
{
this.billard.repaint();
}

/* (non-Javadoc)
 * @see exodecorateur.vues.VueBillard#montrer()
 */
public void changeScenario(Vector<Bille> billes){

}

    @Override
    public void addScenario(Scenario scenario) {

    }

    @Override
    public void addScenarios(ArrayList<Scenario> scenario) {

    }

    @Override
    public void setScenarioCourant(Scenario scenarioCourant) {

    }

    @Override
    public Scenario getScenarioCourant() {
        return null;
    }


    @Override
public void montrer()
{
this.setVisible(true);
}

    @Override
    public void changeScenario(Scenario scenario) {

    }


}