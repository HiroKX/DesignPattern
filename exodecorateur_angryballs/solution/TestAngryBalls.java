package exodecorateur_angryballs.solution;

import exodecorateur_angryballs.solution.scenario.Scenario;
import exodecorateur_angryballs.solution.scenario.ScenarioBillard;
import exodecorateur_angryballs.solution.scenario.ScenarioNB;
import exodecorateur_angryballs.solution.scenario.ScenariosFichier;
import exodecorateur_angryballs.solution.son.SonLongRobin;
import exodecorateur_angryballs.solution.vues.awt.boutons.ecouteurs.*;
import exodecorateur_angryballs.solution.vues.awt.cadre.CadreAngryBallsAWT;
import musique.SonLong;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Gestion d'une liste de billes en mouvement ayant toutes un comportement different
 * <p>
 * Ideal pour mettre en place le DP decorator
 */
public class TestAngryBalls {

    /**
     * @param args
     */
    public static void main(String[] args) {
//---------------------- gestion des bruitages : parametrage du chemin du dossier contenant les fichiers audio --------------------------

        File file = new File(""); // le oe la JVM est lancee : racine du projet

        File repertoireSon = new File(file.getAbsoluteFile(),
                "exodecorateur_angryballs" + File.separatorChar +
                        "maladroit" + File.separatorChar + "bruits");

//-------------------- chargement des sons pour les hurlements --------------------------------------

        Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(repertoireSon, "config_audio_bille_hurlante.txt");
        SonLong sonBilleChoc;
        try {
            sonBilleChoc = SonLongRobin.crée(repertoireSon, "collision_bille_bille 0 2000 200");
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

         ScenariosFichier scenariosFichier = new ScenariosFichier(cadre,sonBilleChoc);
         ArrayList<Scenario> lireScenario = scenariosFichier.chargerScenarios("Scenarios.csv");
         Scenario defaultScenario = lireScenario.get(0);
         Scenario billard = new ScenarioBillard(cadre,sonBilleChoc);
         Scenario nbBille = new ScenarioNB(cadre,sonBilleChoc,1000);
          cadre.addScenarios(lireScenario);
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

        cadre.revalidate();
        cadre.repaint();
    }

}
