package exodecorateur_angryballs.solution.vues.awt.cadre;

import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.scenario.Scenario;
import exodecorateur_angryballs.solution.vues.VueBillard;
import exodecorateur_angryballs.solution.vues.awt.BillardAWT;
import exodecorateur_angryballs.solution.vues.awt.panneaux.PanneauChoixBille;
import exodecorateur_angryballs.solution.vues.awt.panneaux.PanneauChoixHurlement;
import exodecorateur_angryballs.solution.vues.awt.panneaux.PanneauChoixScenario;
import musique.SonLong;
import outilsvues.EcouteurTerminaison;
import outilsvues.Outils;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Vector;

/**
 * Vue dessinant les billes et contenant les 3 boutons de contrele (arret du programme, lancer les billes, arreter les billes)
 * et contenant la ligne des boutons de choix des sons pour la bille hurlante
 */
public final class CadreAngryBallsAWT extends Frame implements VueBillard  {
    private static CadreAngryBallsAWT instance = null;
    TextField presentation;
    BillardAWT billard;
    public Button lancerBilles, arreterBilles, reinitialiserBilles;
    Panel haut, centre, bas, ligneBoutonsLancerArret;
    PanneauChoixHurlement ligneBoutonsChoixHurlement;
    PanneauChoixBille ligneBoutonsChoixBilles;
    public PanneauChoixScenario ligneBoutonsChoixScenario;
    EcouteurTerminaison ecouteurTerminaison;
    ArrayList<Scenario> scenarios;
    Scenario scenarioCourant;

    ArrayList<Observer> observeurs;

    public static CadreAngryBallsAWT getInstance(String titre, String message, Vector<Bille> billes, SonLong[] hurlements, int choixHurlementInitial) {
        if (instance == null)
            instance = new CadreAngryBallsAWT(titre, message, billes, hurlements, choixHurlementInitial);
        return instance;
    }

    private CadreAngryBallsAWT(String titre, String message, Vector<Bille> billes, SonLong[] hurlements, int choixHurlementInitial) throws HeadlessException {
        super(titre);
        Outils.place(this, 0.33, 0.33, 0.5, 0.5);
        this.ecouteurTerminaison = new EcouteurTerminaison(this);

        this.scenarios = new ArrayList<>();

        this.observeurs = new ArrayList<>();

        this.setIgnoreRepaint(true);

        this.haut = new Panel();
        this.haut.setBackground(Color.LIGHT_GRAY);
        this.add(this.haut, BorderLayout.NORTH);
        this.centre = new Panel();
        this.add(this.centre, BorderLayout.CENTER);
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                CadreAngryBallsAWT awt = (CadreAngryBallsAWT) e.getSource();
                awt.miseAJour();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                // Ne pas faire de traitement ici
            }

            @Override
            public void componentShown(ComponentEvent e) {
                // Ne pas faire de traitement ici
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                // Ne pas faire de traitement ici
            }
        });

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
        this.lancerBilles.addActionListener((ActionEvent e)-> this.notifyObservers("lancer"));

        this.arreterBilles = new Button("arreter les billes");
        this.ligneBoutonsLancerArret.add(this.arreterBilles);
        this.arreterBilles.addActionListener((ActionEvent e)-> this.notifyObservers("arreter"));

        this.reinitialiserBilles = new Button("relancer les billes");
        this.ligneBoutonsLancerArret.add(this.reinitialiserBilles);
        this.reinitialiserBilles.addActionListener((ActionEvent e)-> this.notifyObservers("reinitialiser"));

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
        for(int i = 0; i < billes.size(); i++)
            this.ligneBoutonsChoixBilles.boutons[i].addItemListener((ItemEvent e)-> this.notifyObservers("bille;"+e.getItem()));
    }

    public void initPanneauScenario(ArrayList<Scenario> scenarios){
        this.ligneBoutonsChoixScenario = new PanneauChoixScenario(scenarios);
        this.bas.add(this.ligneBoutonsChoixScenario);
        for(int i = 0; i < scenarios.size(); i++)
            this.ligneBoutonsChoixScenario.boutons[i].addItemListener((ItemEvent e) -> this.notifyObservers("scenario;" + e.getItem()));
    }

    public void notifyObservers(Object arg) {
        for (Observer o : this.observeurs)
            o.update(null, arg);
    }

    public void addObserver(Observer o) {
        this.observeurs.add(o);
    }

    public double largeurBillard() {
        return this.billard.getWidth();
    }

    public double hauteurBillard() {
        return this.billard.getHeight();
    }

    public void setScenarioCourant(Scenario scenarioCourant) {
        this.scenarioCourant = scenarioCourant;
    }

    @Override
    public Scenario getScenarioCourant() {
        return this.scenarioCourant;
    }

    @Override
    public void miseAJour() {
        this.billard.myRenderingLoop();
    }

    @Override
    public void changeScenario(Scenario scenario) {
        // On enlève la vue du billard
        this.remove(this.billard);
        // On crée un nouveau billard avec les nouvelles billes
        this.billard = new BillardAWT(scenario.getBilles());
        // On ajoute la vue du billard
        this.add(this.billard);
        // On réinitialise le buffer
        this.montrer();
        this.billard.initBuffer();
        // On met à jour le panneau de choix des billes
        this.initPanneauBille(scenario.getBilles());
        for(Bille b : scenario.getBilles()){
            this.addMouseMotionListener(b.getControlleurGeneral());
            this.addMouseListener(b.getControlleurGeneral());
        }
        this.setScenarioCourant(scenario);

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