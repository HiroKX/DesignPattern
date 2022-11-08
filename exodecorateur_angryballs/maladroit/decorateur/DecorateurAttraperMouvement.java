package exodecorateur_angryballs.maladroit.decorateur;

import exodecorateur_angryballs.maladroit.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

public class DecorateurAttraperMouvement extends DecorateurBille{

    boolean attraper;

    public DecorateurAttraperMouvement(Bille b){
        super(b);
        this.attraper = false;
    }

    @Override
    public void deplacer(double deltaT){
        //System.out.println(this.attraper);
        if(this.attraper){

            this.bille.deplacer(deltaT);
            //System.out.println("pas bouger");
        }else{
            this.bille.deplacer(deltaT);
        }
    }

    @Override
    public void relacher(Vecteur acc){
        //this.getAcceleration().set(acc);
        this.attraper = false;
    }

    @Override
    public void attraper(){
        System.out.println("aaaa");
        this.attraper = true;

    }
}