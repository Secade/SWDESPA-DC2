package model;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.*;

public class MedPlayer {

    private MediaPlayer mediaPlayer;
    private boolean isPlaying;

    public MedPlayer(String filename){
        final JFXPanel fxpanel = new JFXPanel();
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/audio/"+filename+".mp3").toExternalForm()));
    }

    public void play(){
        mediaPlayer.play();
        isPlaying = true;
        System.out.println("played");
    }

    public void pause(){
        mediaPlayer.pause();
        isPlaying = false;
        System.out.println("paused");
    }

    public void stop(){
        mediaPlayer.stop();
        isPlaying = false;
        System.out.println("stop");
    }

    public boolean isPlaying(){
        return isPlaying;
    }

    public Duration getStartTime(){
        return mediaPlayer.getStartTime();
    }

    public Duration getEndTime(){
        return mediaPlayer.getStopTime();
    }
}