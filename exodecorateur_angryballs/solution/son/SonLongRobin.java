package exodecorateur_angryballs.solution.son;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import musique.SonLong;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class SonLongRobin extends SonLong {
    public SonLongRobin(File répertoire, String nomFichier, int débutExtrait, int finExtrait, int effectif) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        super(new SonBrefRobin[effectif]);
        int deltaT = finExtrait - débutExtrait;
        if (deltaT % effectif != 0) {
            throw new IllegalArgumentException("dans SonLong(...) : finExtrait - débutExtrait n'est pas un multiple de effectif ");
        } else {
            int pas = deltaT / effectif;
            int i = 0;
            int début = débutExtrait;

            for(int fin = débutExtrait + pas; i < effectif; fin += pas) {
                this.sons[i] = new SonBrefRobin(répertoire, nomFichier, début, fin);
                début = fin;
                ++i;
            }

        }
    }

    public static SonLong crée(File répertoireBruits, String s) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        String[] t = s.split(" ");
        String nom = t[0];
        nom = nom + ".wav";
        int débutExtrait = Integer.parseInt(t[1]);
        int finExtrait = Integer.parseInt(t[2]);
        int effectif = Integer.parseInt(t[3]);
        return new SonLongRobin(répertoireBruits, nom, débutExtrait, finExtrait, effectif);
    }
}
