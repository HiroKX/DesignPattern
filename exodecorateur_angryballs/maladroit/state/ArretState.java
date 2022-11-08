package exodecorateur_angryballs.maladroit.state;

import exodecorateur_angryballs.maladroit.modele.Bille;

public class ArretState extends State{
    public ArretState(Bille b) {
        super(b);
    }

    @Override
    public void deplacer(double delta) {
    }
}
