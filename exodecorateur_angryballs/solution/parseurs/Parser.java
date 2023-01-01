package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.modele.Bille;

/**
    Le parser sert au programme pour lire les informations du fichier Scenarios.csv. Il transforme des strings en action que nous voulons faire
 */
public abstract class Parser {
    /**
     * Parse une ligne
     * @param ligne la ligne à parser
     * @param args les arguments
     * @throws Exception si quelque chose a mal tourné
     */
    public abstract Object parser(String ligne, Bille bille, Object[] args) throws Exception;

}
