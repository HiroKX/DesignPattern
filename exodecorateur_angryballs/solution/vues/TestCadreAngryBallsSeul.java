package exodecorateur_angryballs.solution.vues;

import java.io.File;
import java.util.Vector;

import exodecorateur_angryballs.solution.OutilsConfigurationBilleHurlante;
import exodecorateur_angryballs.solution.modele.Bille;
import exodecorateur_angryballs.solution.vues.awt.cadre.CadreAngryBallsAWT;
import musique.SonLong;


public class TestCadreAngryBallsSeul
{

/**
 * @param args
 */
public static void main(String[] args)
{
//---------------------- gestion des bruitages : parametrage du chemin du dossier contenant les fichiers audio --------------------------

File file = new File(""); // le oe la JVM est lancee : racine du projet

File repertoireSon = new File(file.getAbsoluteFile(),
      "exodecorateur_angryballs"+File.separatorChar+
      "maladroit"+File.separatorChar+"bruits");

//-------------------- chargement des sons pour les hurlements --------------------------------------

Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(repertoireSon, "config_audio_bille_hurlante.txt");
   
SonLong hurlements[] = SonLong.toTableau(sonsLongs); // on obtient un tableau de SonLong
  
Vector<Bille> billes = new Vector<Bille>();
int choixHurlementInitial = 0;
CadreAngryBallsAWT cadre = CadreAngryBallsAWT.getInstance("angry balls", "animation de billes marrantes", billes, hurlements, choixHurlementInitial);
cadre.montrer();
}

}
