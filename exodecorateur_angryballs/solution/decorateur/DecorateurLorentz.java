package exodecorateur_angryballs.solution.decorateur;

import exodecorateur_angryballs.solution.RobinLagler;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.modele.OutilsBille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class DecorateurLorentz extends DecorateurBille{


    public Vecteur v = new Vecteur(0,0);
    public DecorateurLorentz(Bille b) {
        super(b);
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        boolean temp = OutilsBille.gestionCollisionBilleBille(this, billes);
        if(temp){
            double a = RobinLagler.A;

        }else{
            this.v.produitScalaire(this.getVitesse());
        }

        return temp;
    }


}

