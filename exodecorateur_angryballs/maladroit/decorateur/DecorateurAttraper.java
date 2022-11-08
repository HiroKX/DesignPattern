package exodecorateur_angryballs.maladroit.decorateur;

import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.state.ArretState;
import exodecorateur_angryballs.maladroit.state.BougeState;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class DecorateurAttraper extends DecorateurBille{

    Vecteur influence;

    public DecorateurAttraper(Bille b){
        super(b);
        this.influence = Vecteur.VECTEURNUL;
    }

    @Override
    public Vecteur gestionAcceleration(Vector<Bille> billes){
        this.getAcceleration().ajoute(this.bille.gestionAcceleration(billes));
        this.getAcceleration().ajoute(this.influence);
        return this.getAcceleration();
    }


    @Override
    public void relacher(Vecteur acc){
        this.setState( new BougeState(this.bille));
        this.influence = acc;
        //this.attraper = false;
    }

    @Override
    public void attraper(){
        this.setState( new ArretState(this.bille));
        this.influence = Vecteur.VECTEURNUL;
    }
}
