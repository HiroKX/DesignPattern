package exodecorateur_angryballs.solution.visiteur;

import exodecorateur_angryballs.solution.decorateur.*;

public interface DessineParticulariteBille {
    void visit(DecorateurSelectionnable b);
    void visit(DecorateurLance b);
    void visit(DecorateurAttraper b);
    void visit(DecorateurBille b);

}
