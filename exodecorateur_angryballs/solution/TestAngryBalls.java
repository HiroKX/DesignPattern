package exodecorateur_angryballs.solution;

import java.awt.Color;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import exodecorateur_angryballs.solution.modele.BilleConcrete;
import exodecorateur_angryballs.solution.decorateur.*;
import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.vues.CadreAngryBallsAWT;
import musique.javax.SonLongJavax;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


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
        SonLong billebille;
        try {
            billebille = SonLongJavax.crée(repertoireSon, "collision_bille_bille 0 2000 200");
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
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

        Vecteur p0, p1, p2, p3, p4, v0, v1, v2, v3, v4, v5;    // les positions des centres des billes et les vecteurs vitesse au demarrage.
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
        v5 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);

//--------------- ici commence la partie e changer ---------------------------------
        DecorateurAttraper b = new DecorateurAttraper(new DecorateurCollision(new DecorateurBloqueBord(new DecorateurPesanteur(new DecorateurFrottement(new DecorateurCouleur(new BilleConcrete(p1, rayon * 2, v1, new Vecteur(0, 0.0001)), Color.GREEN), 4.5), new Vecteur(0, 0.001)))));
        billes.add(b);
        DecorateurSonCollision b2 = new DecorateurSonCollision(new DecorateurFrottement(new DecorateurRebond(new DecorateurBloqueBord(new DecorateurPesanteur(new DecorateurCouleur(new BilleConcrete(p2, rayon, v2, new Vecteur(0, 0.0001)), Color.RED), new Vecteur(0, 0.001)))), 8), billebille, cadre);
        DecorateurAttraper b3 = new DecorateurAttraper(b2); //Séparer pour pouvoir faire les ajouts de listener.

        billes.add(b3);

        DecorateurSonCollision b6  = new DecorateurSonCollision(new DecorateurFrottement(new DecorateurRebond(new DecorateurBloqueBord(new DecorateurPesanteur(new DecorateurCouleur(new BilleConcrete(p0, rayon, v3, new Vecteur(0, 0.0001)), Color.RED), new Vecteur(0, 0.001)))), 8), billebille, cadre);

        DecorateurSonCollision b4 = new DecorateurSonCollision(new DecorateurFrottement(new DecorateurRebond(new DecorateurBloqueBord(new DecorateurPesanteur(new DecorateurCouleur(new BilleConcrete(p3, rayon, v4, new Vecteur(0, 0.0001)), Color.RED), new Vecteur(0, 0.001)))), 8), billebille, cadre);

        DecorateurSonCollision b5= new DecorateurSonCollision(new DecorateurFrottement(new DecorateurRebond(new DecorateurBloqueBord(new DecorateurPesanteur(new DecorateurCouleur(new BilleConcrete(p4, rayon, v5, new Vecteur(0, 0.0001)), Color.RED), new Vecteur(0, 0.001)))), 8), billebille, cadre);
        billes.add(b4);
        billes.add(b5);
        billes.add(b6);

        cadre.addChoixHurlementListener(b2);//Nécessaire pour le changement de son peut être un observer/observable

        cadre.addMouseMotionListener(b.getControlleurGeneral());//En faite c'est bon comme ça (vu avec le prof)
        cadre.addMouseListener(b.getControlleurGeneral());
        cadre.addMouseMotionListener(b3.getControlleurGeneral());
        cadre.addMouseListener(b3.getControlleurGeneral());
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
