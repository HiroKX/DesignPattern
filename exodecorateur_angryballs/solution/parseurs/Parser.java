package exodecorateur_angryballs.solution.parseurs;

import exodecorateur_angryballs.solution.modele.Bille;

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
