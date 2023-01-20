package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.Event.ControlleurBilleClavier;
import exodecorateur_angryballs.solution.Event.ControlleurType;
import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

/**
    Ce decorateur permet à la bille d'être selectionnee. Ce decorateur est surtout utile si le decorateur Attraper est actif aussi
 */
public class DecorateurSelectionnable extends DecorateurBille{

    /**
     Vecteur d'influence
     */
    Vecteur influence;

    /**
     Controlleur General
     */
    ControlleurBilleClavier controlleurGeneral;

    public DecorateurSelectionnable(Bille b){
        super(b);
        this.influence = Vecteur.VECTEURNUL;
        this.controlleurGeneral = new ControlleurBilleClavier(this);
    }

    @Override
    public Vecteur gestionAcceleration(Vector<Bille> billes){
        super.gestionAcceleration(billes);
        return this.getAcceleration();
    }

    @Override
    public boolean isTenue(){
        return this.controlleurGeneral.getTenue();
    }

    public ControlleurType getControlleur(){
        return this.controlleurGeneral;
    }

}
