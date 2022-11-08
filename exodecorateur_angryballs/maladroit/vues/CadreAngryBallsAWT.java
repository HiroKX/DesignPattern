package exodecorateur_angryballs.maladroit.vues;

import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.modele.Bille;
import musique.SonLong;
import outilsvues.EcouteurTerminaison;

import outilsvues.Outils;

/**
 * Vue dessinant les billes et contenant les 3 boutons de contrele (arret du programme, lancer les billes, arreter les billes)
 * et contenant la ligne des boutons de choix des sons pour la bille hurlante
 * <p>
 * ICI : IL N'Y A RIEN A CHANGER
 */
public class CadreAngryBallsAWT extends Frame implements VueBillard {
    TextField presentation;
    BillardAWT billard;
    public Button lancerBilles, arreterBilles;
    Panel haut, centre, bas, ligneBoutonsLancerArret;
    PanneauChoixHurlement ligneBoutonsChoixHurlement;

    EcouteurTerminaison ecouteurTerminaison;

    public CadreAngryBallsAWT(String titre, String message, Vector<Bille> billes, SonLong[] hurlements, int choixHurlementInitial) throws HeadlessException {
        super(titre);
        Outils.place(this, 0.33, 0.33, 0.5, 0.5);
        this.ecouteurTerminaison = new EcouteurTerminaison(this);


        this.haut = new Panel();
        this.haut.setBackground(Color.LIGHT_GRAY);
        this.add(this.haut, BorderLayout.NORTH);

        this.centre = new Panel();
        this.add(this.centre, BorderLayout.CENTER);

        this.bas = new Panel();
        this.bas.setBackground(Color.LIGHT_GRAY);
        this.add(this.bas, BorderLayout.SOUTH);

        this.presentation = new TextField(message, 100);
        this.presentation.setEditable(false);
        this.haut.add(this.presentation);

        this.billard = new BillardAWT(billes);
        this.add(this.billard);

//------------------- placement des composants du bas du cadre -------------------------------

        int nombreLignes = 2, nombreColonnes = 1;

        this.bas.setLayout(new GridLayout(nombreLignes, nombreColonnes));

//---------------- placement des boutons lancer - arreter ------------------------------------

        this.ligneBoutonsLancerArret = new Panel();
        this.bas.add(this.ligneBoutonsLancerArret);


        this.lancerBilles = new Button("lancer les billes");
        this.ligneBoutonsLancerArret.add(this.lancerBilles);
        this.arreterBilles = new Button("arreter les billes");
        this.ligneBoutonsLancerArret.add(this.arreterBilles);

//---------------- placement de la ligne de boutons de choix des sons pour le hurlement ------

        this.ligneBoutonsChoixHurlement = new PanneauChoixHurlement(hurlements, choixHurlementInitial);
        this.bas.add(this.ligneBoutonsChoixHurlement);

    }

    public double largeurBillard() {
        return this.billard.getWidth();
    }

    public double hauteurBillard() {
        return this.billard.getHeight();
    }

    @Override
    public void miseAJour() {
        this.billard.repaint();
    }

    /* (non-Javadoc)
     * @see exodecorateur.vues.VueBillard#montrer()
     */
    @Override
    public void montrer() {
        this.setVisible(true);
    }

    public void addChoixHurlementListener(ItemListener ecouteurChoixHurlant) {
        int i;

        for (i = 0; i < this.ligneBoutonsChoixHurlement.boutons.length; ++i)
            this.ligneBoutonsChoixHurlement.boutons[i].addItemListener(ecouteurChoixHurlant);

    }

    public void addMouseListener(MouseListener mouseListener) {
        this.billard.addMouseListener(mouseListener);
    }

    public void addMouseMotionListener(MouseMotionListener mouseMotionListener){
        this.billard.addMouseMotionListener(mouseMotionListener);
    }

}