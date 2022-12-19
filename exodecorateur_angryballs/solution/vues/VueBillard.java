package exodecorateur_angryballs.solution.vues;


import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.scenario.Scenario;

import java.util.ArrayList;
import java.util.Vector;


/**
 * contrat respecte par toute vue capable de dessiner la liste des billes
 * 
 * Comme ea si vous n'aimez pas mes composants awt vous pouvez les changer sans changer le reste de l'appli
 * 
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 * 
 * */
public interface VueBillard
{

public double largeurBillard();

public double hauteurBillard();

public void miseAJour();

public void montrer();

public void changeScenario(Vector<Bille> billes);
public void addScenario(Scenario scenario);
public void addScenarios(ArrayList<Scenario> scenario);

}
