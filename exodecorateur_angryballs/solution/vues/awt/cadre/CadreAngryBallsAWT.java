package exodecorateur_angryballs.solution.vues.awt.cadre;

import exodecorateur_angryballs.solution.Event.ControlleurBilleClavier;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.scenario.Scenario;
import exodecorateur_angryballs.solution.vues.VueBillard;
import exodecorateur_angryballs.solution.vues.awt.BillardAWT;
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
    /** Le billard, avec le DP Singleton */
    private static CadreAngryBallsAWT instance = null;
    /** Titre de la fenetre */
    TextField presentation;
    /** Le billard */
    BillardAWT billard;
    /** Les boutons */
    public Button lancerBilles, arreterBilles, reinitialiserBilles;
    /** Les panneaux */
    Panel haut, centre, bas, ligneBoutonsLancerArret;
    /** Panneau de choix des hurlements */
    PanneauChoixHurlement ligneBoutonsChoixHurlement;
    /** Panneau de choix des scenarios */
    public PanneauChoixScenario ligneBoutonsChoixScenario;
    /** Ecouteur de terminaison */
    EcouteurTerminaison ecouteurTerminaison;
    /** Hurlements */
    SonLong[] hurlements;
    /** Choix du hurlement */
    SonLong hurlementChoisi;
    /** Les scenarios */
    ArrayList<Scenario> scenarios;
    /** Le scenario courant */
    Scenario scenarioCourant;
    /** Implémentation du DP Observable/Observeur, ici la classe est Observable et ce sont ses observeurs */
    ArrayList<Observer> observeurs;

    /**
     * Constructeur avec le DP Singleton
     * @param titre Titre de la fenetre
     * @param message Message de presentation
     * @param billes Les billes
     * @throws HeadlessException Exception
     */
    public static CadreAngryBallsAWT getInstance(String titre, String message, Vector<Bille> billes, SonLong[] hurlements, int choixHurlementInitial) {
        if (instance == null)
            instance = new CadreAngryBallsAWT(titre, message, billes, hurlements, choixHurlementInitial);
        return instance;
    }

    /**
     * Constructeur
     * @param titre Titre de la fenetre
     * @param message Message de presentation
     * @param billes Les billes
     * @throws HeadlessException Exception
     */
    private CadreAngryBallsAWT(String titre, String message, Vector<Bille> billes, SonLong[] hurlements, int choixHurlementInitial) throws HeadlessException {
        super(titre);
        Outils.place(this, 0.33, 0.33, 0.5, 0.5);
        this.ecouteurTerminaison = new EcouteurTerminaison(this);

        this.scenarios = new ArrayList<>();

        this.observeurs = new ArrayList<>();

        this.hurlements = hurlements;
        this.hurlementChoisi = hurlements[choixHurlementInitial];

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

        int nombreLignes = 3, nombreColonnes = 1;

        this.bas.setLayout(new GridLayout(nombreLignes, nombreColonnes));

//---------------- placement des boutons lancer - arreter - reinitialiser ------------------------------------

    // Panneau des boutons
        this.ligneBoutonsLancerArret = new Panel();
        this.bas.add(this.ligneBoutonsLancerArret);
    // Bouton lancer
        this.lancerBilles = new Button("lancer les billes");
        this.ligneBoutonsLancerArret.add(this.lancerBilles);
        this.lancerBilles.addActionListener((ActionEvent e)-> this.notifyObservers("lancer"));
    // Bouton arreter
        this.arreterBilles = new Button("arreter les billes");
        this.ligneBoutonsLancerArret.add(this.arreterBilles);
        this.arreterBilles.addActionListener((ActionEvent e)-> this.notifyObservers("arreter"));
    // Bouton reinitialiser
        this.reinitialiserBilles = new Button("relancer les billes");
        this.ligneBoutonsLancerArret.add(this.reinitialiserBilles);
        this.reinitialiserBilles.addActionListener((ActionEvent e)-> this.notifyObservers("reinitialiser"));

//---------------- placement de la ligne de boutons de choix des sons pour le hurlement ------

        this.ligneBoutonsChoixHurlement = new PanneauChoixHurlement(this.hurlements, choixHurlementInitial);
        this.bas.add(this.ligneBoutonsChoixHurlement);
        for(int i = 0; i < this.hurlements.length; i++)
            this.ligneBoutonsChoixHurlement.boutons[i].addItemListener((ItemEvent e)-> this.notifyObservers("hurlement;" + e.getItem()));

//---------------- placement du billard -----------------------------------------------------

        this.billard = new BillardAWT(billes);
        this.add(this.billard);
        this.montrer();
        this.billard.initBuffer();
    }

    /**
     *  Initialise le panneau des scenarios
     * @param scenarios Les scenarios
     */
    public void initPanneauScenario(ArrayList<Scenario> scenarios){
        this.ligneBoutonsChoixScenario = new PanneauChoixScenario(scenarios);
        this.bas.add(this.ligneBoutonsChoixScenario);
        for(int i = 0; i < scenarios.size(); i++)
            this.ligneBoutonsChoixScenario.boutons[i].addItemListener((ItemEvent e) -> this.notifyObservers("scenario;" + e.getItem()));
    }

    /**
     * Notifie-les observeurs quand un evenement survient
     * @param arg L'evenement
     */
    public void notifyObservers(Object arg) {
        for (Observer o : this.observeurs)
            o.update(null, arg);
    }

    /**
     * Ajoute un observeur
     * @param o L'observeur
     */
    public void addObserver(Observer o) {
        this.observeurs.add(o);
    }

    public double largeurBillard() {
        return this.billard.getWidth();
    }

    public double hauteurBillard() {
        return this.billard.getHeight();
    }

    /**
     *
     * @param scenarioCourant Le scenario courant
     */
    public void setScenarioCourant(Scenario scenarioCourant) {
        this.scenarioCourant = scenarioCourant;
        this.billard.setKeyListner(new ControlleurBilleClavier(this.scenarioCourant));
    }

    @Override
    public Scenario getScenarioCourant() {
        return this.scenarioCourant;
    }

    @Override
    public void miseAJour() {
        this.billard.myRenderingLoop();
    }

    /**
     * Change le scenario courant
     * @param scenario Le nouveau scenario
     */
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

    public void addMouseListener(MouseListener mouseListener) {
        this.billard.addMouseListener(mouseListener);
    }

    public void addMouseMotionListener(MouseMotionListener mouseMotionListener){
        this.billard.addMouseMotionListener(mouseMotionListener);
    }

    public SonLong[] getHurlements() {
        return this.hurlements;
    }

    public void setHurlement(SonLong hurlement) {
        this.hurlementChoisi = hurlement;
    }

    @Override
    public SonLong getHurlement() {
        return this.hurlementChoisi;
    }
}