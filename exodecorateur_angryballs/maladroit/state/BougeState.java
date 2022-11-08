package exodecorateur_angryballs.maladroit.state;

import exodecorateur_angryballs.maladroit.modele.Bille;

public class BougeState extends State{
    public BougeState(Bille b) {
        super(b);
    }

    @Override
    public void deplacer(double delta) {
        this.b.deplacer(delta);
    }
}
