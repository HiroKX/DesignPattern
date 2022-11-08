package exodecorateur_angryballs.maladroit.state;

import exodecorateur_angryballs.maladroit.modele.Bille;

public abstract class State {

    protected Bille b;

    public State(Bille b){
        this.b=b;
    }

    public abstract void deplacer(double delta);

}
