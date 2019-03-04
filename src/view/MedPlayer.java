package view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.*;

public class MedPlayer {

    private String file;
    private Media hit;
    private MediaPlayer mediaPlayer;
    private MediaView music;
    private boolean isPlaying;

    public MedPlayer(File filename){
        final JFXPanel fxpanel = new JFXPanel();
        //file = "C:\\Users\\LENOVO\\GitHub Repository\\SWDESPA-DC2\\src\\audio\\" + filename + ".mp3";
        hit = new Media(getClass().getResource("/audio/" + filename + ".mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(hit);
        music = new MediaView(mediaPlayer);
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

    public static void main(String[] args) throws IOException {
        File file = new File("Nelly Furtado - Say It Right");
        MedPlayer me = new MedPlayer(file);
        me.play();
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
