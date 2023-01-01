package exodecorateur_angryballs.solution.vues.awt.panneaux;

import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.Panel;


import exodecorateur_angryballs.maladroit.vues.BoutonChoixHurlement;
import musique.SonLong;

/**
 * Represente la ligne des cases à cocher pour le choix du son pour la bille hurlante.
 * <p>
 * Ligne du bas du cadre contenant les billes
 */
public class PanneauChoixHurlement extends Panel {
    public exodecorateur_angryballs.maladroit.vues.BoutonChoixHurlement boutons[];
    CheckboxGroup checkboxGroup;

    /**
     * @param hurlements            : tous les sons disponibles pour la bille hurlante
     * @param choixHurlementInitial : hurlement choisi par defaut e l'initialisation de l'application.
     *                              choixHurlementInitial doit representer un indice valide du tableau hurlements
     */
    public PanneauChoixHurlement(SonLong[] hurlements, int choixHurlementInitial) {
        this.boutons = new exodecorateur_angryballs.maladroit.vues.BoutonChoixHurlement[hurlements.length];
        this.checkboxGroup = new CheckboxGroup();
        this.setLayout(new GridLayout(1, this.boutons.length));
        int i;
        for (i = 0; i < this.boutons.length; ++i) {
            this.boutons[i] = new BoutonChoixHurlement(checkboxGroup, false, hurlements[i]);
            this.add(this.boutons[i]);
        }

        this.boutons[choixHurlementInitial].setState(true);
    }

}