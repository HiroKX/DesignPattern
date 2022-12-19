package exodecorateur_angryballs.solution.vues;

import exodecorateur_angryballs.solution.scenario.Scenario;
import exodecorateur_angryballs.solution.modele.Bille;
import musique.SonLong;
import outilsvues.EcouteurTerminaison;
import outilsvues.Outils;

import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Vector;

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
    PanneauChoixBille ligneBoutonsChoixBilles;
    public PanneauChoixScenario ligneBoutonsChoixScenario;
    EcouteurTerminaison ecouteurTerminaison;

    ArrayList<Scenario> scenarios;

    public CadreAngryBallsAWT(String titre, String message, Vector<Bille> billes, SonLong[] hurlements, int choixHurlementInitial) throws HeadlessException {
        super(titre);
        Outils.place(this, 0.33, 0.33, 0.5, 0.5);
        this.ecouteurTerminaison = new EcouteurTerminaison(this);

        this.scenarios = new ArrayList<>();

        this.setIgnoreRepaint(true);

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

//------------------- placement des composants du bas du cadre -------------------------------

        int nombreLignes = 4, nombreColonnes = 1;

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

//---------------- placement du billard -----------------------------------------------------
        this.billard = new BillardAWT(billes);
        this.add(this.billard);
        this.montrer();
        this.billard.initBuffer();
    }
    public void initPanneauBille(Vector<Bille> billes) {
        try {
            this.bas.remove(this.ligneBoutonsChoixBilles);
        } catch (NullPointerException ignored) {}
        this.ligneBoutonsChoixBilles = new PanneauChoixBille(billes);
        this.bas.add(this.ligneBoutonsChoixBilles);
    }

    public void initPanneauScenario(ArrayList<Scenario> scenarios){
        this.ligneBoutonsChoixScenario = new PanneauChoixScenario(scenarios);
        this.bas.add(this.ligneBoutonsChoixScenario);
    }

    public double largeurBillard() {
        return this.billard.getWidth();
    }

    public double hauteurBillard() {
        return this.billard.getHeight();
    }

    @Override
    public void miseAJour() {
        this.billard.myRenderingLoop();
    }

    @Override
    public void changeScenario(Vector<Bille> billes){
        // On enlève la vue du billard
        this.remove(this.billard);
        // On crée un nouveau billard avec les nouvelles billes
        this.billard = new BillardAWT(billes);
        // On ajoute la vue du billard
        this.add(this.billard);
        // On réinitialise le buffer
        this.montrer();
        this.billard.initBuffer();
        // On met à jour le panneau de choix des billes
        this.initPanneauBille(billes);
        for(Bille b : billes){
            this.addMouseMotionListener(b.getControlleurGeneral());
            this.addMouseListener(b.getControlleurGeneral());
        }

        // On met à jour la vue
        this.revalidate();
        this.repaint();
    }

    @Override
    public void addScenario(Scenario scenario) {
        this.scenarios.add(scenario);
    }

    @Override
    public void addScenarios(ArrayList<Scenario> scenario) {
        this.scenarios.addAll(scenario);
    }

    public ArrayList<Scenario> getScenarios() {
        return this.scenarios;
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

    public void addChoixBilleListener(ItemListener ecouteurChoixBille) {
        for (int i = 0; i < this.ligneBoutonsChoixBilles.boutons.length; ++i)
            this.ligneBoutonsChoixBilles.boutons[i].addItemListener(ecouteurChoixBille);
    }

    public void addMouseListener(MouseListener mouseListener) {
        this.billard.addMouseListener(mouseListener);
    }

    public void addMouseMotionListener(MouseMotionListener mouseMotionListener){
        this.billard.addMouseMotionListener(mouseMotionListener);
    }
}