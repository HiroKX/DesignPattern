package exodecorateur_angryballs.solution.visiteur;

import exodecorateur_angryballs.solution.modele.Bille;

/** Interface de visiteur de fenêtre pour choisir le type de fenêtre à afficher
 */
public interface WindowVisitor {
    void visit(Bille bille);

}
