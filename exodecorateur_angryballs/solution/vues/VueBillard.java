package exodecorateur_angryballs.solution.vues;

import exodecorateur_angryballs.solution.scenario.Scenario;
import java.util.ArrayList;

/**
 * contrat respecte par toute vue capable de dessiner la liste des billes
 * Comme ça si vous n'aimez pas mes composants awt vous pouvez les changer sans changer le reste de l'appli
 * */
public interface VueBillard{

public double largeurBillard();

public double hauteurBillard();

public void miseAJour();

public void montrer();

public void changeScenario(Scenario scenario);
public void addScenario(Scenario scenario);
public void addScenarios(ArrayList<Scenario> scenario);
public void setScenarioCourant(Scenario scenarioCourant);
public Scenario getScenarioCourant();
}
