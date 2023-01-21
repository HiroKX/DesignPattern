package exodecorateur_angryballs.solution;

import exodecorateur_angryballs.solution.decorateur.*;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.BilleConcrete;
import exodecorateur_angryballs.solution.scenario.*;
import exodecorateur_angryballs.solution.son.SonLongRobin;
import exodecorateur_angryballs.solution.vues.awt.boutons.ecouteurs.*;
import exodecorateur_angryballs.solution.vues.awt.cadre.CadreAngryBallsAWT;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
  Création des éléments pour les tests
 */
public class TestAngryBalls {


    public static void main(String[] args) {
//---------------------- gestion des bruitages : parametrage du chemin du dossier contenant les fichiers audio --------------------------

        File file = new File(""); // le oe la JVM est lancee : racine du projet

        File repertoireSon = new File(file.getAbsoluteFile(),
                "exodecorateur_angryballs" + File.separatorChar +
                        "maladroit" + File.separatorChar + "bruits");

//-------------------- chargement des sons pour les hurlements --------------------------------------

        Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(repertoireSon, "config_audio_bille_hurlante.txt");
        SonLong[] son = new SonLong[2];
        try {
            son[0] = SonLongRobin.crée(repertoireSon, "huey2 3000 3100 10");
            son[1] = SonLongRobin.crée(repertoireSon, "collision_bille_bille 0 2000 200");
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
        SonLong[] hurlements = SonLong.toTableau(sonsLongs);                // on obtient un tableau de SonLong
//---------------- creation de la vue responsable du dessin des billes -------------------------

        int choixHurlementInitial = 0;
        CadreAngryBallsAWT cadre = CadreAngryBallsAWT.getInstance("Angry balls",
                "Animation de billes ayant des comportements differents. Situation ideale pour mettre en place le DP Decorator",
                new Vector<>(), hurlements, choixHurlementInitial);

        cadre.montrer(); // on rend visible la vue

//------------------- creation des scenarios ---------------------------------------------

        ScenariosFichier scenariosFichier = new ScenariosFichier(cadre,son);
        //ArrayList<Scenario> lireScenario = scenariosFichier.chargerScenarios("Scenarios.csv");
        ArrayList<Scenario> lireScenario = scenariosFichier.chargerScenarios("S1.csv");
        Scenario defaultScenario = lireScenario.get(0);
        Scenario billard = new ScenarioBillard(cadre, son[1]);
        ScenarioClassique test = new ScenarioClassique("Test Base",son[0]);
         test.addBille(new DecorateurSelectionnable(new DecorateurRebond(new BilleConcrete(new Vecteur(200,200),10, new Vecteur(0.1,0.1)))));
         //test.addBille(new DecorateurCouleur(new DecorateurFrottement(new DecorateurPesanteur(new DecorateurRebond(new BilleConcrete(new Vecteur(300,300),40, new Vecteur(0.1,0.1))),new Vecteur(0,0.0001)),0.1),Color.yellow));
         //test.addBille(new DecorateurCouleur(new DecorateurFrottement(new DecorateurNewton(new DecorateurRebond(new BilleConcrete(new Vecteur(400,400),30, new Vecteur(0.1,0.1)))),0.1),Color.green));
         test.addBille(new DecorateurPasseMuraille(new DecorateurBloqueBord(new DecorateurCouleur(new BilleConcrete(new Vecteur(500,400),30, new Vecteur(0.1,0.1)),Color.cyan))));
        Bille hurle = (new DecorateurCouleur(
                new DecorateurBloqueBord(
                        new DecorateurNewton(
                                new DecorateurFrottement(
                                        new DecorateurHurlement(
                                                new BilleConcrete(
                                                        new Vecteur(800,400),30, new Vecteur(0.1,0.1))
                                                ,son[0],cadre),0.1))),Color.BLACK));
         //test.addBille(hurle);
         int rgbtangerine = 0xFEB06A;
         test.addBille(new DecorateurRebond(new DecorateurFrottement(new DecorateurCouleurInt(new DecorateurAttraper(new BilleConcrete(new Vecteur( 100,100),50,new Vecteur(0.1,-0.3))),rgbtangerine),0.1)));

        //Bille hurle = new DecorateurAttraper(new DecorateurCouleur(new DecorateurRebond(new BilleConcrete(new Vecteur(00,400),50, new Vecteur(0.1,0.1))),Color.BLUE));
         //test.addBille(hurle);

        Scenario nbBille = new ScenarioNB(cadre,son[0],100);
          cadre.addScenarios(lireScenario);
          cadre.addScenario(test);
          cadre.addScenario(billard);
          cadre.addScenario(nbBille);

//---------------------- ici finit la partie e changer -------------------------------------------------------------

        System.out.println("billesNombres = " + defaultScenario.getBilles().size());
        System.out.println("billes = " + defaultScenario.getBilles());
        cadre.initPanneauScenario(cadre.getScenarios());

//-------------------- creation de l'objet responsable de l'animation (c'est un thread separe) -----------------------

        AnimationBilles animationBilles = new AnimationBilles(cadre);
        animationBilles.setBilles(defaultScenario);

//------------------------- activation des ecouteurs des boutons et ea tourne tout seul ------------------------------
        cadre.addObserver(new EcouteurBoutonLancer(animationBilles));
        cadre.addObserver(new EcouteurBoutonArreter(animationBilles));
        cadre.addObserver(new EcouteurBoutonReinitialiser(animationBilles));
        for (int i = 0; i < cadre.getScenarios().size(); i++)
            cadre.addObserver(new EcouteurChoixScenario(cadre.getScenarios().get(i), animationBilles));
        for(int i = 0; i < cadre.getHurlements().length; i++)
            cadre.addObserver(new EcouteurChoixHurlement(cadre.getHurlements()[i], animationBilles));

        cadre.revalidate();
        cadre.repaint();
    }

}
