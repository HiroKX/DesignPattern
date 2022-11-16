package exodecorateur_angryballs.maladroit.decorateur;

import exodecorateur_angryballs.maladroit.Event.ControlleurGeneral;
import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

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
        //this.getAcceleration().ajoute(this.influence);
        return this.getAcceleration();
    }

    public ControlleurGeneral getControlleurGeneral(){

        return this.controlleurGeneral;
    }

    public void attrape(){
        this.attraper = true;
        this.setCouleur(-32000);
    }

    public boolean isAttraper(){
        return attraper;
    }

    public void relacher(){
        this.attraper=false;
        this.setCouleur(-255);
    }
}
