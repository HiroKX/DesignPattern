package exodecorateur_angryballs.solution.son;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import musique.SonBref;
import musique.SonBrefFantome;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SonBrefRobin extends SonJavaxRobin implements SonBref {
    public SonBrefRobin(File repertoire, String nomFichier, int debutExtrait, int finExtrait) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        super(repertoire, nomFichier, debutExtrait, finExtrait);
    }

    public SonBrefRobin(AudioInputStream fichierAudio, int debutExtrait, int finExtrait) throws LineUnavailableException, IOException {
        super(fichierAudio, debutExtrait, finExtrait);
    }

    protected SonBrefRobin(AudioFormat format, byte[] tampon, double volume, double balance, double coeffPitch) throws LineUnavailableException {
        super(format, tampon, volume, balance, coeffPitch);
        this.initInfos(this.nomFichier, this.debutExtrait, this.finExtrait);
    }

    public String getNom() {
        String nomComplet = this.getNomFichier();
        int p = nomComplet.indexOf(46);
        return nomComplet.substring(0, p);
    }

    public SonBref clone() {
        try {
            return new SonBrefRobin(this.ligne.getFormat(), Arrays.copyOf(this.tampon, this.tampon.length), this.volume, this.balance, this.coeffPitch);
        } catch (LineUnavailableException var2) {
            System.err.println("echec de clone() dans SonBrefJavax");
            return new SonBrefFantome();
        }
    }

    public void joue(double volume, double balance, double coeffPitch) {
        this.volume = volume;
        this.balance = balance;
        this.coeffPitch = coeffPitch;
        this.start();
    }

    public void joue(double volume, double balance) {
        this.joue(volume, balance, 1.0);
    }

    protected void run1(FloatControl contrôleVolume, FloatControl contrôleBalance, FloatControl contrôlePitch, double gainMin, double gainMax) {
        double gain = convertit(this.volume, gainMin, gainMax);
        contrôleVolume.setValue((float)gain);
        contrôleBalance.setValue((float)this.balance);
        contrôlePitch.setValue((float)(this.coeffPitch * (double)this.audioFormat.getFrameRate()));
        this.ligne.write(this.tampon, 0, this.tampon.length);
        this.ligne.drain();
    }
}
