package exodecorateur_angryballs.maladroit;

import java.awt.Color;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.modele.BilleConcrete;
import exodecorateur_angryballs.maladroit.decorateur.*;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;
import exodecorateur_angryballs.maladroit.modele.Bille;
import exodecorateur_angryballs.maladroit.vues.CadreAngryBallsAWT;


/**
 * Gestion d'une liste de billes en mouvement ayant toutes un comportement different
 * <p>
 * Ideal pour mettre en place le DP decorator
 */
public class TestAngryBalls {

    /**
     * @param args
     */
    public static void main(String[] args) {
//---------------------- gestion des bruitages : parametrage du chemin du dossier contenant les fichiers audio --------------------------

        File file = new File(""); // le oe la JVM est lancee : racine du projet

        File repertoireSon = new File(file.getAbsoluteFile(),
                "exodecorateur_angryballs" + File.separatorChar +
                        "maladroit" + File.separatorChar + "bruits");

//-------------------- chargement des sons pour les hurlements --------------------------------------

        Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(repertoireSon, "config_audio_bille_hurlante.txt");

        SonLong[] hurlements = SonLong.toTableau(sonsLongs);                // on obtient un tableau de SonLong

//------------------- creation de la liste (pour l'instant vide) des billes -----------------------

        Vector<Bille> billes = new Vector<Bille>();

//---------------- creation de la vue responsable du dessin des billes -------------------------

        int choixHurlementInitial = 0;
        CadreAngryBallsAWT cadre = new CadreAngryBallsAWT("Angry balls",
                "Animation de billes ayant des comportements differents. Situation ideale pour mettre en place le DP Decorator",
                billes, hurlements, choixHurlementInitial);

        cadre.montrer(); // on rend visible la vue

//------------- remplissage de la liste avec 5 billes -------------------------------


        double xMax, yMax;
        double vMax = 0.1;
        xMax = cadre.largeurBillard();      // abscisse maximal
        yMax = cadre.hauteurBillard();      // ordonnee maximale

        double rayon = 0.05 * Math.min(xMax, yMax); // rayon des billes : ici toutes les billes ont le meme rayon, mais ce n'est pas obligatoire

        Vecteur p0, p1, p2, p3, p4, v0, v1, v2, v3, v4;    // les positions des centres des billes et les vecteurs vitesse au demarrage.
        // Elles vont etre choisies aleatoirement

//------------------- creation des vecteurs position des billes ---------------------------------

        p0 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p1 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p2 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p3 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
        p4 = Vecteur.créationAléatoire(0, 0, xMax, yMax);

//------------------- creation des vecteurs vitesse des billes ---------------------------------

        v0 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v1 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, 0);
        v2 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v3 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
        v4 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);

//--------------- ici commence la partie e changer ---------------------------------
        DecorateurAttraper b = new DecorateurAttraper(new DecorateurBloqueBord(new DecorateurPesanteur(new DecorateurFrottement(new DecorateurCouleur(new BilleConcrete(p1, rayon*2, v1, new Vecteur(0,0.0001)),Color.YELLOW),4.5),new Vecteur(0,0.001))));
        billes.add(b);
        Bille b2 =  new DecorateurSonMur(new DecorateurAttraper(new DecorateurBloqueBord(new DecorateurFrottement(new DecorateurCouleur(new BilleConcrete(p0, rayon, v0, new Vecteur(0,0.0001)),Color.RED),4.5))), hurlements[choixHurlementInitial], cadre);

        billes.add(b2);        //billes.add(new DecorateurCouleur(new DecorateurRebond(new BilleConcrete(p0, rayon, v0)), Color.RED));

        //billes.add(new DecorateurCouleur(new DecorateurRebond(new DecorateurFrottement(new DecorateurPesanteur(new BilleConcrete(p2, rayon, v2),new Vecteur(0,0.001)),4.5)), Color.GREEN));
        //billes.add(new DecorateurCouleur(new DecorateurRebond(new DecorateurFrottement(new DecorateurPesanteur(new BilleConcrete(p3, rayon, v3),new Vecteur(0,0.001)),10)), Color.RED));

        //billes.add(new DecorateurCouleur(new DecorateurRebond(new BilleConcrete(p0, rayon, v0)), Color.RED));
         //billes.add(new DecorateurCouleur(new DecorateurPesanteur(new DecorateurFrottement(new DecorateurRebond(new BilleConcrete(p1, rayon, v1)),100), new Vecteur(0, 0.001)), Color.YELLOW));
         //billes.add(new DecorateurCouleur(new DecorateurRebond(new DecorateurFrottement(new DecorateurNewton(new BilleConcrete(p2, rayon, v2)),0.001)), Color.GREEN));
        //billes.add(new DecorateurPasseMuraille(new DecorateurCouleur(new BilleConcrete(p3, rayon, v3), Color.BLUE)));
        //Bille B = new DecorateurHurlement(new DecorateurCouleur(new DecorateurBloqueBord(new DecorateurNewton(new BilleConcrete(p4, rayon, v4))), Color.black), hurlements[choixHurlementInitial], cadre);
        //billes.add(B);

        //cadre.addChoixHurlementListener((ItemListener) B);
        cadre.addChoixHurlementListener((ItemListener) b2);
        //MouseFollower mf = new MouseFollower();
        cadre.addMouseMotionListener(b.getControlleurGeneral());
        cadre.addMouseListener(b.getControlleurGeneral());
        cadre.addMouseMotionListener(b2.getControlleurGeneral());
        cadre.addMouseListener(b2.getControlleurGeneral());
        //---------------------- ici finit la partie e changer -------------------------------------------------------------

        System.out.println("billes = " + billes);


//-------------------- creation de l'objet responsable de l'animation (c'est un thread separe) -----------------------

        AnimationBilles animationBilles = new AnimationBilles(billes, cadre);

//----------------------- mise en place des ecouteurs de boutons qui permettent de contreler (un peu...) l'application -----------------


        EcouteurBoutonLancer ecouteurBoutonLancer = new EcouteurBoutonLancer(animationBilles);
        EcouteurBoutonArreter ecouteurBoutonArreter = new EcouteurBoutonArreter(animationBilles);

//------------------------- activation des ecouteurs des boutons et ea tourne tout seul ------------------------------

        cadre.lancerBilles.addActionListener(ecouteurBoutonLancer);             // pourrait etre remplace par Observable - Observer
        cadre.arreterBilles.addActionListener(ecouteurBoutonArreter);           // pourrait etre remplace par Observable - Observer


    }

}
