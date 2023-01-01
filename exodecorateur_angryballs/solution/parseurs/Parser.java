package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.modele.Bille;

/*
    Le parser sert au programme pour lire les informations du fichier Scenarios.csv. Il transforme des strings en action que nous voulons faire
 */
public abstract class Parser {
    /**
     * Parse une ligne. Renvoie une Exception si quelque chose a mal tourné...
     *
     * @param ligne
     * @param args
     * @throws Exception
     */
    public abstract Object parser(String ligne, Bille bille, Object[] args) throws Exception;

}
