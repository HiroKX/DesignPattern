package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.Event.ControlleurGeneral;
import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;


/*
    Ce décorateur permet de contrôler le mouvement d'un bille après avoir été sélectionnée
 */
public class DecorateurAttraper extends DecorateurBille{

    Vecteur influence;

    ControlleurGeneral controlleurGeneral;

    boolean attraper;

    public DecorateurAttraper(Bille b){
        super(b);
        this.influence = Vecteur.VECTEURNUL;
        this.controlleurGeneral = new ControlleurGeneral(this);
    }

    @Override
    public Vecteur gestionAcceleration(Vector<Bille> billes){
        super.gestionAcceleration(billes);
        return this.getAcceleration();
    }

    public ControlleurGeneral getControlleurGeneral(){
        return this.controlleurGeneral;
    }

    public void attrape(){
        this.attraper = true;
    }

    public boolean isAttraper(){
        return attraper;
    }

    public void relacher(){
        this.attraper=false;
    }
}
