package exodecorateur_angryballs.solution.scenario;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.BilleConcrete;
import exodecorateur_angryballs.solution.parseurs.ParserCOR;
import exodecorateur_angryballs.solution.vues.VueBillard;
import mesmaths.geometrie.base.Vecteur;

import musique.SonLong;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ScenariosFichier extends Scenario{

    public ScenariosFichier(VueBillard vue,SonLong sonChoc){
        super(vue,sonChoc);
    }

    @Override
    public Vector<Bille> getInitBilles() {
        return null;
    }

    /**
     * Charge les scenarios de billes a partir d'un fichier
     * @param nomFichier nom du fichier contenant la description des scenarios
     * @return la liste des scenarios de billes
     */
    public ArrayList<Scenario> chargerScenarios(String nomFichier) {
        if (nomFichier == null)
            throw new IllegalArgumentException("Le nom du fichier est null.");
        File racine = new File(""); // contient le chemin d'où est lancée la JVM
        File fichier = new File(racine.getAbsoluteFile(), "exodecorateur_angryballs"+ File.separator + "solution" + File.separator + nomFichier);
        if (!fichier.isFile())
            throw new IllegalArgumentException("Le fichier "+ nomFichier +" n'existe pas");

        double vMax = 0.1;
        // Fichier
        BufferedReader reader = null;
        // Ligne courante
        String ligne;
        // Les scénarios lus
        ArrayList<Scenario> scenarios = new ArrayList<>();
        // Les billes d'un scénario
        Vector<Bille> billes = new Vector<>();
        // Le nom du scénario
        String nomScenario = null;
        // Bille courante
        Bille bille;
        // Arguments supplémentaires
        Object args[] = new Object[2];
        args[0] = this.sonChocBille;
        args[1] = this.cadre;
        try {
            reader = new BufferedReader(new FileReader(fichier));
            ligne = reader.readLine();
            // Première ligne du fichier, signifie l'ordre des décorateurs
            String[] decorateurs = ligne.split(";");
            // Les autres lignes sont des billes
            while ((ligne = reader.readLine()) != null) {
               // ligne = ligne+";";
                String[] elements = ligne.split(";");
                System.out.println(Arrays.toString(elements));
                // Si c'est un nouveau scénario
                if(nomScenario == null)
                    nomScenario = elements[0];
                // Si on change de scénario
                else if(!nomScenario.equals(elements[0])) {
                    // On crée un nouveau scénario avec les billes
                    scenarios.add(new ScenarioClassique(nomScenario, billes,sonChocBille));
                    // On réinitialise les billes
                    billes = new Vector<>();
                    // On change le nom du scénario courant
                    nomScenario = elements[0];
                }
                // On traite la ligne qui retourne une bille
                double r = Double.parseDouble(elements[1].trim());
                // On crée la bille
                bille = new BilleConcrete(
                        Vecteur.créationAléatoire(0, 0, cadre.largeurBillard(), cadre.hauteurBillard()),
                        r,
                        Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax),
                        new Vecteur(0, 0.0001));
                // On ajoute les décorateurs
                bille = ParserCOR.Parser(decorateurs, ligne, bille, args);
                // On ajoute la bille au scénario
                billes.add(bille);
            }
            // On ajoute le dernier scénario
            scenarios.add(new ScenarioClassique(nomScenario, billes,sonChocBille));
            // On ferme le fichier
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // On retourne les scénarios
        return scenarios;
    }



}
