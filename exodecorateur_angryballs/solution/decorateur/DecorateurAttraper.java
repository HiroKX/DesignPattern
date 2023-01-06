package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.Event.ControlleurAttraper;
import exodecorateur_angryballs.solution.Event.ControlleurType;
import exodecorateur_angryballs.solution.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;


/**
    Ce décorateur permet de contrôler le mouvement d'une bille après avoir été sélectionnée
 */
public class DecorateurAttraper extends DecorateurBille{

    /**
        Vecteur d'influence
     */
    Vecteur influence;

    /**
        Controlleur General
     */
    ControlleurAttraper controlleurGeneral;

    /**
        Si la bille est attrapée
     */
    boolean attraper;

    public DecorateurAttraper(Bille b){
        super(b);
        this.influence = Vecteur.VECTEURNUL;
        this.controlleurGeneral = new ControlleurAttraper(this);
    }

    @Override
    public Vecteur gestionAcceleration(Vector<Bille> billes){
        super.gestionAcceleration(billes);
        return this.getAcceleration();
    }

    public ControlleurType getControlleur(){
        return this.controlleurGeneral;
    }
    
}
