package exodecorateur_angryballs.solution.COR;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.visiteur.DessineParticulariteBille;

public class CORIndex {
    public DessineParticulariteBille visiteur;
    COR cor;
    public CORIndex(DessineParticulariteBille visiteur){
        this.visiteur = visiteur;
        CORLorentz CORLorentez = new CORLorentz(null,visiteur);
        CORAttraper CORAttraper = new CORAttraper(CORLorentez,visiteur);
        CORLance CORLance = new CORLance(CORAttraper,visiteur);
        this.cor = new CORSelectionnable(CORLance,visiteur);
    }

    public void dessiner(Bille b){
        this.cor.resoudre(b);
    }
}
