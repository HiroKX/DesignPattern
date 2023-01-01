package exodecorateur_angryballs.solution.son;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import musique.javax.FloatControlFantôme;

import javax.sound.sampled.*;
import javax.sound.sampled.FloatControl.Type;
import java.io.File;
import java.io.IOException;

/** Gère les sons. */

public abstract class SonJavaxRobin implements Runnable {
    public static final int TAILLE_BUFFER_LIGNE = 4800;
    SourceDataLine ligne;
    byte[] tampon;
    AudioFormat audioFormat;
    String nomFichier;
    int debutExtrait;
    int finExtrait;
    double volume;
    double balance;
    double coeffPitch;

    public void initInfos(String nomFichier, int debutExtrait, int finExtrait) {
        this.nomFichier = nomFichier;
        this.debutExtrait = debutExtrait;
        this.finExtrait = finExtrait;
    }

    public String getNomFichier() {
        return this.nomFichier;
    }

    static double convertit(double volume, double gainMin, double gainMax) {
        if (volume < 0.0) {
            return gainMin;
        } else {
            double u = 2.0 * volume - 1.0;
            if (volume < 0.5) {
                return -gainMin * u;
            } else {
                return volume < 1.0 ? gainMax * u : gainMax;
            }
        }
    }

    public SonJavaxRobin(File repertoire, String nomFichier, int debutExtrait, int finExtrait) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        this(AudioSystem.getAudioInputStream(new File(repertoire, nomFichier)), debutExtrait, finExtrait);
        this.initInfos(nomFichier, debutExtrait, finExtrait);
    }

    public void init(AudioFormat format, byte[] tampon, double volume, double balance, double coeffPitch) throws LineUnavailableException {
        this.ligne = AudioSystem.getSourceDataLine(format);
        this.ligne.open(format, 4800);
        this.tampon = tampon;
        this.audioFormat = format;
        this.volume = volume;
        this.balance = balance;
        this.coeffPitch = coeffPitch;
    }

    protected SonJavaxRobin(AudioFormat format, byte[] tampon, double volume, double balance, double coeffPitch) throws LineUnavailableException {
        this.nomFichier = "non renseigne";
        this.debutExtrait = -1;
        this.finExtrait = -1;
        this.init(format, tampon, volume, balance, coeffPitch);
    }

    protected SonJavaxRobin(AudioInputStream fichierAudio, int debutExtrait, int finExtrait) throws LineUnavailableException, IOException {
        this.nomFichier = "non renseigne";
        this.debutExtrait = -1;
        this.finExtrait = -1;
        AudioFormat format = fichierAudio.getFormat();
        System.err.println(" format audio : " + format);
        System.out.println(" format du fichier son : " + format);
        System.out.println(" nombre de canaux : " + format.getChannels());
        System.out.println(" nombre de frames par seconde : " + format.getFrameRate());
        System.out.println(" taille d'un frame en octets : " + format.getFrameSize());
        System.out.println(" frequence d'echantillonnage : " + format.getSampleRate());
        System.out.println(" taille d'un echantillon en bits : " + format.getSampleSizeInBits());
        System.out.println("format.isBigEndian() : " + format.isBigEndian());
        double debutSecondes = 0.01 * (double)debutExtrait;
        double finSecondes = 0.01 * (double)finExtrait;
        double dureeSecondes = finSecondes - debutSecondes;
        double frequence = (double)format.getFrameRate();
        double nombreFrames = dureeSecondes * frequence;
        int m = format.getFrameSize();
        int nombreOctets = (int)nombreFrames * m;
        byte[] tampon = new byte[nombreOctets];
        double positionDebut = debutSecondes * frequence;
        int positionDebutOctets = (int)positionDebut * m;
        fichierAudio.skip((long)positionDebutOctets);
        fichierAudio.read(tampon);
        this.init(format, tampon, 0.5, 0.0, 1.0);
        int tailleBufferLigne = this.ligne.getBufferSize();
        System.err.println("tailleBufferLigne = " + tailleBufferLigne);
    }

    public void run() {
        FloatControl contrôleVolume = (FloatControl)this.ligne.getControl(Type.MASTER_GAIN);
        double gainMin = (double)contrôleVolume.getMinimum();
        double gainMax = (double)contrôleVolume.getMaximum();

        Object contrôleBalance;
        try {
            contrôleBalance = (FloatControl)this.ligne.getControl(Type.BALANCE);
        } catch (IllegalArgumentException var10) {
            System.err.println("reglage \"balance\" gauche - droite non disponible pour le fichier son utilise : enregistrement non stereo ?");
            contrôleBalance = new FloatControlFantôme();
        }

        Object contrôlePitch;
        try {
            contrôlePitch = (FloatControl)this.ligne.getControl(Type.SAMPLE_RATE);
        } catch (IllegalArgumentException var9) {
            contrôlePitch = new FloatControlFantôme();
        }

        if (this.tampon == null) {
            throw new ArrayIndexOutOfBoundsException("le tampon n'a pas ete cre");
        } else {
            this.ligne.start();
            this.run1(contrôleVolume, (FloatControl) contrôleBalance, (FloatControl) contrôlePitch, gainMin, gainMax);
            this.ligne.stop();
        }/**
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(
                    "exodecorateur_angryballs" + File.separatorChar +
                            "maladroit" + File.separatorChar + "bruits"+File.separatorChar +this.nomFichier));
            //clip.setLoopPoints(1,2000);
            clip.open(inputStream);
            int nrFrames = clip.getFrameLength();
            clip.loop(1);
            clip.setLoopPoints(nrFrames/3, nrFrames/3*2);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }*/
    }

    protected abstract void run1(FloatControl var1, FloatControl var2, FloatControl var3, double var4, double var6);

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public String toString() {
        return "SonJavax [\n taille tampon=" + this.tampon.length + ",\n audioFormat=" + this.audioFormat + ",\n nomFichier=" + this.nomFichier + ", debutExtrait=" + this.debutExtrait + ", finExtrait=" + this.finExtrait + ", volume=" + this.volume + ", balance=" + this.balance + ", coeffPitch=" + this.coeffPitch + "\n]";
    }
}
