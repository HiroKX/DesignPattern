package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.Event.ControlleurLance;
import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class DecorateurLance extends DecorateurBille{
    /**
     Vecteur d'influence
     */
    Vecteur influence;

    /**
     Controlleur General
     */
    ControlleurLance controlleurGeneral;


    /**
     Si la bille est attrapée
     */

    public DecorateurLance(Bille b) {
        super(b);
        this.influence = Vecteur.VECTEURNUL;
        this.controlleurGeneral = new ControlleurLance(this);
    }


    @Override
    public Vecteur gestionAcceleration(Vector<Bille> billes){
        super.gestionAcceleration(billes);
        return this.getAcceleration();
    }

    public ControlleurLance getControlleur(){
        return this.controlleurGeneral;
    }


}
